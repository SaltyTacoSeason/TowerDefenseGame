package dev.tdgame.shae.entity;

import dev.tdgame.shae.render.Renderer;
import dev.tdgame.shae.render.Texture;

public class Tile {
	public float gScore;
	public float hScore;
	public float fScore;
	
	public float x, y;
	
	public boolean passable;
	public Texture t;
	
	public Tile(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void init(String path) {
		t = Texture.loadTexture(path);
	}
	
	public void render(Renderer r) {
		t.bind();
		
		r.drawTexture(t, x, y);
		
	}
}
