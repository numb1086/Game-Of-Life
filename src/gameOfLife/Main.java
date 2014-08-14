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
		//��l�����Ҥj�p�P�ͪ��`��
		Environment env = new Environment(30);
		Biology bio = new Biology(env.Map,env.size,450);
		//�N�ͪ���J���Ҥγ]�w�@�N�ƨ���ܵ��G
		env.getNewGeneration(bio,100);
	}
}
