package dev.tdgame.shae.core;

import dev.tdgame.shae.entity.Tile;
import dev.tdgame.shae.render.Renderer;

public class World {

	public Tile[][] map;
	public int mapWidth;

	public void init() {
		System.out.println((int)Math.ceil(Game.height / 32)+1);
		map = new Tile[(int)(Game.width / 32)][(int)(Game.height / 32)+1];
		mapWidth = (int)(Game.width / 32);

		for (int j = 0; j < map[0].length; j++) {
			for (int i = 0; i < map.length; i++) {
				map[i][j] = new Tile(i * 32, j * 32);
				map[i][j].init("/floor.png");
			}
		}
	}

	public void render(Renderer r) {
		r.begin();
		for (int j = 0; j < map[0].length; j++) {
			for (int i = 0; i < map.length; i++) {
				map[i][j].render(r);
//				System.out.println(" loops, and this space is " + n);
			}
		}
		r.end();
	}
}
