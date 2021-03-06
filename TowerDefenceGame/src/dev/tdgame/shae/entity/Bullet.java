package dev.tdgame.shae.entity;

import java.awt.Rectangle;
import java.util.Random;

import dev.tdgame.shae.core.Game;
import dev.tdgame.shae.render.Renderer;
import dev.tdgame.shae.render.Texture;



public class Bullet {

    public float posX, posY;
    
    public float angle;
    
    float velocity = 11;
    
    double velocityX, velocityY;
    
    public int time = 0;
        
    private Texture texture;
    
    public Rectangle r;
    
    private Random rand;
    
    public float damage;
    
    public Bullet(float angle, float damage) {
    	
    	rand = new Random();
        
        texture = new Texture();
        texture = Texture.loadTexture("/bullet.png");
                
        float inac = (rand.nextFloat() - 0.5f) / 48;
        
        this.angle = angle + inac;
        
        this.damage = damage;
        
        r = new Rectangle();      
    }
    
    public void move() {
    	        
    	if(Game.rightMouseDown) {
    		int delta_x = (int) (Game.mouseX - posX);
			int delta_y = (int) (Game.mouseY - posY);
			float theta_radians = (float) Math.atan2(delta_y, delta_x);
    	
			angle = theta_radians;
    	}
    	    	
        velocityX =  (velocity*Math.cos(angle));
        velocityY =  (velocity*Math.sin(angle));
        
        posX += velocityX;
        posY += velocityY;
        
        time++;
        
        r.setBounds((int)posX,(int) posY, texture.getWidth(), texture.getHeight());
        
    }

    public void render(Renderer r) {  
//        renderer.begin();
//        texture.bind();
        r.drawTexture(texture, posX, posY);
//        renderer.end();
    }
    
    public void delete() {
    	texture.delete();
    }
    
    public Texture getTexture() {
    	return texture;
    }
}
