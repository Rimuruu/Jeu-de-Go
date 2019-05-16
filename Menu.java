import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;



public class Menu extends JPanel{
	public JButton play;
	public JButton parametre;
	public Fenetre fenetre;

	Menu(Fenetre fenetre){
		super();
		this.setSize(1100,900);
		this.setLocation(0,0);
		addMouseListener(fenetre);
		play = new JButton("Jouer");
		parametre = new JButton("Parametre");
		play.addActionListener(fenetre);
		parametre.addActionListener(fenetre);
		this.add(play,BorderLayout.CENTER);
		this.add(parametre,BorderLayout.CENTER);
		this.fenetre = fenetre;


 

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
	
	}

	public void jouer(ActionEvent e){
		Object source = e.getSource();
		if(source == fenetre.menu.play){
			fenetre.panel = new Panel(fenetre);
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.panel);
			fenetre.repaint();
			fenetre.revalidate();
			
			
		} else if(source == fenetre.menu.parametre){
			fenetre.repaint();
			fenetre.revalidate();

			fenetre.setContentPane(fenetre.param);
			fenetre.repaint();
			fenetre.revalidate();
			
		}

	}



}