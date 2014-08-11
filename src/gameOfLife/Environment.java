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
	public LinkedList<LinkedList<String>> map = new LinkedList<LinkedList<String>>();
	
	public Environment(int size)
	{			
		this.size = size;
		initailEnvironment();//初始化環境
	}
	private void initailEnvironment()
	{
		//建立二維 LinkedList陣列並建立30*30環境
		for(int i=0;i<size;i++){
			LinkedList<String> mapContent = new LinkedList<String>();
			for(int j=0;j<size;j++)
				mapContent.add("- ");
			map.add(mapContent);
		}
	}
	private void show(int generation)
	{
		int totalBio = 0;
		for(int column=0;column<size;column++){
			for(int row=0;row<size;row++){
				System.out.print(map.get(column).get(row));
				if(map.get(column).get(row).equals("@ ")) totalBio++;
			}
			System.out.println();
		}
		System.out.printf("\n第%d世代 ",generation);
		System.out.println("totalBio = " + totalBio + "\n");
	}
	//得到新世代
	public void getNewGeneration(Biology bio,int genNum)
	{
		//顯示第1代環境的生物分布
		show(1);
		//產生新世代並顯示
		for(int i=2;i<=genNum;i++){
			bio.setBiology();
			show(i);
			try {
			    //每次顯示延遲0.5秒
			    Thread.sleep(500);
			} catch (InterruptedException ie) {
			    System.out.println(ie);
			}
		}
	}
}