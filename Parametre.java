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
	public Fenetre fenetre;
	public int size;

	Parametre(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(fenetre);
		goban9=new JButton("Goban 9x9");
		goban13=new JButton("Goban 13x13");
		goban19=new JButton("Goban 19x19");
		
		goban9.addActionListener(fenetre);
		goban13.addActionListener(fenetre);
		goban19.addActionListener(fenetre);
		this.add(goban9,BorderLayout.CENTER);
		this.add(goban13,BorderLayout.CENTER);
		this.add(goban19,BorderLayout.CENTER);
		this.fenetre = fenetre;
		this.size = 9;


 

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



	}
}