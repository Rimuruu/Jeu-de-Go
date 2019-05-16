import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;


public class Fenetre extends JFrame{
	public Menu menu;
	public Panel panel;
	Fenetre(){
		this.setSize(1100,928);
	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLocation(300,200);
		this.setLayout(null);
		this.setVisible(true);
		this.setResizable(false);
		/*menu = new Menu(this);
		this.add(menu);*/
		this.panel = new Panel(this);
		this.add(panel);

	}
}