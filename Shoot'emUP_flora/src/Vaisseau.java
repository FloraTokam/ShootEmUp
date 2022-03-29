import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Vaisseau {
	private float x,y;//coordonnées du coin sup gauche de mon vaisseau
	private Image image;
	

//constructeur de mon vaisseau, je choisi de l'initialiser au centre de la fenetre qui a pour dimension 1500*1000
	public Vaisseau() throws SlickException {
		this.x=650;
		this.y=800;
		this.image=new Image("mesImages/lanceur.png");
	}
	
//ensuite je dessine mon vaisseau
	public void dessiner(Graphics g) {
		g.drawImage(image, x-90, y-90);
	}
	
//mon vaisseau peut se deplacer a gauche et a droite de 9px
	public void gauche() {
		if(x-90-9>0) 
			x=x-9;
	}
	public void droite() {
		if(x-90+9<(1500-180)) //centre du projectile+distance entre le centre le bord de la fenetre qui=90
			x=x+9;
	}
	//ce constructeur agit lorsque la souris est maintenue appuyée
	public void gauche(int a) {
		if(x-90-a>0) 
			x=x-a;
	}
	public void droite(int a) {
		if(x-90+a<(1500-180)) 
			x=a+x;
	}
	public void haut() {
		if(y-90-10>0) 
			y=y-10;
	}
	public void tresHaut(int a) {
		if(y-90-a>0) 
			y=y-a;
	}
	public void bas() {
		if(y-90+10<1000-180) 
			y=y+10;
	}
	public void tresBas(int a) {
		if(y-90+a<1000-180) 
			y=y+a;
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
