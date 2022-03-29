import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class MainShoot {

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new ShootEmUP("ShootEmUp by_Flora"));
		app.setShowFPS(false);//on affcihe le nbre d'images par secondes
		app.setDisplayMode(1500, 1000, false);//definir la dimension de la fenetre de mon,largeur,hauteur et fullscreen=false
		app.start();


	}

}
