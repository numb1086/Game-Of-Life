/*******************************************************************************
 * FileName       	[ Environment.java ]
 * PackageName    	[ gameOfLife ]
 * JavaProjectName	[ GameOfLife ]
 * Synopsis       	[ This file sets the environment and shows new generations.]
 * Author         	[ Yong-Ting (Tony) Wu ]
 * Copyright      	[ Copyleft(c) 2014 MITLAB, GIEE, NTUST, Taiwan ]
********************************************************************************/
package gameOfLife;

import java.util.*;

public class Environment
{
	public int size;
	public LinkedList<LinkedList<String>> Map = new LinkedList<LinkedList<String>>();
	private LinkedList<LinkedList<String>> newMap;
	
	public Environment(int size)
	{			
		this.size = size;
		initailEnvironment(Map);//��l���¥@�N����
	}
	private void initailEnvironment(LinkedList<LinkedList<String>> tempMap)
	{
		//�إߤG�� LinkedList�}�C�ëإ�30*30����
		for(int i=0;i<size;i++){
			LinkedList<String> mapContent = new LinkedList<String>();
			for(int j=0;j<size;j++)
				mapContent.add("- ");
			tempMap.add(mapContent);
		}
	}
	private void show(int generation)
	{
		int totalBio = 0;
		if(generation==1) newMap = Map;//��1�N������¦a��
		for(int column=0;column<size;column++){
			for(int row=0;row<size;row++){
				System.out.print(newMap.get(column).get(row));
				if(newMap.get(column).get(row).equals("@ ")) totalBio++;
			}
			System.out.println();
		}
		System.out.printf("\n��%d�@�N ",generation);
		System.out.println("totalBio = " + totalBio + "\n");
	}
	//�o��s�@�N
	public void getNewGeneration(Biology bio,int genNum)
	{
		//��ܲ�1�N���Ҫ��ͪ�����
		show(1);
		//���ͷs�@�N�����
		//newMap = null;//����O����
		for(int i=2;i<=genNum;i++){
			newMap = new LinkedList<LinkedList<String>>();
			initailEnvironment(newMap);//��l�Ʒs�@�N����
			bio.setBiology(Map,newMap);//�N�s�@�N���X���ͪ���J�s�a��
			show(i);
			try {
			    //�C����ܩ���0.5��
			    Thread.sleep(500);
			} catch (InterruptedException ie) {
			    System.out.println(ie);
			}
			Map = newMap;//�N�s�a�ϳ]���¦a��
		}
	}
}