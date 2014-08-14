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
	private LinkedList<LinkedList<String>> Map;
	private LinkedList<LinkedList<String>> newMap;
	private Random ran = new Random();
	private int size,countBio;
	
	public Biology(LinkedList<LinkedList<String>> Map,int size,int bioNumber)
	{
		this.Map = Map;
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
				Map.get(column).set(r, "@ ");
				//�P�_�ӦC�ثe�ͪ����X��
				countBio = 0;
				for(int row=0;row<size;row++)
					if(Map.get(column).get(row).equals("@ ")) countBio++;
			}
	}
	//�P�_�ͪ��O�_�s��
	private boolean isSurvival(int column,int row)
	{
		boolean isSurvival = true;
		//System.out.println("column= "+ column+" row="+row);
		countBio = 0;
		//�P�_���I�P�D�E�c�椺�O�_���ͪ��A��N�O�V���ΦV�W�A�[�N�O�V�k�ΦV�U
		if((column-1>=0 && row-1>=0) && Map.get(column-1).get(row-1).equals("@ ")) countBio++;
		if((column+1<size && row-1>=0) && Map.get(column+1).get(row-1).equals("@ ")) countBio++;
		if((column-1>=0 && row+1<size) && Map.get(column-1).get(row+1).equals("@ ")) countBio++;
		if((column+1<size && row+1<size) && Map.get(column+1).get(row+1).equals("@ ")) countBio++;
		if(column+1<size && Map.get(column+1).get(row).equals("@ ")) countBio++;
		if(column-1>=0 && Map.get(column-1).get(row).equals("@ ")) countBio++;
		if(row+1<size && Map.get(column).get(row+1).equals("@ ")) countBio++;
		if(row-1>=0 && Map.get(column).get(row-1).equals("@ ")) countBio++;
		//�c�l
		if(countBio==1) isSurvival = true;
		//���`
		else if(countBio>=2) isSurvival =  false;
//		if(isSurvival || countBio==0)
//			System.out.println("column="+column+" row="+row+" countBio="+countBio+" isSurvival="+isSurvival);
		return isSurvival;
	}
	//�]�w�s�@�N���c�l�Φ��`
	private void setNewGeneration(int column,int row,boolean isSurvival)
	{
		int Rc = ran.nextInt(3)-1;//�üƲ��� -1,0,1
		int Rr = ran.nextInt(3)-1;//�üƲ��� -1,0,1
		//�N�s�����ͪ����e�b�s�a�ϤW
		if(countBio<=1) newMap.get(column).set(row,"@ ");
		if(isSurvival && countBio!=0){//�c�l
			//�]����ɡA�W�X��ɫh�N��]�w�����
			if((column+Rc)<0) Rc++;
			if((row+Rr)<0) Rr++;
			if((column+Rc)>=size) Rc--;
			if((row+Rr)>=size) Rr--;		
			//�Y�üƤ��t�b���I�Φ��`���I�N����
			if((Map.get(column+Rc).get(row+Rr).equals("@ ") && !isSurvival((column+Rc),(row+Rr)))
				||((column+Rc)==column && (row+Rr)==row)){
				setNewGeneration(column,row,isSurvival(column,row));
			}else 
				newMap.get(column+Rc).set(row+Rr,"@ ");	
		}
		else if(countBio!=0)//���`
			newMap.get(column).set(row, "- ");
	}
	//�]�w�ͪ��c�l�Φ��`
	public void setBiology(LinkedList<LinkedList<String>> Map,LinkedList<LinkedList<String>> nextMap)
	{
		this.Map = Map;
		this.newMap = nextMap;
		for(int column=0;column<size;column++)
			for(int row=0;row<size;row++)
				if(Map.get(column).get(row).equals("@ "))
					setNewGeneration(column,row,isSurvival(column,row));//�]�w�s���@�N
	}	
}