import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Panel extends JPanel{
	public Image goban;

	public Image pionn;
	public Image pionnt;
	public Image pionbt;
	public Image pionb;
	public Plateau plateau;
	public boolean init;

	public Fenetre fenetre;
	public Score score;
	public int lastx;
	public int lasty;
	public Case lastj1;
	public Case lastj2;
	
	Panel(Fenetre fenetre){
		super();
		this.setSize(900,900);
		this.setLocation(0,0);
		
		addMouseListener(fenetre);
		addMouseMotionListener(fenetre);

		goban = Toolkit.getDefaultToolkit().getImage("asset/goban"+fenetre.param.size+".png");
		pionn = Toolkit.getDefaultToolkit().getImage("asset/pionnoir"+fenetre.param.size+".png");
		pionnt = Toolkit.getDefaultToolkit().getImage("asset/pionnoir"+fenetre.param.size+"t.png");
		pionbt = Toolkit.getDefaultToolkit().getImage("asset/pionblanc"+fenetre.param.size+"t.png");
		pionb = Toolkit.getDefaultToolkit().getImage("asset/pionblanc"+fenetre.param.size+".png");
	
		plateau = new Plateau(fenetre);
		init = false;
		if (fenetre.param.pionh != 0) {
			this.plateau.j=2;
		}
		else{
			this.plateau.j=1;
		}
		
		lasty = 0;
		lastx = 0;
		this.fenetre = fenetre;
		this.score = new Score(fenetre);
		Plateau copy = score.copyPlateau(this.plateau);
		score.listModel.addElement(copy);
		score.scrolled();
		score.list.setSelectedIndex(0);
		
		
		fenetre.repaint();
		fenetre.revalidate();

		
	

	}

	public static void setCase(Case[][] plat,int size,Panel panel){
		if (size == 9) {
			for (int x = 0,pox = 69-46 ;x<9;x=x+1,pox=pox+95) {
				for (int y = 0,poy = 69-46 ;y<9;y=y+1,poy=poy+95) {
					plat[x][y] = new Case(pox,poy,x,y,panel);
					
				}
			}

			
		}
		else if (size == 19) {
			for (int x = 0,pox = 45-22 ;x<19;x=x+1,pox=pox+45) {
				for (int y = 0,poy = 45-22 ;y<19;y=y+1,poy=poy+45) {
					plat[x][y] = new Case(pox,poy,x,y,panel);
					
				}
			}

			
		}
		else if (size == 13) {
			for (int x = 0,pox = 68-32 ;x<13;x=x+1,pox=pox+64) {
				for (int y = 0,poy = 68-32 ;y<13;y=y+1,poy=poy+64) {
					plat[x][y] = new Case(pox,poy,x,y,panel);
					
				}
			}

			
		}

	}

	public void posePion(Graphics g){
		for (int x = 0 ;x<fenetre.param.size;x=x+1) {
			for (int y = 0 ;y<fenetre.param.size;y=y+1) {
				if (plateau.plat[x][y].contenue == 1) {
					g.drawImage(pionn,plateau.plat[x][y].x,plateau.plat[x][y].y,this);				
				}
				else if (plateau.plat[x][y].contenue == 2) {
					g.drawImage(pionb,plateau.plat[x][y].x,plateau.plat[x][y].y,this);				
				}
				else if (plateau.plat[x][y].contenue == 3) {
					g.drawImage(pionnt,plateau.plat[x][y].x,plateau.plat[x][y].y,this);				
				}
				else if (plateau.plat[x][y].contenue == 4) {
					g.drawImage(pionbt,plateau.plat[x][y].x,plateau.plat[x][y].y,this);				
				}
			}
		}
	}



	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(goban,0,0,this);
		posePion(g);
		
	}

	public void passerTour(){
		if (this.plateau.j == 1) {
			this.plateau.j = 2;
		}
		else if (this.plateau.j == 2) {
			this.plateau.j = 1;
		}
		if (score.list.getSelectedIndex()+1 < score.listModel.size()) {
			score.listModel.removeRange(score.list.getSelectedIndex()+1,score.listModel.size()-1);
		}
		this.plateau.nbPasser = this.plateau.nbPasser+1;
		System.out.println("Nombre de tour passer "+this.plateau.nbPasser);
		Plateau copy = this.score.copyPlateau(this.plateau);
		this.score.listModel.addElement(copy);
		this.score.scrolled();
		this.score.list.setSelectedIndex(this.score.list.getSelectedIndex()+1);
		if (this.plateau.nbPasser == 3) {
			this.finDePartie();			
		}
	}

	public void finDePartie(){
		


		this.score.passe.removeActionListener(this.fenetre);
		removeMouseListener(this.fenetre);
		removeMouseMotionListener(this.fenetre);
		//removeMouseListener(this.fenetre);
		System.out.println("fin de la partie");


	}

	public void swapPlateau(Plateau historique){
		this.plateau = score.copyPlateau(historique);
		this.score.scoren = this.plateau.scoren;
		this.score.scoreb = this.plateau.scoreb;
		this.score.scorenoir.setText("Score Pion Noir : "+this.score.scoren);
		this.score.scoreblanc.setText("Score Pion Blanc : "+this.score.scoreb);
		this.repaint();

	}

	public void placerPion(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		int button = e.getButton();
		System.out.println("x "+x+" y "+y);
		
		Case casepick = Case.searchCase(plateau.plat,x,y,fenetre.param.size);

		if (casepick != null) {
			System.out.println("Check liberter "+casepick.sizeLiberter(this.plateau.plat,new LinkedList<Case>(),fenetre.param.size));
			
		
			if (x>40 && y > 40 && x<900 && y < 900) {
				if (button == MouseEvent.BUTTON1 && this.plateau.j == 1 && casepick.contenue!=2  && casepick.contenue!=1) {
					if (score.list.getSelectedIndex()+1 < score.listModel.size()) {
						score.listModel.removeRange(score.list.getSelectedIndex()+1,score.listModel.size()-1);
						
					}
					
					casepick.contenue=1;
					Case.setLiberter(this.plateau.plat,casepick,fenetre.param.size);
					System.out.println("Liberter : "+casepick.sizeLiberter(this.plateau.plat,new LinkedList<Case>(),fenetre.param.size));
					this.plateau.j=2;
					Case.checkLiberter(this.plateau.plat,fenetre.param.size,casepick,this);
				
				}
				else if (button == MouseEvent.BUTTON1 && this.plateau.j == 2 && casepick.contenue!=1  && casepick.contenue!=2) {
					if (score.list.getSelectedIndex()+1 < score.listModel.size()) {
						score.listModel.removeRange(score.list.getSelectedIndex()+1,score.listModel.size()-1);
						
					}

					casepick.contenue=2;
					Case.setLiberter(this.plateau.plat,casepick,fenetre.param.size);
					System.out.println("Liberter : "+casepick.sizeLiberter(this.plateau.plat,new LinkedList<Case>(),fenetre.param.size));
					this.plateau.j=1;
					Case.checkLiberter(this.plateau.plat,fenetre.param.size,casepick,this);
				
				}
			
		}
}
		
		
		this.repaint(); 
	}

	public void mouseOver(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		Case lastcase = Case.searchCase(plateau.plat,lastx,lasty,fenetre.param.size);
		Case casepick = Case.searchCase(plateau.plat,x,y,fenetre.param.size);
		if (casepick != null) {
			
		
		if (x>40 && y > 40 && x<900 && y < 900) {
			if (this.plateau.j == 1 && casepick.contenue!=2  && casepick.contenue!=1) {
				if (lastx != 0 && lasty != 0) {
					
						
						
						if (lastcase.contenue!=1  && lastcase.contenue!=2) {
							lastcase.contenue=0;
						
						}
							
						
						casepick.contenue=3;
						//System.out.println("Lastx "+lastx+" Lasty "+lasty);
						//System.out.println("x "+x+" y "+y);
						
					
					
				}
				
				lastx = x;
				lasty = y;

		
			}
			else if (this.plateau.j == 2 && casepick.contenue!=1  && casepick.contenue!=2) {
				if (lastx != 0 && lasty != 0) {
				
						if (lastcase.contenue!=1  && lastcase.contenue!=2) {
							lastcase.contenue=0;
										}
						
						casepick.contenue=4;
						
					
				}
			
				lastx = x;
				lasty = y;
		
			}
			
		}
		this.repaint(); 
	}

	}



}