import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Score extends JPanel{
	public Fenetre fenetre;
	Score(Fenetre fenetre){
		super();
		this.setSize(300,900);
		this.setLocation(900,0);
		//addMouseListener(fenetre);
		//addMouseMotionListener(fenetre);
		setBackground(Color.BLACK);
		this.fenetre = fenetre;
	

	}

		@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		

	
	}


}