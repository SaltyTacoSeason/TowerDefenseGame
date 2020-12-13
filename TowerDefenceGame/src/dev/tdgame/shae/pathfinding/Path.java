package dev.tdgame.shae.pathfinding;

import java.util.HashMap;

public class Path {
	public HashMap<Integer, Node> open = new HashMap<Integer, Node>();
	public HashMap<Integer, Node> closed = new HashMap<Integer, Node>();
	public Node current;
	public Node goal;
	
	public void tracePath(Node current, Node goal) {
		
	}
}
