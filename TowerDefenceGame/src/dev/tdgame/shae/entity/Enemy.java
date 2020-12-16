package dev.tdgame.shae.entity;

import java.awt.Point;
import java.awt.Rectangle;

import dev.tdgame.shae.core.Game;
import dev.tdgame.shae.pathfinding.Node;
//import dev.tdgame.shae.pathfinding.Node;
import dev.tdgame.shae.pathfinding.Path;
import dev.tdgame.shae.render.Renderer;
import dev.tdgame.shae.render.Texture;

public class Enemy {
	public Texture t;

	public int x, y;

	public boolean dead = false;

	public float health = 1000;

	private Rectangle bounds;
	
	private Path path;

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void init() {
		t = Texture.loadTexture("/enemy.png");
		t.bind();
		bounds = new Rectangle(x, y, t.getWidth(), t.getHeight());
		path = new Path();
//		path.current = new Node(x, y);
	}

	public void tick() {
		if (!dead) {
			path.tracePath(Game.nodes.get(new Point((int)Math.floor(x / 32), (int)Math.floor(y / 32))), Game.p.pNode);
			if(!(path.path.size() == 0)) {
				x = path.path.get(0).x * 32;
				path.path.remove(0);
			}
			System.out.println(path.path.size());
			
			bounds.x = x;
			bounds.y = y;

			for (Bullet b : Game.bw.bullet) {
				if (bounds.intersects(b.r)) {
					b.posX = 309485;
					health -= b.damage;
				}
			}

			if (health <= 0) {
				dead = true;
			}
		}
	}

	public void render(Renderer r) {
		if (!dead) {
			r.begin();

			t.bind();
			r.drawTexture(t, x, y);

			r.end();
		}
	}

	public void dispose() {
		t.delete();
	}
}
