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
	public Image selection;
	public Plateau plateau;
	public boolean init;

	public Fenetre fenetre;
	public Score score;
	public int lastx;
	public int lasty;
	public Case lastj1;
	public Case lastj2;
	public int statut;
	public LinkedList<Case> groupeMort;
	public javax.swing.Timer timer;
	public int temps;
	public float scorej1;
	public float scorej2;
	
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
		selection = Toolkit.getDefaultToolkit().getImage("asset/selection"+fenetre.param.size+".png");
	
		plateau = new Plateau(fenetre);
		init = false;
		scorej2 = fenetre.param.handicap;
		if (fenetre.param.pionh != 0) {
			this.plateau.j=2;
			scorej2 = 0;
		}
		else{
			this.plateau.j=1;
		}
		this.statut=0; 
		lasty = 0;
		lastx = 0;
		scorej1 = 0;
		
		this.fenetre = fenetre;
		this.score = new Score(fenetre);
		this.groupeMort = new LinkedList<Case>();
		Plateau copy = score.copyPlateau(this.plateau);
		score.listModel.addElement(copy);
		score.scrolled();
		score.list.setSelectedIndex(0);
		
		temps = 60;
		if (fenetre.param.horloge == 1) {
			
			timer = new javax.swing.Timer(1000,new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					temps = temps-1;
					score.temps.setText("Temps : "+temps);
					if (temps == 0) {
						temps = 60;
						tempsdEcouler();

					}

		
					
				}
			});


			timer.start();
		}
		fenetre.repaint();
		fenetre.revalidate();

		
	

	}

	public void resetTimer(){
		temps = 60;
		score.temps.setText("Temps : "+temps);
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
				else if (plateau.plat[x][y].contenue == 3 && fenetre.panel.statut == 0) {
					g.drawImage(pionnt,plateau.plat[x][y].x,plateau.plat[x][y].y,this);				
				}
				else if (plateau.plat[x][y].contenue == 4 && fenetre.panel.statut == 0 ) {
					g.drawImage(pionbt,plateau.plat[x][y].x,plateau.plat[x][y].y,this);				
				}
				if (plateau.plat[x][y].selection == 1) {
					g.drawImage(selection,plateau.plat[x][y].x,plateau.plat[x][y].y,this);
				}
			}
		}
	}

	public void selectionGroupeMort(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		int button = e.getButton();
		
		Case casepick = Case.searchCase(plateau.plat,x,y,fenetre.param.size);
		if (x>40 && y > 40 && x<900 && y < 900) {
				if (button == MouseEvent.BUTTON1 && casepick.selection == 0 &&(casepick.contenue==2  || casepick.contenue==1)) {
					
					casepick.selectionGroup(plateau.plat,fenetre.param.size,new LinkedList<Case>(),groupeMort);
					
				
				}
				else if (button == MouseEvent.BUTTON1 && casepick.selection == 1 &&(casepick.contenue==2  || casepick.contenue==1)) {
					
					casepick.deselectionGroup(plateau.plat,fenetre.param.size,new LinkedList<Case>(),groupeMort);
					
				
				}

			}
		this.repaint();


	}

	public void calculPoint(){
		for (int x =0;x<fenetre.param.size;x=x+1) {
			for (int y =0;y<fenetre.param.size;y=y+1) {
				if (plateau.plat[x][y].contenue == 1) {
					scorej1 = scorej1+1;
				}
				else if (plateau.plat[x][y].contenue == 2) {
					scorej2 = scorej2+1;
				}
			}
		}
		this.statut = 4;
		score.add(new JLabel("Score J1 : "+ scorej1));
		score.add(new JLabel("Score J2 : "+ scorej2));
		score.add(Box.createRigidArea(new Dimension(10,10)));
		if (scorej2 > scorej1) {
			score.add(new JLabel("Joueur 2 gagne"));
		}
		else if (scorej1 > scorej2) {
			score.add(new JLabel("Joueur 1 gagne"));
		}
		else if (scorej1 == scorej2) {
			score.add(new JLabel("Egalite"));
		}
		score.add(Box.createRigidArea(new Dimension(10,10)));
		score.add(score.quitter);
	}

	public void rempli(){
		for (int x = 0;x<fenetre.param.size;x=x+1) {
			for (int y = 0;y<fenetre.param.size;y=y+1) {
				plateau.plat[x][y].entourer(plateau.plat,fenetre.param.size);
			}
		}
		repaint();
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
		Plateau copy = this.score.copyPlateau(this.plateau);
		this.score.listModel.addElement(copy);
		this.score.scrolled();
		this.score.list.setSelectedIndex(this.score.list.getSelectedIndex()+1);
		if (this.plateau.nbPasser == 2) {
			this.finDePartie();			
		}
	}

	public void supprimerGroupMort(){
		Iterator<Case> it = this.groupeMort.iterator();
		Case c;
		while(it.hasNext()){
			c = it.next();
			if (c.contenue == 1) {
				score.scoreb = score.scoreb +1;
				
			}
			else if (c.contenue == 2) {
				score.scoren = score.scoren +1;
			}
			c.contenue = 0;
			c.selection = 0;
			it.remove();
		}
		this.score.scorenoir.setText("Prisonnier Pion Blanc : "+this.score.scoren);
		this.score.scoreblanc.setText("Prisonnier Pion Noir : "+this.score.scoreb);
		this.repaint();
		rempli();
		calculPoint();
	}

	public void finDePartie(){
		


		this.statut = 3;
		score.add(Box.createRigidArea(new Dimension(10,10)));
		score.add(score.group);
		score.repaint();
		score.revalidate();
		this.repaint();
		if (fenetre.param.horloge == 1) {
			resetTimer();
			timer.stop();
		}
		
		



	}

	public void abandonner(){
		this.statut = 7;
		if (this.plateau.j == 1) {
			score.add(new JLabel("Joueur 2 gagne"));
		}
		else if (this.plateau.j == 2) {
			score.add(new JLabel("Joueur 1 gagne"));
		}
		if (fenetre.param.horloge == 1) {
			resetTimer();
			timer.stop();
		}
		score.add(Box.createRigidArea(new Dimension(10,10)));
		score.add(score.quitter);
		score.repaint();
		score.revalidate();
		fenetre.repaint();
	}

	public void tempsdEcouler(){
		

		this.statut = 7;
		if (this.plateau.j == 2) {
			score.add(new JLabel("Joueur 1 gagne"));
		}
		else if (this.plateau.j == 1){
			score.add(new JLabel("Joueur 2 gagne"));
		}
		score.add(Box.createRigidArea(new Dimension(10,10)));
		score.add(score.quitter);
		score.repaint();
		score.revalidate();
		this.repaint();
		if (fenetre.param.horloge == 1) {
			resetTimer();
			timer.stop();
		}
		this.score.scorenoir.setText("Prisonnier Pion Blanc : "+this.score.scoren);
		this.score.scoreblanc.setText("Prisonnier Pion Noir : "+this.score.scoreb);




	}

	public void swapPlateau(Plateau historique){
		this.plateau = score.copyPlateau(historique);
		this.score.scoren = this.plateau.scoren;
		this.score.scoreb = this.plateau.scoreb;
		this.score.scorenoir.setText("Prisonnier Pion Blanc : "+this.score.scoren);
		this.score.scoreblanc.setText("Prisonnier Pion Noir : "+this.score.scoreb);

		if (fenetre.param.horloge == 1) {
			resetTimer();
			timer.stop();
		}
		this.repaint();

	}

	public void placerPion(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		int button = e.getButton();
	
		
		Case casepick = Case.searchCase(plateau.plat,x,y,fenetre.param.size);

		if (casepick != null) {
	
			
		
			if (x>40 && y > 40 && x<900 && y < 900) {
				if (button == MouseEvent.BUTTON1 && this.plateau.j == 1 && casepick.contenue!=2  && casepick.contenue!=1) {
					if (score.list.getSelectedIndex()+1 < score.listModel.size()) {
						score.listModel.removeRange(score.list.getSelectedIndex()+1,score.listModel.size()-1);
						if (fenetre.param.horloge == 1) {
							timer.start();
						}
						
					}
					
					casepick.contenue=1;
					Case.setLiberter(this.plateau.plat,casepick,fenetre.param.size);
					this.plateau.j=2;
					Case.checkLiberter(this.plateau.plat,fenetre.param.size,casepick,this);
				
				}
				else if (button == MouseEvent.BUTTON1 && this.plateau.j == 2 && casepick.contenue!=1  && casepick.contenue!=2) {
					if (score.list.getSelectedIndex()+1 < score.listModel.size()) {
						score.listModel.removeRange(score.list.getSelectedIndex()+1,score.listModel.size()-1);
						if (fenetre.param.horloge == 1) {
							timer.start();
						}
					}

					casepick.contenue=2;
					Case.setLiberter(this.plateau.plat,casepick,fenetre.param.size);
					
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