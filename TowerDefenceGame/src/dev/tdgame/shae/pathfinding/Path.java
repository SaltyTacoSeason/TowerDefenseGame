package dev.tdgame.shae.pathfinding;

import java.awt.Point;
import java.util.ArrayList;

import dev.tdgame.shae.core.Game;

public class Path {
	public ArrayList<Node> open = new ArrayList<Node>();
	public ArrayList<Node> closed = new ArrayList<Node>();

	public void tracePath(Node current, Node goal) {
		open.add(current);

		while (open.size() > 0) {
			for (Node n : open) {
				if (n.fCost() <= current.fCost()) {
					current = n;
				}
			}

			open.remove(current);
			closed.add(current);

			if (current.equals(goal)) {
				// trace path back
				return;
			}
			
			for(Node neighbour : getNeigbours(current)) {
				if(neighbour.isTraversable() || closed.contains(neighbour)) {
					continue;
				}
				
				
			}
		}
	}

	public ArrayList<Node> getNeigbours(Node center) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		Point key = new Point();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					key.x = i + center.x;
					key.y = j + center.y;
					if(Game.nodes.get(key) == null)
						neighbours.add(Game.nodes.get(key));
				}
			}
		}
		
		return neighbours;
	}
	
	public int getDistance(Node from, Node to) {
		
	}
}
