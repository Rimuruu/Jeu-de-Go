import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Plateau{
	public Case[][] plat;
	public int j;
	public Case lastj1;
	public Case lastj2;
	public float scoren;
	public float scoreb;
	public int nbPasser;
	public LinkedList<Case> hoshi;
	public LinkedList<Case> groupeMort;
	public int nbhoshi;

	Plateau(Fenetre fenetre){
		this.plat = new Case[fenetre.param.size][fenetre.param.size];
		Panel.setCase(plat,fenetre.param.size,fenetre.panel);
		this.scoren = 0f;
		this.scoreb = fenetre.param.handicap;
		this.nbPasser = 0;

		this.nbhoshi = fenetre.param.pionh;
		
		Plateau.setHoshi(fenetre.param.size,this.plat,fenetre.param.pionh);
	}

	public static void setHoshi(int size,Case[][] plat,int nbhoshi){
		if (size == 9) {
			switch(nbhoshi){
				case 1:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				break;
				case 2:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][6],size);
				break;
				case 3:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[4][4].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][6],size);
				break;
				case 4:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][6],size);
				break;
				case 5:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][6],size);
				plat[4][4].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
				break;
				case 6:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][4].contenue=1;
				Case.setLiberter(plat,plat[2][4],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][4].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				break;
				case 7:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][4].contenue=1;
				Case.setLiberter(plat,plat[2][4],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
			
				plat[4][4].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
	
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][4].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				break;
				case 8:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][4].contenue=1;
				Case.setLiberter(plat,plat[2][4],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
				plat[4][2].contenue=1;
				Case.setLiberter(plat,plat[4][2],size);
				plat[4][6].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][4].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				break;
				case 9:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][4].contenue=1;
				Case.setLiberter(plat,plat[2][4],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
				plat[4][2].contenue=1;
				Case.setLiberter(plat,plat[4][2],size);
				plat[4][4].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
				plat[4][6].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][4].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				break;

			}

			
			
		}
		else if (size == 13) {
			switch(nbhoshi){
				case 1:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				break;
				case 2:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				break;
				case 3:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][6],size);
				break;
				case 4:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][9].contenue=1;
				Case.setLiberter(plat,plat[3][9],size);
				plat[9][3].contenue=1;
				Case.setLiberter(plat,plat[9][3],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				break;
				case 5:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][9].contenue=1;
				Case.setLiberter(plat,plat[3][9],size);
				plat[9][3].contenue=1;
				Case.setLiberter(plat,plat[9][3],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][6],size);
				
				break;
				case 6:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][4].contenue=1;
				Case.setLiberter(plat,plat[2][4],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][4].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				break;
				case 7:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][4].contenue=1;
				Case.setLiberter(plat,plat[2][4],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
			
				plat[4][4].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
	
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][4].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				break;
				case 8:
				plat[2][2].contenue=1;
				Case.setLiberter(plat,plat[2][2],size);
				plat[2][4].contenue=1;
				Case.setLiberter(plat,plat[2][4],size);
				plat[2][6].contenue=1;
				Case.setLiberter(plat,plat[2][6],size);
				plat[4][2].contenue=1;
				Case.setLiberter(plat,plat[4][2],size);
				plat[4][6].contenue=1;
				Case.setLiberter(plat,plat[4][4],size);
				plat[6][2].contenue=1;
				Case.setLiberter(plat,plat[6][2],size);
				plat[6][4].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][4],size);
				break;
				case 9:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][6].contenue=1;
				Case.setLiberter(plat,plat[3][6],size);
				plat[3][9].contenue=1;
				Case.setLiberter(plat,plat[3][9],size);
				plat[9][3].contenue=1;
				Case.setLiberter(plat,plat[9][3],size);
				plat[9][6].contenue=1;
				Case.setLiberter(plat,plat[9][6],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[6][6].contenue=1;
				Case.setLiberter(plat,plat[6][6],size);
				plat[6][9].contenue=1;
				Case.setLiberter(plat,plat[6][9],size);
				plat[6][3].contenue=1;
				Case.setLiberter(plat,plat[6][3],size);
				break;

			}
	
		}
		else if (size == 19) {
			switch(nbhoshi){
				case 1:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				break;
				case 2:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);
				break;	
				case 3:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);
				break;	
				case 4:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][15].contenue=1;
				Case.setLiberter(plat,plat[3][15],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[15][3].contenue=1;
				Case.setLiberter(plat,plat[15][3],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);
				break;	
				case 5:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][15].contenue=1;
				Case.setLiberter(plat,plat[3][15],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[15][3].contenue=1;
				Case.setLiberter(plat,plat[15][3],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);
				break;	
				case 6:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][9].contenue=1;
				Case.setLiberter(plat,plat[3][9],size);
				plat[3][15].contenue=1;
				Case.setLiberter(plat,plat[3][15],size);
				plat[15][3].contenue=1;
				Case.setLiberter(plat,plat[15][3],size);
				plat[15][9].contenue=1;
				Case.setLiberter(plat,plat[15][9],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);
				break;	
				case 7:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][9].contenue=1;
				Case.setLiberter(plat,plat[3][9],size);
				plat[3][15].contenue=1;
				Case.setLiberter(plat,plat[3][15],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[15][3].contenue=1;
				Case.setLiberter(plat,plat[15][3],size);
				plat[15][9].contenue=1;
				Case.setLiberter(plat,plat[15][9],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);
				break;	
				case 8:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][9].contenue=1;
				Case.setLiberter(plat,plat[3][9],size);
				plat[3][15].contenue=1;
				Case.setLiberter(plat,plat[3][15],size);
				plat[9][3].contenue=1;
				Case.setLiberter(plat,plat[9][3],size);
				plat[9][15].contenue=1;
				Case.setLiberter(plat,plat[9][15],size);
				plat[15][3].contenue=1;
				Case.setLiberter(plat,plat[15][3],size);
				plat[15][9].contenue=1;
				Case.setLiberter(plat,plat[15][9],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);	
				break;
				case 9:
				plat[3][3].contenue=1;
				Case.setLiberter(plat,plat[3][3],size);
				plat[3][9].contenue=1;
				Case.setLiberter(plat,plat[3][9],size);
				plat[3][15].contenue=1;
				Case.setLiberter(plat,plat[3][15],size);
				plat[9][3].contenue=1;
				Case.setLiberter(plat,plat[9][3],size);
				plat[9][9].contenue=1;
				Case.setLiberter(plat,plat[9][9],size);
				plat[9][15].contenue=1;
				Case.setLiberter(plat,plat[9][15],size);
				plat[15][3].contenue=1;
				Case.setLiberter(plat,plat[15][3],size);
				plat[15][9].contenue=1;
				Case.setLiberter(plat,plat[15][9],size);
				plat[15][15].contenue=1;
				Case.setLiberter(plat,plat[15][15],size);	
				break;
			}
	
		
		}

	}

	public static boolean isEqual(Plateau plat1, Plateau plat2){
		for (int i = 0;i<plat1.plat.length;i=i+1) {
			for (int y = 0;y< plat1.plat[i].length;y=y+1) {
				if (plat1.plat[i][y].contenue != plat2.plat[i][y].contenue && plat1.plat[i][y].contenue != 3 && plat1.plat[i][y].contenue != 4 && plat2.plat[i][y].contenue != 3 && plat2.plat[i][y].contenue != 4) {
					return false;
				}
			}
		}
		return true;
	}

	public static int contient(DefaultListModel<Plateau> list, Plateau plat){
		Plateau a;
		int b=0;
		for (int i = 0;i<list.size();i=i+1) {
			a = list.getElementAt(i);
			
			if (Plateau.isEqual(a,plat) == true) {
			
					b=b+1;
				}	
		}
		return b;
	}
}