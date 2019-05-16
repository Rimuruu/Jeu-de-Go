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
	public int[][] plateau;
	public boolean init;
	public int j;
	public Fenetre fenetre;
	public int lastx;
	public int lasty;
	Panel(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(fenetre);
		addMouseMotionListener(fenetre);

		goban = Toolkit.getDefaultToolkit().getImage("goban"+fenetre.param.size+".png");
		pionn = Toolkit.getDefaultToolkit().getImage("pionnoir"+fenetre.param.size+".png");
		pionnt = Toolkit.getDefaultToolkit().getImage("pionnoir"+fenetre.param.size+"t.png");
		pionbt = Toolkit.getDefaultToolkit().getImage("pionblanc"+fenetre.param.size+"t.png");
		pionb = Toolkit.getDefaultToolkit().getImage("pionblanc"+fenetre.param.size+".png");
		plateau = new int[fenetre.param.size][fenetre.param.size];
		init = false;
		j=1;
		lasty = 0;
		lastx = 0;
		this.fenetre = fenetre;

		
	

	}

	public void posePion(Graphics g){
		for (int x = 0,pox = 69-46 ;x<9;x=x+1,pox=pox+95) {
			for (int y = 0,poy = 69-46 ;y<9;y=y+1,poy=poy+95) {
				if (plateau[x][y] == 1) {
					g.drawImage(pionn,pox,poy,this);				
				}
				else if (plateau[x][y] == 2) {
					g.drawImage(pionb,pox,poy,this);				
				}
				else if (plateau[x][y] == 3) {
					g.drawImage(pionnt,pox,poy,this);				
				}
				else if (plateau[x][y] == 4) {
					g.drawImage(pionbt,pox,poy,this);				
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

	public void placerPion(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		int button = e.getButton();
		System.out.println("x "+x+" y "+y);
		if (x>40 && y > 40 && x<850 && y < 850) {
			if (button == MouseEvent.BUTTON1 && this.j == 1 && this.plateau[x/100][y/100]!=2  && this.plateau[x/100][y/100]!=1) {
				this.plateau[x/100][y/100]=1;
				this.j=2;
			
			}
			else if (button == MouseEvent.BUTTON1 && this.j == 2 && this.plateau[x/100][y/100]!=1  && this.plateau[x/100][y/100]!=2) {
				this.plateau[x/100][y/100]=2;
				this.j=1;
			
			}
	}
		
		
		this.repaint(); 
	}

	public void mouseOver(MouseEvent e){
		int y = e.getY();
		int x = e.getX();
		if (x>40 && y > 40 && x<850 && y < 850) {
			if (this.j == 1 && this.plateau[x/100][y/100]!=2  && this.plateau[x/100][y/100]!=1) {
				if (lastx != 0 && lasty != 0) {
					
						
						
						if (this.plateau[lastx/100][lasty/100]!=1  && this.plateau[lastx/100][lasty/100]!=2) {
							this.plateau[lastx/100][lasty/100]=0;
						
						}
							
						
						this.plateau[x/100][y/100]=3;
						//System.out.println("Lastx "+lastx+" Lasty "+lasty);
						//System.out.println("x "+x+" y "+y);
						
					
					
				}
				
				lastx = x;
				lasty = y;

		
			}
			else if (this.j == 2 && this.plateau[x/100][y/100]!=1  && this.plateau[x/100][y/100]!=2) {
				if (lastx != 0 && lasty != 0) {
				
						if (this.plateau[lastx/100][lasty/100]!=1  && this.plateau[lastx/100][lasty/100]!=2) {
							this.plateau[lastx/100][lasty/100]=0;
										}
						
						this.plateau[x/100][y/100]=4;
						
					
				}
			
				lastx = x;
				lasty = y;
		
			}
			
		}
		this.repaint(); 

	}



}