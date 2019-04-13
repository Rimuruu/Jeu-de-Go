import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;

public class Panel extends JPanel{
	private Image goban;
	private Image goban2;
	private Image goban3;
	Panel(){
		goban = Toolkit.getDefaultToolkit().getImage("goban19.png");
		goban2 = Toolkit.getDefaultToolkit().getImage("goban9.png");

	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(goban,0,0,this);
		
		g.drawImage(goban2,0,0,this);
	}
}