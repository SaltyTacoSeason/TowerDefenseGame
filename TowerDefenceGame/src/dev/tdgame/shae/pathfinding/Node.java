package dev.tdgame.shae.pathfinding;

import dev.tdgame.shae.core.Game;

public class Node {
	public int x, y;
	public int gCost, hCost;
	public Node parent;
	private boolean traversable = true;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
		
		traversable = Game.m.map[x][y].passable;
		System.out.println(traversable + " x: " + x + " y: " + y);
	}
	
	public int fCost() {
		return gCost + hCost;
	}
	
	public boolean isTraversable() {
		return traversable;
	}
}
