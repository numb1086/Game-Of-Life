/*************************************************************************
 * FileName       	[ Main.java ]
 * PackageName    	[ gameOfLife ]
 * JavaProjectName	[ GameOfLife ]
 * Synopsis       	[ This file initials all the settings.]
 * Author         	[ Yong-Ting (Tony) Wu ]
 * Copyright      	[ Copyleft(c) 2014 MITLAB, GIEE, NTUST, Taiwan ]
**************************************************************************/
package gameOfLife;

public class Main
{
	public static void main(String[] args)
	{
		//初始化環境大小與生物總數
		Environment env = new Environment(30);
		Biology bio = new Biology(env.Map,env.size,450);
		//將生物放入環境及設定世代數並顯示結果
		env.getNewGeneration(bio,100);
	}
}
