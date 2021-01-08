package dev.tdgame.shae.entity;

import dev.tdgame.shae.render.Renderer;
import dev.tdgame.shae.render.Texture;

public class Tile {
	
	public float x, y;
	
	public boolean passable = true;
	public Texture t;
	
	public Tile(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void init(int id) {
		switch(id) {
		case 0:
			t = Texture.loadTexture("/floor.png");
		}
	}
	
	public void render(Renderer r) {
		t.bind();
		
		r.drawTexture(t, x, y);
		r.flush();
	}
}
