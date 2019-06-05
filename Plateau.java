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

	Plateau(Fenetre fenetre){
		this.plat = new Case[fenetre.param.size][fenetre.param.size];
		Panel.setCase(plat,fenetre.param.size,fenetre.panel);
		this.scoren = 0f;
		this.scoreb = fenetre.param.handicap;
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