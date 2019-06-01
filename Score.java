import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Score extends JPanel{
	public Fenetre fenetre;
	public LinkedList<Case[][]> historique;
	Score(Fenetre fenetre){
		super();
		this.setSize(300,900);
		this.setLocation(900,0);
		//addMouseListener(fenetre);
		//addMouseMotionListener(fenetre);
		setBackground(Color.BLACK);
		this.fenetre = fenetre;
	

	}

	/*public Case[][] copyPlateau(Case[][] plat){
		Case[][] copy = new Case[fenetre.param.size][fenetre.param.size];
		for (int i = 0;i<fenetre.param.size;i=i+1) {
			for (int y = 0;y<fenetre.param.size;y=y+1) {
				copy[i][y] = new Case(plat[i][y])
			}	
		}

	}*/

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		

	
	}


}