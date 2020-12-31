package dev.tdgame.shae.entity;

import java.awt.Point;

import dev.tdgame.shae.core.Game;
import dev.tdgame.shae.pathfinding.Node;
import dev.tdgame.shae.render.Renderer;
import dev.tdgame.shae.render.Texture;

public class Oil {
	
	public Texture t;
	public Node oNode;
	
	public void init() {
		t = Texture.loadTexture("/oil.png");
		t.bind();
		
		oNode = Game.nodes.get(new Point((Game.width / 2 - t.getWidth() / 2)/32, (Game.height / 2 - t.getHeight() / 2)/32));
	}
	
	public void tick(float delta) {
		
	}
	
	public void render(Renderer r) {
		r.begin();
		
		t.bind();
		r.drawTextureRegion(Game.width / 2 - t.getWidth() / 2, Game.height / 2 - t.getHeight() / 2, Game.width / 2 - t.getWidth() / 2 + 64, Game.height / 2 - t.getHeight() / 2 + 64, 0, 0, 1, 1);
		
		r.end();
	}
	
	public void dispose() {
		t.delete();
	}
}
