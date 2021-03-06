package dev.tdgame.shae.core;

import dev.tdgame.shae.entity.Tile;
import dev.tdgame.shae.render.Renderer;

public class World {

	public Tile[][] map;

	public void init() {
		map = new Tile[(int) (Game.width / 32) + 1][(int) (Game.height / 32) + 1];

		for (int j = 0; j < map[0].length; j++) {
			for (int i = 0; i < map.length; i++) {
				map[i][j] = new Tile(i * 32, j * 32);
				map[i][j].init(0);
			}
		}
		
		map[4][6].init(1);
	}

	public void render(Renderer r) {
		r.begin();
		for (int j = 0; j < map[0].length; j++) {
			for (int i = 0; i < map.length; i++) {
				map[i][j].render(r);
			}
		}
		r.end();
	}
}
