package dev.tdgame.shae.core;

import dev.tdgame.shae.pathfinding.Node;

public class World {
	
	public Node[] map;
	
	public void init() {
		map = new Node[Game.width / 32 * Game.height / 32];
	}
}
