import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class Plateau{
	public Case[][] plat;
	public int j;
	public Case lastj1;
	public Case lastj2;

	Plateau(Fenetre fenetre){
		this.plat = new Case[fenetre.param.size][fenetre.param.size];
		Panel.setCase(plat,fenetre.param.size,fenetre.panel);
	}
}