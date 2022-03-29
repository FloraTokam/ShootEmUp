import java.util.ArrayList;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ennemis {
	private float x,y;//coin sup gauche
	private Image image;
	private float vitesse;

	private ArrayList<Ennemis> e = new ArrayList<Ennemis>();
	
//je construis mes ennemis
	public Ennemis(float x, float y,Image image,float vitesse) {
		if(x>=0 && x<1500 && y>=0 && y<1000) {
			this.x=x;
			this.y=y;
		}
		this.image=image;
		this.vitesse=vitesse;
	}
	
	//Ensuite je les dessine les ennemis1
		public void dessiner(Graphics g) throws SlickException {
			image = new Image("mesImages/ennemi1.png");
			g.drawImage(image, x, y);
		}
		//Ensuite je les dessine les ennemis2
		public void dessiner2(Graphics g) throws SlickException {
			image = new Image("mesImages/ennemifav2.png");
			g.drawImage(image, x, y);
		}
		//Ensuite je les dessine les ennemis3
		public void dessiner3(Graphics g) throws SlickException {
			image = new Image("mesImages/ennemifav3.png");
			g.drawImage(image, x, y);
		}
	//Apres, je les deplace
		public void deplacer(int delta) {
			float distance = vitesse * delta / 1000f;
			y = y - distance;
		}
		public void deplacer2(int delta) {
			float distance = vitesse * delta / 1000f;
			x = x - 2;
			y = y - distance/2;
		} 
		public void deplacer3(int delta) {
			float distance = vitesse * delta / 1000f;
			x = x + 2.3f;
			y = y - distance/4.5f;
			
			
		}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		if(x>=0 && x<1500)
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		if(y>=0 && y<1000)
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	

}
