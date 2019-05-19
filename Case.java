import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;


public class Case{
	public int x;
	public int y;
	public int index;
	public int indey;
	public int contenue;
	public LinkedList<Case> liberte;
	Case(int x,int y,int index,int indey){
		this.x = x;
		this.y = y;
		this.index = index;
		this.indey = indey;
		this.liberte = new LinkedList<Case>();
		this.contenue = 0;
	}

	public static Case searchCase(Case[][] plat,int posx,int posy,int size){
		int cases=9;
		if (size == 19) {
			cases = 44;
			
		}
		else if (size == 9) {
			cases = 95;
		}
		else if (size == 13) 
{			cases = 60;
		}

		for (int x = 0 ;x<size;x=x+1) {
			for (int y = 0 ;y<size;y=y+1) {
				if (posx > plat[x][y].x && posx < plat[x][y].x+cases && posy > plat[x][y].y && posy < plat[x][y].y+cases ) {
					return plat[x][y];
				}
			
}		}	

		return null;



	}

	public static void supprimerLiberter(Case cases,Case supprimer){
		cases.liberte.remove(supprimer);

	}

	public int sizeLiberter(){
		return this.liberte.size();

	}

	public static void setLiberter(Case[][] plat,Case cases,int size){
		if (cases.index != 0) {
			if(plat[cases.index-1][cases.indey].contenue == 1 || plat[cases.index-1][cases.indey].contenue == 2){
				Case.supprimerLiberter(plat[cases.index-1][cases.indey],cases);
			}
			else{
				cases.liberte.add(plat[cases.index-1][cases.indey]);
			}
		}
	
		if (cases.indey != 0) {
			if(plat[cases.index][cases.indey-1].contenue == 1 || plat[cases.index][cases.indey-1].contenue == 2){
				Case.supprimerLiberter(plat[cases.index][cases.indey-1],cases);
			}
			else{
				cases.liberte.add(plat[cases.index][cases.indey-1]);
			}
			
		}
		if (cases.index != size-1) {
			if(plat[cases.index+1][cases.indey].contenue == 1 || plat[cases.index+1][cases.indey].contenue == 2){
				Case.supprimerLiberter(plat[cases.index+1][cases.indey],cases);
			}
			else{
				cases.liberte.add(plat[cases.index+1][cases.indey]);
			}
		
		}
		if (cases.indey != size-1) {
			if(plat[cases.index][cases.indey+1].contenue == 1 || plat[cases.index][cases.indey+1].contenue == 2){
				Case.supprimerLiberter(plat[cases.index][cases.indey+1],cases);
			}
			else{
				cases.liberte.add(plat[cases.index][cases.indey+1]);
			}
		
			
		}
	}

}