package dev.tdgame.shae.entity;

import java.util.ArrayList;

import dev.tdgame.shae.core.Game;
import dev.tdgame.shae.render.Renderer;

public class Spawner {

	public ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	private int ticks = 0;
	private int chosenCorner = 0;

	public void init() {

	}

	public void render(Renderer r) {

		for (Enemy e : enemies)
			e.render(r);

	}

	public void tick() {
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).tick();
			if (enemies.get(i).dead) {
				enemies.get(i).dispose();
				enemies.remove(i);
			}
		}

		if (ticks == 75) {
			switch(chosenCorner) {
			case 0:
				enemies.add(new Enemy(0, 0));
				enemies.get(enemies.size() - 1).init();
				ticks = 0;
				chosenCorner = 1;
				break;
			case 1:
				enemies.add(new Enemy(0, Game.height));
				enemies.get(enemies.size() - 1).init();
				ticks = 0;
				chosenCorner = 2;
				break;
			case 2:
				enemies.add(new Enemy(Game.width, Game.height));
				enemies.get(enemies.size() - 1).init();
				ticks = 0;
				chosenCorner = 3;
				break;
			case 3:
				enemies.add(new Enemy(Game.width, 0));
				enemies.get(enemies.size() - 1).init();
				ticks = 0;
				chosenCorner = 0;
				break;
			}
		} else ticks++;
	}

	public void dispose() {
		for (Enemy e : enemies) {
			e.dispose();
		}
	}
}
