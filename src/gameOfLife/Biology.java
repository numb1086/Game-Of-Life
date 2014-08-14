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
	//初始化生物總數
	private void initailBiology(int bioNumber)
	{
		//初始化生物數量450隻
		for(int column=0;column<size;column++)
			//如果每列生物達15隻就停止迴圈
			for(countBio=0;countBio<bioNumber/size;){
				int r = ran.nextInt(size);//亂數0~29
				Map.get(column).set(r, "@ ");
				//判斷該列目前生物有幾隻
				countBio = 0;
				for(int row=0;row<size;row++)
					if(Map.get(column).get(row).equals("@ ")) countBio++;
			}
	}
	//判斷生物是否存活
	private boolean isSurvival(int column,int row)
	{
		boolean isSurvival = true;
		//System.out.println("column= "+ column+" row="+row);
		countBio = 0;
		//判斷該點周遭九宮格內是否有生物，減就是向左或向上，加就是向右或向下
		if((column-1>=0 && row-1>=0) && Map.get(column-1).get(row-1).equals("@ ")) countBio++;
		if((column+1<size && row-1>=0) && Map.get(column+1).get(row-1).equals("@ ")) countBio++;
		if((column-1>=0 && row+1<size) && Map.get(column-1).get(row+1).equals("@ ")) countBio++;
		if((column+1<size && row+1<size) && Map.get(column+1).get(row+1).equals("@ ")) countBio++;
		if(column+1<size && Map.get(column+1).get(row).equals("@ ")) countBio++;
		if(column-1>=0 && Map.get(column-1).get(row).equals("@ ")) countBio++;
		if(row+1<size && Map.get(column).get(row+1).equals("@ ")) countBio++;
		if(row-1>=0 && Map.get(column).get(row-1).equals("@ ")) countBio++;
		//繁衍
		if(countBio==1) isSurvival = true;
		//死亡
		else if(countBio>=2) isSurvival =  false;
//		if(isSurvival || countBio==0)
//			System.out.println("column="+column+" row="+row+" countBio="+countBio+" isSurvival="+isSurvival);
		return isSurvival;
	}
	//設定新世代的繁衍或死亡
	private void setNewGeneration(int column,int row,boolean isSurvival)
	{
		int Rc = ran.nextInt(3)-1;//亂數產生 -1,0,1
		int Rr = ran.nextInt(3)-1;//亂數產生 -1,0,1
		//將存活的生物先畫在新地圖上
		if(countBio<=1) newMap.get(column).set(row,"@ ");
		if(isSurvival && countBio!=0){//繁衍
			//設立邊界，超出邊界則將其設定為邊界
			if((column+Rc)<0) Rc++;
			if((row+Rr)<0) Rr++;
			if((column+Rc)>=size) Rc--;
			if((row+Rr)>=size) Rr--;		
			//若亂數分配在原點或死亡的點就重來
			if((Map.get(column+Rc).get(row+Rr).equals("@ ") && !isSurvival((column+Rc),(row+Rr)))
				||((column+Rc)==column && (row+Rr)==row)){
				setNewGeneration(column,row,isSurvival(column,row));
			}else 
				newMap.get(column+Rc).set(row+Rr,"@ ");	
		}
		else if(countBio!=0)//死亡
			newMap.get(column).set(row, "- ");
	}
	//設定生物繁衍或死亡
	public void setBiology(LinkedList<LinkedList<String>> Map,LinkedList<LinkedList<String>> nextMap)
	{
		this.Map = Map;
		this.newMap = nextMap;
		for(int column=0;column<size;column++)
			for(int row=0;row<size;row++)
				if(Map.get(column).get(row).equals("@ "))
					setNewGeneration(column,row,isSurvival(column,row));//設定新的世代
	}	
}