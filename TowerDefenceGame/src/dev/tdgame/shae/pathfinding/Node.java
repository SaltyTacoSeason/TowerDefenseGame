package dev.tdgame.shae.pathfinding;

import dev.tdgame.shae.render.Texture;

public class Node {
	public float gScore;
	public float hScore;
	public float fScore;
	
	public float x, y;
	
	public boolean passable;
	public Texture t;
	
	public Node(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public void init(String path) {
		t = Texture.loadTexture(path);
	}
	
	public void tick(float delta) {
		
	}
	
	public void render() {
		
	}
}
