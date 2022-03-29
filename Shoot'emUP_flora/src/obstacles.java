import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class obstacles {
	private float xObs,yObs,vitesseObs;//coordonnées du centre et vitesse
	private Image imgObs;

	public obstacles(float x, float y, float vitesse) throws SlickException {
		if(x>=0 && y>=0 && x<1500 && y<1000) {
			this.xObs = x;
			this.yObs = y;
			this.vitesseObs = vitesse;
		} else {
			this.xObs = 0;
			this.yObs = 0;
			this.vitesseObs = 0;
		}
		imgObs = new Image("mesImages/bouleDeFeu.png");
	}
//dessiner les obstacles
	public void dessiner(Graphics g) throws SlickException {
		g.drawImage(imgObs, xObs, yObs);
	}
//lancer les obstacles sur le vaisseau
	public void lancerObstacles(int delta) {
		float distance = vitesseObs * delta/1000f;
		yObs = yObs + 2 - distance;
	}
	
	public boolean horsEcran() {
		if (yObs <= 0)
			return true;
		else
			return false;
	}
	//Et enfin, une methode colllision dans le cas ou le projectile rencontre le vaisseau
	public boolean testCollision(Vaisseau v) {
		if(this.xObs >= v.getX()-90 && this.xObs <= v.getX()+90 && this.yObs >= v.getY()-90 && this.yObs <= v.getY()+90)//les coordonnees du projectile doivent etre comprises dans les dimensions du vaisseau
			return true;
		else
			return false;
	}
	public float getxObs() {
		return xObs;
	}
	public void setxObs(float xObs) {
		this.xObs = xObs;
	}
	public float getyObs() {
		return yObs;
	}
	public void setyObs(float yObs) {
		this.yObs = yObs;
	}
	

}
