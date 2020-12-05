package dev.tdgame.shae.pathfinding;

public class Node {
	public float gScore;
	public float hScore;
	public float fScore;
	
	public float x, y;
	
	public Node(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	
}
