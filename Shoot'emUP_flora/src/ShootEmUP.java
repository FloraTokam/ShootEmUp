import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ShootEmUP extends BasicGame {
    private Image fond;
	private Vaisseau v;
	private ArrayList<Projectile> p = new ArrayList<Projectile>();
	private ArrayList<Ennemis> e = new ArrayList<Ennemis>();
	private ArrayList<Ennemis> e2 = new ArrayList<Ennemis>();
	private ArrayList<Ennemis> e3 = new ArrayList<Ennemis>();
	private ArrayList<obstacles> o = new ArrayList<obstacles>();
	private Image image1,imgObstacle,gameOver;
	private float x1, y1;
	private float vx1, vy1;
	private int temps1 = 0,temps2 = 0,temps3 = 0, cpt=0,score=0;
	private Menu menu;
	private Music musique;
	private float t=0;

	public ShootEmUP(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if(Menu.var==0)
			menu.dessiner(g);
		 if(Menu.var==1) {
		g.drawImage(fond, 0,0);
		defilement(g, fond,t);
		// dessine le vaisseau
		v.dessiner(g);
		// dessine l'ennemi
		//g.drawImage(image1, x1, y1);	
		for(int i=0;i<e.size();i++) {
			e.get(i).dessiner(g);
		}
		for(int i=0;i<e2.size();i++) {
			if((e.get(i) != null) && (e2.get(i).getX() != e.get(i).getX()) && (e2.get(i).getY() != e.get(i).getY()))
			e2.get(i).dessiner2(g);
		}
		for(int i=0;i<e3.size();i++) {
			//if((e3.get(i).getX()!=e.get(i).getX()) && (e3.get(i).getY()!=e.get(i).getY()) && (e3.get(i).getX()!=e2.get(i).getX()) && (e3.get(i).getY()!=e2.get(i).getY()))
			e3.get(i).dessiner3(g);
		}
		//dessine les obstacles
		for(int j=0;j<o.size();j++) {
			o.get(j).dessiner(g);
		}

		// dessiner les projectiles
		for (int i = 0; i < p.size(); i++) {
			p.get(i).dessiner(g);
		}
		g.drawString("score:"+score, 0002, 003);
		}
		 if(cpt>2)
			gameOver.draw(0, 0, 1500, 1000);

		
	}
	

	@Override
	public void init(GameContainer gc) throws SlickException {
		menu=new Menu();
		fond = new Image("mesImages/fondfav3.png"); 
		gameOver=new Image("mesImages/game-over.jpg");
		musique = new Music("musics/en1kill.wav");
		musique.loop();
		v = new Vaisseau();

		// on initialise les ennemis
		gc.setTargetFrameRate(100);
		
//		x1 = (float) (Math.random() * (1500-170));
//		y1 = 0;//(float) (Math.random() * (200-170));
//		vy1 = 100;
//		vx1=0;
		

	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input inp = gc.getInput();
		System.out.println(Menu.var);
		if(Menu.var==0) {
			if((inp.isMousePressed(Input.MOUSE_LEFT_BUTTON))||(inp.isKeyPressed(Input.KEY_ENTER)))
				menu.selection(inp.getMouseX(),inp.getMouseY());	
		}
		if(Menu.var==1) { //on peut afficher le jeu
		temps1 += delta;	
		temps2 += delta;
		temps3 += delta;
		//deplacement du vaisseau
		if (inp.isKeyPressed(Input.KEY_LEFT))
			v.gauche();
		if (inp.isKeyPressed(Input.KEY_RIGHT))
			v.droite();
		if (inp.isKeyDown(Input.KEY_LEFT))
			v.gauche(15);
		if (inp.isKeyDown(Input.KEY_RIGHT))
			v.droite(15);
		if (inp.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
			v.gauche(15);
		if (inp.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON))
			v.droite(15);
		if(inp.isKeyPressed(Input.KEY_H )|| inp.isKeyPressed(Input.KEY_UP))
			v.haut();
		if (inp.isKeyDown(Input.KEY_UP))
			v.tresHaut(15);
		if(inp.isKeyPressed(Input.KEY_B )|| inp.isKeyPressed(Input.KEY_DOWN))
			v.bas();
		if (inp.isKeyDown(Input.KEY_DOWN))
			v.tresBas(15);
		//quitter le jeu
		if (inp.isKeyPressed(Input.KEY_ESCAPE))
			System.exit(0);
			
//on deplace les projectiles
		for (int i = 0; i < p.size(); i++) {
			p.get(i).deplacer(delta);
		}
		for (int i = 0; i < p.size(); i++) {
			if (p.get(i).horsEcran()) {
				p.remove(i);
				i--;// pour tester le projectile qui occupe la nouvelle case dont la valeur vient
					// d'etre supprimer car le contenu ne sera plus le meme
			}
		}
		for (int i = 0; i < o.size(); i++) {
			if (o.get(i).horsEcran()) {
				o.remove(i);
				i--;
			}
		}
//on teste la collision entre le projectile et un ennemi
		for (int i = 0; i < e.size(); i++) {
			for (int k = 0; k < p.size(); k++) {
				if (e.get(i) != null && p.get(k).testCollision(e.get(i))) {
					e.remove(i);
					p.remove(k);
					i--;
					score++;
					break;
				}
			}
		}
		for (int i = 0; i < e2.size(); i++) {
			for (int k = 0; k < p.size(); k++) {
				if (e2.get(i) != null && p.get(k).testCollision(e2.get(i))) {
					e2.remove(i);
					p.remove(k);
					i--;
					break;
				}
			}
		}
		for (int i = 0; i < e3.size(); i++) {
			for (int k = 0; k < p.size(); k++) {
				if (e3.get(i) != null && p.get(k).testCollision(e3.get(i))) {
					e3.remove(i);
					p.remove(k);
					i--;
					break;
				}
			}
		}
//tester la collision entre l'obstacle et le vaisseau
			for (int k = 0; k < o.size(); k++) {
				if (o.get(k).testCollision(v)) {
					cpt++;
					o.remove(k);
					k--;
				}
			}
			if(cpt>2) {
				e.remove(v);
				// gc.pause();
			}
//Enfin, je tire sur l'ennemi
		if (inp.isKeyPressed(Input.KEY_SPACE) && p.size() < 10) {
			p.add(new Projectile(v.getX(), v.getY()-20, -500));
		}
			// car il ne peut pas y avoir plus de 10 projectiles au meme instant
			//image1.rotate(10*delta);//modification de l'orientation de l'ennemi quand on appui sur espace
			// on ajoute un projectile a notre arrayList
		

		
		// ajouter des ennemis

		/* v=d/t 
		 * d=v*t 
		 * x=x+d  
		 * x=x+v*t 
		 * x=x+50*delta/1000f
		 */

		y1 = y1 + vy1 * delta / 1000f;
		if (temps1 > 2000) {
			temps1 = 0;
			e.add(new Ennemis((float) (Math.random() * (1500 - 110)), (float) 0.00001, image1, -30));

			for (int j = 0; j < e.size(); j++)
				o.add(new obstacles(e.get(j).getX()+110/2-20, e.get(j).getY()+110, -100));

		}
		if (temps2 > 4000) {
			temps2 = 0;
			e2.add(new Ennemis((float) (Math.random() * (1500 - 100)), (float) 0.00001, image1, -40));

			for (int j = 0; j < e2.size(); j++)
				o.add(new obstacles(e2.get(j).getX()+110/2-20, e2.get(j).getY()+100, -100));

		}
		if (temps3 > 6000) {
			temps3 = 0;
			e3.add(new Ennemis((float) (Math.random() * (1500 - 90)), (float) 0.00001, image1, -50));

			for (int j = 0; j < e3.size(); j++)
				//if((e3.get(j).getX() != e2.get(j).getX()) && (e3.get(j).getX() != e.get(j).getX()))
				o.add(new obstacles(e3.get(j).getX()+90/2-20, e3.get(j).getY()+98, -100));

		}

//on deplace les obstacles
				for(int j=0;j<o.size();j++) {
					o.get(j).lancerObstacles(delta);
				}
//deplacer les ennemis	
		for(int i=0;i<e.size();i++) {
			e.get(i).deplacer(delta);
		}
		for(int i=0;i<e2.size();i++) {
			e2.get(i).deplacer2(delta);
		}
		for(int i=0;i<e3.size();i++) {
			e3.get(i).deplacer3(delta);
		}
		double frequence = 100;
		t += frequence * delta/1000f;
		if(t>=1000)
			t=0;
		
		


	}

}
	public static void defilement(Graphics g, Image fond, float t) {
		g.drawImage(fond.getScaledCopy(1500,1000),0, t-1000);
		g.drawImage(fond.getScaledCopy(1500,1000),0, t);
	}
}
