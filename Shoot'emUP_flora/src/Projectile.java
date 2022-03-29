import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Projectile {
	private float x,y;//coordonnées du centre du projectile
	private int vitesse;
	private Image image;

	//constructeur
	public Projectile(float x, float y, int vitesse) throws SlickException {
		if(x>=0 && y>=0 && x<1500 && y<1000) {
			this.x = x;
			this.y = y;
			this.vitesse = vitesse;
		} else {
			this.x = 0;
			this.y = 0;
			this.vitesse = 0;
		}
		image = new Image("mesImages/missileAntiEnnemi6.png");
	}
	
	//on dessine le projectiles
	public void dessiner(Graphics g) {
		g.drawImage(image, x-20, y-20);//x et y sont le centre du projectile
	}
	
	//on deplace le projectile
	public void deplacer(int delta) {// le projectile se deplace, donc c'est la coordonnée y qui va varier
		// vitesse = distance/temps
		float distance = vitesse * delta/1000f;// on divise par 1000f car delta est exprimé en ms et la division n'est pas
											// entieres
		y = y + distance;

	}
	
	//on ajoute une methode qui indique si le projectile est hors de l'ecran
	
	/* le haut de la fenetre a pour equation y=0 quand le projectile passe au dessus
	 * du haute de la fenetre, ça veut dire que son centre a passe la coordonnée y=0
	 */
	public boolean horsEcran() {
		if (y <= 0)
			return true;
		else
			return false;
	}
	
	//Et enfin, une methode colllision dans le cas ou le projectile rencontre un ennemi
		public boolean testCollision(Ennemis e) {
			if(this.x >= e.getX() && this.x <= e.getX()+170 && this.y >= e.getY() && this.y <= e.getY()+170)//les coordonnees du projectile doivent etre comprises dans les dimensions de l'ennemi
				return true;
			else
				return false;
		}
	

}
