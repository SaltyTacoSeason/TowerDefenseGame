package dev.tdgame.shae.entity;

import java.awt.Point;

import dev.tdgame.shae.core.Game;
import dev.tdgame.shae.pathfinding.Node;
import dev.tdgame.shae.render.Renderer;
import dev.tdgame.shae.render.Texture;

public class Player {
	public int x, y, vx = 0, vy = 0;
	public Texture t;		
	public Node pNode;
		
	public Player(int x, int y)	{
		this.x = x;
		this.y = y;
	}
	
	public void init() {
		t = Texture.loadTexture("/player.png");
		t.bind();
	}
	
	public void delete() {
		t.delete();
	}
	
	public void render(Renderer r) {
		r.begin();
		
		t.bind();
		r.drawTextureRegion(x, y, x + t.getWidth(), y + t.getHeight(), 0, 0, 1, 1);
		
		r.end();
	}
	
	public void tick(float delta) {
		move();
		pNode = Game.nodes.get(new Point((int)Math.floor(x / 32), (int)Math.floor(y / 32)));
		System.out.println(pNode.x + "   " + pNode.y);
	}
	
	public void move() {
		if(Game.w)
			vy = 4;
		else if(Game.s)
			vy = -4;
		else vy = 0;
		
		if(Game.d)
			vx = 4;
		else if(Game.a)
			vx = -4;
		else vx = 0;
		
		x += vx;
		y += vy;
		
		if(x > Game.width - 32)
			x = Game.width - 32;
		else if(x < 0)
			x = 0;
		
		if(y > Game.height - 32)
			y = Game.height - 32;
		else if(y < 0)
			y = 0;
	}
}
