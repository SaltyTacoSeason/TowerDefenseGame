package dev.tdgame.shae.entity;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_SPACE;

import java.util.ArrayList;

import dev.tdgame.shae.core.Game;
import dev.tdgame.shae.render.Renderer;

public class Spawner {

	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	
	public void init() {
		
	}

	public void render(Renderer r) {
		
		for (Enemy e : enemies)
			e.render(r);
		
	}
	
	public void tick() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
			if(enemies.get(i).dead) {
				enemies.get(i).dispose();
				enemies.remove(i);
			}	
		}

		// test code
		if (Game.keys[GLFW_KEY_SPACE]) {
			enemies.add(new Enemy((int) Game.mouseX, (int) Game.mouseY));
			enemies.get(enemies.size() - 1).init();
		}
	}

	public void dispose() {
		for (Enemy e : enemies) {
			e.dispose();
		}
	}
}
