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
	public Panel panel;
	Case(int x,int y,int index,int indey,Panel panel){
		this.x = x;
		this.y = y;
		this.index = index;
		this.indey = indey;
		this.liberte = new LinkedList<Case>();
		this.contenue = 0;
		this.panel = panel;
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

public void prise(Case[][] plat,int size){
	int a = 0;
	int b = this.contenue;
	if (this.contenue == 1) {
		a = 2;
	}
	else{
		a =1;
	}
	this.contenue = 0;
	if (this.index != 0) {
			if(plat[this.index-1][this.indey].contenue == a){
				Case.ajouterLiberter(plat[this.index-1][this.indey],this);
			}
			else if(plat[this.index-1][this.indey].contenue == b){
				plat[this.index-1][this.indey].prise(plat,size);
			}
			
		}
	
	if (this.indey != 0) {
			if(plat[this.index][this.indey-1].contenue == a){
				Case.ajouterLiberter(plat[this.index][this.indey-1],this);	
			}
			else if(plat[this.index][this.indey-1].contenue == b){
				plat[this.index][this.indey-1].prise(plat,size);
			}
			
		}
	if (this.index != size-1) {
			if(plat[this.index+1][this.indey].contenue == a ){
				Case.ajouterLiberter(plat[this.index+1][this.indey],this);
			}
			else if(plat[this.index+1][this.indey].contenue == b){
				plat[this.index+1][this.indey].prise(plat,size);
			}
		
		}
	if (this.indey != size-1) {
			if(plat[this.index][this.indey+1].contenue == a){
				Case.ajouterLiberter(plat[this.index][this.indey+1],this);
			}
			else if(plat[this.index][this.indey+1].contenue == b){
				plat[this.index][this.indey+1].prise(plat,size);
			}
			
		
			
		}

		this.liberte.clear();
		


}

	public Boolean checkCapture(Case[][] plat,int size){
		int a = 0;
		int b = this.contenue;
		if (this.contenue == 1) {
			a = 2;
		}
		else{
			a =1;
	}
		if (this.index != 0) {
			if (plat[this.index-1][this.indey].sizeLiberter(plat,new LinkedList<Case>(),size) <= 0 && plat[this.index-1][this.indey].contenue == a) {
				return true;
			}
	
		}
	
		if (this.indey != 0) {
			if (plat[this.index][this.indey-1].sizeLiberter(plat,new LinkedList<Case>(),size) <= 0 && plat[this.index][this.indey-1].contenue == a) {
				
				return true;
			}
	
				
			}
		if (this.index != size-1) {
			if (plat[this.index+1][this.indey].sizeLiberter(plat,new LinkedList<Case>(),size) <= 0 && plat[this.index+1][this.indey].contenue == a) {
				return true;
			}
			
			
			}
		if (this.indey != size-1) {
			if (plat[this.index][this.indey+1].sizeLiberter(plat,new LinkedList<Case>(),size) <= 0 && plat[this.index][this.indey+1].contenue == a) {
				return true;
			}
			
				
			
				
			}
		return false;


	}

	public LinkedList<Case> checkCaseCapture(Case[][] plat,int size){
		int a = 0;
		int b = this.contenue;
		LinkedList<Case> capture = new LinkedList<Case>();
		if (this.contenue == 1) {
			a = 2;
		}
		else{
			a =1;
	}
		if (this.index != 0) {
			if (plat[this.index-1][this.indey].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[this.index-1][this.indey].contenue == a) {
				capture.add(plat[this.index-1][this.indey]);
				System.out.println("Size capture liberte "+(plat[this.index-1][this.indey].sizeLiberter(plat,new LinkedList<Case>(),size)-1));
				System.out.println("A:Casex "+(this.index-1)+" Casey "+this.indey);
			}
	
		}
	
		if (this.indey != 0) {
			if (plat[this.index][this.indey-1].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[this.index][this.indey-1].contenue == a) {
				
				capture.add(plat[this.index][this.indey-1]);
				System.out.println("B:Casex "+this.index+" Casey "+(this.indey-1));
			}
	
				
			}
		if (this.index != size-1) {
			if (plat[this.index+1][this.indey].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[this.index+1][this.indey].contenue == a) {
				capture.add(plat[this.index+1][this.indey]);
				System.out.println("C:Casex "+(this.index+1)+" Casey "+this.indey);
			}
			
			
			}
		if (this.indey != size-1) {
			if (plat[this.index][this.indey+1].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[this.index][this.indey+1].contenue == a) {
				capture.add(plat[this.index][this.indey+1]);
				System.out.println("D:Casex "+this.index+" Casey "+(this.indey+1));
			}
			
				
			
				
			}
		return capture;


	}

	public static void checkLiberter(Case[][] plat,int size,Case cases,Panel panel){
		int a = 0;
		int b = cases.contenue;
		Case lastCase;
		if (cases.contenue == 1) {
			a = 2;
			lastCase = panel.lastj2;
		}
		else{
			a =1;
			lastCase = panel.lastj1;
	}

		System.out.println(cases.sizeLiberter(plat,new LinkedList<Case>(),size));
		LinkedList<Case> capture = cases.checkCaseCapture(plat,size);
		if (cases.sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && cases.checkCapture(plat,size) == false) {
			panel.j = b;
			cases.contenue = 0;
			Case.unsetLiberter(plat,cases,size);
			cases.liberte.clear();
			System.out.println("Suicide Interdit");

			

		}
		
		else if (cases.sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && capture.size() == 1 && capture.contains(lastCase)==true) {
			panel.j = b;
			cases.contenue = 0;
			Case.unsetLiberter(plat,cases,size);
			cases.liberte.clear();
			System.out.println(capture.size());
			System.out.println("Regle du ko");
		}
		else{
			if (cases.sizeLiberter(plat,new LinkedList<Case>(),size) == 0) {
				System.out.println("Suicide Capture");
			}
			if (cases.contenue == 1) {
				panel.lastj1=cases;
		}
			else{
			
				panel.lastj2=cases;
		}
			if (cases.index != 0) {
				if (plat[cases.index-1][cases.indey].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index-1][cases.indey].contenue == a) {
					plat[cases.index-1][cases.indey].prise(plat,size);
				}
		
			}
		
			if (cases.indey != 0) {
				if (plat[cases.index][cases.indey-1].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index][cases.indey-1].contenue == a) {
					plat[cases.index][cases.indey-1].prise(plat,size);
				}
		
					
				}
			if (cases.index != size-1) {
				if (plat[cases.index+1][cases.indey].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index+1][cases.indey].contenue == a) {
					plat[cases.index+1][cases.indey].prise(plat,size);
				}
				
				
				}
			if (cases.indey != size-1) {
				if (plat[cases.index][cases.indey+1].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index][cases.indey+1].contenue == a) {
					plat[cases.index][cases.indey+1].prise(plat,size);
				}
				
					
				
					
				}
		}
				
		
		

	}

	public static void supprimerLiberter(Case cases,Case supprimer){
		cases.liberte.remove(supprimer);

	}

	public static void ajouterLiberter(Case cases,Case ajouter){
		cases.liberte.add(ajouter);

	}

	public int sizeLiberter(Case[][] plat,LinkedList<Case> visiter,int size){
		int sizes = 0;
		visiter.add(this);
		if (this.index != 0) {
			if(plat[this.index-1][this.indey].contenue == this.contenue && !visiter.contains(plat[this.index-1][this.indey])){
				sizes = sizes + plat[this.index-1][this.indey].sizeLiberter(plat,visiter,size);
			}
			
		}
	
		if (this.indey != 0) {
			if(plat[this.index][this.indey-1].contenue == this.contenue && !visiter.contains(plat[this.index][this.indey-1])){
				sizes = sizes + plat[this.index][this.indey-1].sizeLiberter(plat,visiter,size);
			}
			
			
		}
		if (this.index != size-1) {
			if(plat[this.index+1][this.indey].contenue == this.contenue && !visiter.contains(plat[this.index+1][this.indey])){
				sizes = sizes + plat[this.index+1][this.indey].sizeLiberter(plat,visiter,size);
			}
		
		
		}
		if (this.indey != size-1) {
			if(plat[this.index][this.indey+1].contenue == this.contenue && !visiter.contains(plat[this.index][this.indey+1])){
				sizes = sizes + plat[this.index][this.indey+1].sizeLiberter(plat,visiter,size);
			}
			
		
			
		}
	
		return sizes + this.liberte.size();

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

	public static void unsetLiberter(Case[][] plat,Case cases,int size){
		if (cases.index != 0) {
			if(plat[cases.index-1][cases.indey].contenue == 1 || plat[cases.index-1][cases.indey].contenue == 2){
				Case.ajouterLiberter(plat[cases.index-1][cases.indey],cases);
			}
		
		}
	
		if (cases.indey != 0) {
			if(plat[cases.index][cases.indey-1].contenue == 1 || plat[cases.index][cases.indey-1].contenue == 2){
				Case.ajouterLiberter(plat[cases.index][cases.indey-1],cases);
			}
		
			
		}
		if (cases.index != size-1) {
			if(plat[cases.index+1][cases.indey].contenue == 1 || plat[cases.index+1][cases.indey].contenue == 2){
				Case.ajouterLiberter(plat[cases.index+1][cases.indey],cases);
			}
		
		
		}
		if (cases.indey != size-1) {
			if(plat[cases.index][cases.indey+1].contenue == 1 || plat[cases.index][cases.indey+1].contenue == 2){
				Case.ajouterLiberter(plat[cases.index][cases.indey+1],cases);
			}
		
		
			
		}
	}

}