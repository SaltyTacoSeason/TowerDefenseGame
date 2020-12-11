package dev.tdgame.shae.entity;

import java.util.ArrayList;
import java.util.Random;

import dev.tdgame.shae.core.Game;
import dev.tdgame.shae.render.Renderer;

public class BulletWand {
	
	public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	
	private Renderer r;
	
	private int l = 1;
	
    private float reload = 25;
    
    private int shots = 10;
    
    private Random rand;
	
	public BulletWand(Renderer r, int shots, float reload) {
		this.shots = shots;
		this.reload = reload;
		this.r = r;
		rand = new Random();
	}
	
	public void render(Renderer r) {
		this.r = r;
		r.begin();
		for(Bullet b : bullet) {
			b.render(r);
		}
		r.end();
	}
	
	public void tick() {
		if(Game.leftMouseDown) {
			if(l % reload == 0) {
				for(int i = 0; i < shots; i++) {
					int delta_x = (int) (Game.mouseX - Game.p.x - 4);
					int delta_y = (int) (Game.mouseY - Game.p.y - 16);
					float theta_radians = (float) Math.atan2(delta_y, delta_x);
					float inac = (rand.nextFloat() - 0.5f) / 12;
					bullet.add(new Bullet(theta_radians + inac, 100));
					bullet.get(bullet.size() - 1).posX = Game.p.x + 4;
					bullet.get(bullet.size() - 1).posY = Game.p.y + 16;
					if(shots >= 3) {
						bullet.get(bullet.size() - 1).posX += (rand.nextFloat() - 0.5f) * 12;
						bullet.get(bullet.size() - 1).posY += (rand.nextFloat() - 0.5f) * 12;
					}
				}
			}
			l++;
		}
		for(int i = 0; i < bullet.size(); i++) {
			bullet.get(i).move();
			
			if((bullet.get(i).posX > Game.width) || 
					(bullet.get(i).posY > Game.height) || 
					(bullet.get(i).posX < 0) || 
					(bullet.get(i).posY < 0)) {bullet.get(i).delete();bullet.remove(i);};
		}
		
//		l++;
	}
}
