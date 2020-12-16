package dev.tdgame.shae.pathfinding;

public class Node {
	public int x, y;
	public int gCost, hCost;
	public Node parent;
	private boolean traversable = true;
	
	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int fCost() {
		return gCost + hCost;
	}
	
	public boolean isTraversable() {
		return traversable;
	}
}
