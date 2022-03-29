import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

import java.awt.Font;

public class Menu {
	public static int var=0;
	private Image imgmenu, imgButton1, imgButton2,welcom;
	
	public Menu() throws SlickException {
		this.imgmenu=new Image("mesImages/fondfav.png");
		this.imgButton1=new Image("mesImages/startGame.png");
		this.imgButton2=new Image("mesImages/exitGame.png");
		this.welcom=new Image("mesImages/welcomefav.png");
	}
	public void dessiner(Graphics g) {
//		java.awt.Font font=new Font("Verdana", Font.PLAIN,50);
//		TrueTypeFont ttf=new TrueTypeFont(font,true);

		
		imgmenu.draw(0, 0, 1500, 1000);
		welcom.draw(0, 0, 1300, 300);
		g.drawImage(imgButton1, 1500/3, 400);
		g.drawImage(imgButton2, 1500/3, 600);
		//g.setColor(Color.green);
		//g.drawString("MENU", 1500/3, 100);	
	}
	public int clicButton(int x, int y) {
		int ici=-1;
			if(y>400 && y<=400+139 && x>1500/3 && x<1500/3+320) 
				ici=0;
			if(y>=600 && y<=600+153 && x>1500/3 && x<1500/3+320) 
				ici=1;	
		
		return ici;
	}
	//selection permet de passer d'une fentre a l'autre
	public void selection(int x, int y) {
		int n=clicButton(x,y);
		if(n!=-1) {
			var=n+1;
		}
	}
}
