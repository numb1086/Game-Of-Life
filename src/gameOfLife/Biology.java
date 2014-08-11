/*********************************************************************
 * FileName       	[ Biology.java ]
 * PackageName    	[ gameOfLife ]
 * JavaProjectName	[ GameOfLife ]
 * Synopsis       	[ This file generates new biology.]
 * Author         	[ Yong-Ting (Tony) Wu ]
 * Copyright      	[ Copyleft(c) 2014 MITLAB, GIEE, NTUST, Taiwan ]
*********************************************************************/
package gameOfLife;

import java.util.LinkedList;
import java.util.Random;

public class Biology 
{
	private LinkedList<LinkedList<String>> map;
	private Random ran = new Random();
	private int size,countBio;
	
	public Biology(LinkedList<LinkedList<String>> map,int size,int bioNumber)
	{
		this.map = map;
		this.size = size;
		initailBiology(bioNumber);
	}
	//��l�ƥͪ��`��
	private void initailBiology(int bioNumber)
	{
		//��l�ƥͪ��ƶq450��
		for(int column=0;column<size;column++)
			//�p�G�C�C�ͪ��F15���N����j��
			for(countBio=0;countBio<bioNumber/size;){
				int r = ran.nextInt(size);//�ü�0~29
				map.get(column).set(r, "@ ");
				//�P�_�ӦC�ثe�ͪ����X��
				countBio = 0;
				for(int row=0;row<size;row++)
					if(map.get(column).get(row).equals("@ ")) countBio++;
			}
	}
	//�P�_�ͪ��O�_�s��
	private boolean isSurvival(int column,int row)
	{
		boolean isSurvival = false;
		countBio = 0;
		//�P�_���I�P�D�E�c�椺�O�_���ͪ��A��N�O�V���ΦV�W�A�[�N�O�V�k�ΦV�U
		if((column-1>=0 && row-1>=0) && map.get(column-1).get(row-1).equals("@ ")) countBio++;
		if((column+1<size && row-1>=0) && map.get(column+1).get(row-1).equals("@ ")) countBio++;
		if((column-1>=0 && row+1<size) && map.get(column-1).get(row+1).equals("@ ")) countBio++;
		if((column+1<size && row+1<size) && map.get(column+1).get(row+1).equals("@ ")) countBio++;
		if(column+1<size && map.get(column+1).get(row).equals("@ ")) countBio++;
		if(column-1>=0 && map.get(column-1).get(row).equals("@ ")) countBio++;
		if(row+1<size && map.get(column).get(row+1).equals("@ ")) countBio++;
		if(row-1>=0 && map.get(column).get(row-1).equals("@ ")) countBio++;
		//�c�l
		if(countBio==1) isSurvival = true;
		//���`
		else if(countBio>=2) isSurvival =  false;
		
		return isSurvival;
	}
	//�]�w�s�@�N���c�l�Φ��`
	private void setNewGeneration(int column,int row,boolean isSurvival)
	{
		int r = ran.nextInt(3)-1;//�üƲ��� -1,0,1
		if(isSurvival && countBio!=0){//�c�l
			//�]����ɱ���A�W�X��ɫh���s���t
			if((column+r)>=0 && (row+r)>=0 && (column+r)<size && (row+r)<size)
				map.get(column+r).set(row+r,"@ ");
			else 
				setNewGeneration(column,row,isSurvival(column,row));
		}
		else if(countBio!=0)//���`
			map.get(column).set(row, "- ");
	}
	//�]�w�ͪ��c�l�Φ��`
	public void setBiology()
	{
		for(int column=0;column<size;column++)
			for(int row=0;row<size;row++)
				if(map.get(column).get(row).equals("@ "))
					setNewGeneration(column,row,isSurvival(column,row));//�]�w�s���@�N
	}	
}