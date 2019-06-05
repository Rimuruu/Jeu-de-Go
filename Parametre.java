import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;



public class Parametre extends JPanel{
	public JButton goban9;
	public JButton goban13;
	public JButton goban19;
	public JButton handi;
	public Fenetre fenetre;
	public int size;
	public float handicap;

	Parametre(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(fenetre);
		goban9=new JButton("Goban 9x9");
		goban13=new JButton("Goban 13x13");
		goban19=new JButton("Goban 19x19");
		handi=new JButton("Handicape");
		
		goban9.addActionListener(fenetre);
		goban13.addActionListener(fenetre);
		goban19.addActionListener(fenetre);
		handi.addActionListener(fenetre);
		this.add(goban9,BorderLayout.CENTER);
		this.add(goban13,BorderLayout.CENTER);
		this.add(goban19,BorderLayout.CENTER);
		this.add(handi,BorderLayout.CENTER);
		this.fenetre = fenetre;
		this.size = 9;
		this.handicap = 0f;


 

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
	}

	public void select(ActionEvent e){
		Object source = e.getSource();
		if(source == this.goban9){
			this.size = 9;
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
			
	

	}
		else if(source == this.goban13){
			this.size = 13;
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
			
	

	}
		else if(source == this.goban19){
			this.size = 19;
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.menu);
			fenetre.repaint();
			fenetre.revalidate();
			
	

	}
		else if (source == this.handi) {
			if (this.handicap == 0f) {
				this.handicap = 7.5f;
				this.handi.setBorderPainted(false);
				this.handi.setFocusPainted(false);
				this.handi.setContentAreaFilled(false);

			}
			else{
				this.handicap = 0f;
				this.handi.setBorderPainted(true);
				this.handi.setFocusPainted(true);
				this.handi.setContentAreaFilled(true);
			}
			
		}



	}
}