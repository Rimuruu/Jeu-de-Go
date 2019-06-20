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
	public int selection;
	public LinkedList<Case> liberte;
	public Panel panel;
	public int voisinn;
	public int voisinb;
	Case(int x,int y,int index,int indey,Panel panel){
		this.x = x;
		this.y = y;
		this.index = index;
		this.indey = indey;
		this.liberte = new LinkedList<Case>();
		this.contenue = 0;
		this.panel = panel;
		this.selection = 0;
		this.voisinn = 0;
		this.voisinb = 0;
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

public int searchVoisinn(Case[][] plat,int size,LinkedList<Case> visiter){
	int voisin = 0;
	visiter.add(this);
	if (this.index != 0) {
		if (plat[this.index-1][this.indey].contenue == 1) {
			voisin = voisin+1;
		}
	}
	if (this.index != size-1) {
		if (plat[this.index+1][this.indey].contenue == 1) {
			voisin = voisin+1;
		}
	}
	if (this.indey != 0) {
		if (plat[this.index][this.indey-1].contenue == 1) {
			voisin = voisin+1;
		}
	}
	if (this.indey != size-1) {
		if (plat[this.index][this.indey+1].contenue == 1) {
			voisin = voisin+1;
		}
	} 


	if (this.index != 0) {
		if (plat[this.index-1][this.indey].contenue == 0 && !visiter.contains(plat[this.index-1][this.indey])) {
			voisin = voisin+plat[this.index-1][this.indey].searchVoisinn(plat,size,visiter);
		}
	}
	if (this.index != size-1) {
		if (plat[this.index+1][this.indey].contenue == 0 && !visiter.contains(plat[this.index+1][this.indey])) {
			voisin = voisin+plat[this.index+1][this.indey].searchVoisinn(plat,size,visiter);
		}
	}
	if (this.indey != 0) {
		if (plat[this.index][this.indey-1].contenue == 0 && !visiter.contains(plat[this.index][this.indey-1])) {
			voisin = voisin+plat[this.index][this.indey-1].searchVoisinn(plat,size,visiter);
		}
	}
	if (this.indey != size-1) {
		if (plat[this.index][this.indey+1].contenue == 0 &&  !visiter.contains(plat[this.index][this.indey+1])) {
			voisin = voisin+plat[this.index][this.indey+1].searchVoisinn(plat,size,visiter);
		}
	}
	return voisin;

}

public int searchVoisinb(Case[][] plat,int size,LinkedList<Case> visiter){
	int voisin = 0;
	visiter.add(this);
	if (this.index != 0) {
		if (plat[this.index-1][this.indey].contenue == 2) {
			voisin = voisin+1;
		}
	}
	if (this.index != size-1) {
		if (plat[this.index+1][this.indey].contenue == 2) {
			voisin = voisin+1;
		}
	}
	if (this.indey != 0) {
		if (plat[this.index][this.indey-1].contenue == 2) {
			voisin = voisin+1;
		}
	}
	if (this.indey != size-1) {
		if (plat[this.index][this.indey+1].contenue == 2) {
			voisin = voisin+1;
		}
	} 


	if (this.index != 0) {
		if (plat[this.index-1][this.indey].contenue == 0 && !visiter.contains(plat[this.index-1][this.indey])) {
			voisin = voisin+plat[this.index-1][this.indey].searchVoisinb(plat,size,visiter);
		}
	}
	if (this.index != size-1) {
		if (plat[this.index+1][this.indey].contenue == 0 && !visiter.contains(plat[this.index+1][this.indey])) {
			voisin = voisin+plat[this.index+1][this.indey].searchVoisinb(plat,size,visiter);
		}
	}
	if (this.indey != 0) {
		if (plat[this.index][this.indey-1].contenue == 0 && !visiter.contains(plat[this.index][this.indey-1])) {
			voisin = voisin+plat[this.index][this.indey-1].searchVoisinb(plat,size,visiter);
		}
	}
	if (this.indey != size-1) {
		if (plat[this.index][this.indey+1].contenue == 0 &&  !visiter.contains(plat[this.index][this.indey+1])) {
			voisin = voisin+plat[this.index][this.indey+1].searchVoisinb(plat,size,visiter);
		}
	}
	return voisin;

}

public void entourer(Case[][] plat,int size){
	int voisinn = this.searchVoisinn(plat,size,new LinkedList<Case>());
	int voisinb = this.searchVoisinb(plat,size,new LinkedList<Case>());
	if (voisinb == 0 && voisinn > 0) {
		this.contenue = 1;
	}
	else if (voisinn == 0 && voisinb >0) {
		this.contenue = 2;
	}
}

public void selectionGroup(Case[][] plat,int size,LinkedList<Case> visiter,LinkedList<Case> groupeMort){
	this.selection = 1;
	visiter.add(this);
	groupeMort.add(this);
	if (this.index != 0) {
		if (plat[this.index-1][this.indey].contenue == this.contenue && !visiter.contains(plat[this.index-1][this.indey])) {
			plat[this.index-1][this.indey].selectionGroup(plat,size,visiter,groupeMort);
		}
	}
	if (this.index != size-1) {
		if (plat[this.index+1][this.indey].contenue == this.contenue && !visiter.contains(plat[this.index+1][this.indey])) {
			plat[this.index+1][this.indey].selectionGroup(plat,size,visiter,groupeMort);
		}
	}
	if (this.indey != 0) {
		if (plat[this.index][this.indey-1].contenue == this.contenue && !visiter.contains(plat[this.index][this.indey-1])) {
			plat[this.index][this.indey-1].selectionGroup(plat,size,visiter,groupeMort);
		}
	}
	if (this.indey != size-1) {
		if (plat[this.index][this.indey+1].contenue == this.contenue&& !visiter.contains(plat[this.index][this.indey+1])) {
			plat[this.index][this.indey+1].selectionGroup(plat,size,visiter,groupeMort);
		}
	}

}

public void deselectionGroup(Case[][] plat,int size,LinkedList<Case> visiter,LinkedList<Case> groupeMort){
	this.selection = 0;
	visiter.add(this);
	groupeMort.remove(this);
	if (this.index != 0) {
		if (plat[this.index-1][this.indey].contenue == this.contenue && !visiter.contains(plat[this.index-1][this.indey])) {
			plat[this.index-1][this.indey].deselectionGroup(plat,size,visiter,groupeMort);
		}
	}
	if (this.index != size-1) {
		if (plat[this.index+1][this.indey].contenue == this.contenue && !visiter.contains(plat[this.index+1][this.indey])) {
			plat[this.index+1][this.indey].deselectionGroup(plat,size,visiter,groupeMort);
		}
	}
	if (this.indey != 0) {
		if (plat[this.index][this.indey-1].contenue == this.contenue && !visiter.contains(plat[this.index][this.indey-1])) {
			plat[this.index][this.indey-1].deselectionGroup(plat,size,visiter,groupeMort);
		}
	}
	if (this.indey != size-1) {
		if (plat[this.index][this.indey+1].contenue == this.contenue&& !visiter.contains(plat[this.index][this.indey+1])) {
			plat[this.index][this.indey+1].deselectionGroup(plat,size,visiter,groupeMort);
		}
	}

}

public float prise(Case[][] plat,int size){
	int a = 0;
	int b = this.contenue;
	float nbprise = 1;
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
				nbprise = nbprise + plat[this.index-1][this.indey].prise(plat,size);
			}
			
		}
	
	if (this.indey != 0) {
			if(plat[this.index][this.indey-1].contenue == a){
				Case.ajouterLiberter(plat[this.index][this.indey-1],this);	
			}
			else if(plat[this.index][this.indey-1].contenue == b){
				nbprise = nbprise + plat[this.index][this.indey-1].prise(plat,size);
			}
			
		}
	if (this.index != size-1) {
			if(plat[this.index+1][this.indey].contenue == a ){
				Case.ajouterLiberter(plat[this.index+1][this.indey],this);
			}
			else if(plat[this.index+1][this.indey].contenue == b){
				nbprise = nbprise + plat[this.index+1][this.indey].prise(plat,size);
			}
		
		}
	if (this.indey != size-1) {
			if(plat[this.index][this.indey+1].contenue == a){
				Case.ajouterLiberter(plat[this.index][this.indey+1],this);
			}
			else if(plat[this.index][this.indey+1].contenue == b){
				nbprise = nbprise + plat[this.index][this.indey+1].prise(plat,size);
			}
			
		
			
		}

		this.liberte.clear();
		return nbprise;
		


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
		Plateau copy;
		int ko = 0;
		float nbprise = 0;
		if (cases.contenue == 1) {
			a = 2;
			lastCase = panel.plateau.lastj2;
		}
		else{
			a =1;
			lastCase = panel.plateau.lastj1;
	}
		//System.out.println(Plateau.contient(panel.score.listModel,panel.plateau));
		System.out.println(cases.sizeLiberter(plat,new LinkedList<Case>(),size));
		LinkedList<Case> capture = cases.checkCaseCapture(plat,size);
		if (cases.sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && cases.checkCapture(plat,size) == false) {
			panel.plateau.j = b;
			cases.contenue = 0;
			Case.unsetLiberter(plat,cases,size);
			cases.liberte.clear();
			System.out.println("Suicide Interdit");

			

		}
		
		else if (cases.sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && capture.size() == 1 && capture.contains(lastCase)==true) {
			panel.plateau.j = b;
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
				panel.plateau.lastj1=cases;
		}
			else{
			
				panel.plateau.lastj2=cases;
		}
			if (cases.index != 0) {
				if (plat[cases.index-1][cases.indey].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index-1][cases.indey].contenue == a) {
					nbprise = nbprise + plat[cases.index-1][cases.indey].prise(plat,size);
				}
		
			}
		
			if (cases.indey != 0) {
				if (plat[cases.index][cases.indey-1].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index][cases.indey-1].contenue == a) {
					nbprise = nbprise + plat[cases.index][cases.indey-1].prise(plat,size);
				}
		
					
				}
			if (cases.index != size-1) {
				if (plat[cases.index+1][cases.indey].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index+1][cases.indey].contenue == a) {
					nbprise = nbprise + plat[cases.index+1][cases.indey].prise(plat,size);
				}
				
				
				}
			if (cases.indey != size-1) {
				if (plat[cases.index][cases.indey+1].sizeLiberter(plat,new LinkedList<Case>(),size) == 0 && plat[cases.index][cases.indey+1].contenue == a) {
					nbprise = nbprise + plat[cases.index][cases.indey+1].prise(plat,size);
				}
				
					
				
					
				}
			ko = Plateau.contient(panel.score.listModel,panel.plateau);
			if(ko >=3){
				System.out.println("3 Configuration deja rencontre coup annule");
				panel.swapPlateau(panel.score.listModel.getElementAt(panel.score.list.getSelectedIndex()));
				nbprise = 0;
				
			}
			else if (ko >=0) {
				panel.plateau.nbPasser = 0;
				if (a == 2) {
					panel.score.scoren = panel.score.scoren + nbprise;
					panel.plateau.scoren = panel.score.scoren; 
					panel.score.scorenoir.setText("Prisonnier Pion Blanc : "+panel.score.scoren);
					if (panel.fenetre.param.horloge == 1) {
						panel.resetTimer();
					}
			
				}
				else{
					panel.score.scoreb = panel.score.scoreb + nbprise;
					panel.plateau.scoreb = panel.score.scoreb;
					panel.score.scoreblanc.setText("Prisonnier Pion Noir : "+panel.score.scoreb);
					if (panel.fenetre.param.horloge == 1) {
						panel.resetTimer();
					}
				
				
				}

				System.out.println("Nombre de configuration deja rencontre " + ko);
				copy = panel.score.copyPlateau(panel.plateau);
				panel.score.listModel.addElement(copy);
				panel.score.scrolled();
				panel.score.list.setSelectedIndex(panel.score.list.getSelectedIndex()+1);

			}
			else{
				panel.plateau.nbPasser = 0;
				if (a == 2) {
					panel.score.scoren = panel.score.scoren + nbprise;
					panel.plateau.scoren = panel.score.scoren; 
					panel.score.scorenoir.setText("Prisonnier Pion Blanc : "+panel.score.scoren);
			
				}
				else{
					panel.score.scoreb = panel.score.scoreb + nbprise;
					panel.plateau.scoreb = panel.score.scoreb;
					panel.score.scoreblanc.setText("Prisonnier Pion Noir : "+panel.score.scoreb);
				
				
				}

				copy = panel.score.copyPlateau(panel.plateau);
				panel.score.listModel.addElement(copy);
				panel.score.scrolled();
				panel.score.list.setSelectedIndex(panel.score.list.getSelectedIndex()+1);
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