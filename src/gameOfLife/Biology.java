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
	//初始化生物總數
	private void initailBiology(int bioNumber)
	{
		//初始化生物數量450隻
		for(int column=0;column<size;column++)
			//如果每列生物達15隻就停止迴圈
			for(countBio=0;countBio<bioNumber/size;){
				int r = ran.nextInt(size);//亂數0~29
				map.get(column).set(r, "@ ");
				//判斷該列目前生物有幾隻
				countBio = 0;
				for(int row=0;row<size;row++)
					if(map.get(column).get(row).equals("@ ")) countBio++;
			}
	}
	//判斷生物是否存活
	private boolean isSurvival(int column,int row)
	{
		boolean isSurvival = false;
		countBio = 0;
		//判斷該點周遭九宮格內是否有生物，減就是向左或向上，加就是向右或向下
		if((column-1>=0 && row-1>=0) && map.get(column-1).get(row-1).equals("@ ")) countBio++;
		if((column+1<size && row-1>=0) && map.get(column+1).get(row-1).equals("@ ")) countBio++;
		if((column-1>=0 && row+1<size) && map.get(column-1).get(row+1).equals("@ ")) countBio++;
		if((column+1<size && row+1<size) && map.get(column+1).get(row+1).equals("@ ")) countBio++;
		if(column+1<size && map.get(column+1).get(row).equals("@ ")) countBio++;
		if(column-1>=0 && map.get(column-1).get(row).equals("@ ")) countBio++;
		if(row+1<size && map.get(column).get(row+1).equals("@ ")) countBio++;
		if(row-1>=0 && map.get(column).get(row-1).equals("@ ")) countBio++;
		//繁衍
		if(countBio==1) isSurvival = true;
		//死亡
		else if(countBio>=2) isSurvival =  false;
		
		return isSurvival;
	}
	//設定新世代的繁衍或死亡
	private void setNewGeneration(int column,int row,boolean isSurvival)
	{
		int r = ran.nextInt(3)-1;//亂數產生 -1,0,1
		if(isSurvival && countBio!=0){//繁衍
			//設立邊界條件，超出邊界則重新分配
			if((column+r)>=0 && (row+r)>=0 && (column+r)<size && (row+r)<size)
				map.get(column+r).set(row+r,"@ ");
			else 
				setNewGeneration(column,row,isSurvival(column,row));
		}
		else if(countBio!=0)//死亡
			map.get(column).set(row, "- ");
	}
	//設定生物繁衍或死亡
	public void setBiology()
	{
		for(int column=0;column<size;column++)
			for(int row=0;row<size;row++)
				if(map.get(column).get(row).equals("@ "))
					setNewGeneration(column,row,isSurvival(column,row));//設定新的世代
	}	
}