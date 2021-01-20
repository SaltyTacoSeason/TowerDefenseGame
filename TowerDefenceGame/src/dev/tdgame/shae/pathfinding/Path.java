package dev.tdgame.shae.pathfinding;

import java.awt.Point;
import java.util.ArrayList;

import dev.tdgame.shae.core.Game;

public class Path {
	public ArrayList<Node> open;
	public ArrayList<Node> closed = new ArrayList<Node>();
	
	public ArrayList<Node> path = new ArrayList<Node>();

	public void tracePath(Node start, Node goal) {
		open = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		Node current = start;
		
		current.gCost = 0;
		current.hCost = getDistance(current, goal);
		System.out.println("distance: " + getDistance(current, goal));
		
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
				System.out.println("yay!");
				retracePath(start, goal);
				return;
			}
			
			int i = 0;
			for(Node neighbour : getNeigbours(current)) {
				System.out.println(i++ + " loops");
				if(!neighbour.isTraversable() || closed.contains(neighbour)) {
					System.out.println("not a valid neighbour");
					continue;
				}
				
				int newMovementCostToNeighbour = current.gCost + getDistance(current, neighbour);
				
				if(neighbour.gCost == 0) {
					neighbour.gCost = newMovementCostToNeighbour;
				}
				
				if(newMovementCostToNeighbour < neighbour.gCost || !open.contains(neighbour)) {
					neighbour.gCost = newMovementCostToNeighbour;
					neighbour.hCost = getDistance(neighbour, goal);
					neighbour.parent = current;
					
					if(!open.contains(neighbour)) {
						open.add(neighbour);
					}
				}
			}
		}
	}
	
	void retracePath(Node startNode, Node endNode) {
		path = new ArrayList<Node>();
		Node currentNode = endNode;
		
		while(currentNode != startNode) {
			path.add(currentNode);
			currentNode = currentNode.parent;
		}
		
		path = reverse(path);
	}

	public ArrayList<Node> reverse(ArrayList<Node> list){
		ArrayList<Node> temp = new ArrayList<Node>();		
		int i = list.size() - 1;
		
		while(list.size() > 0) {
			temp.add(list.remove(i));
			i--;
		}
		return temp;
	}
	
	public ArrayList<Node> getNeigbours(Node center) {
		ArrayList<Node> neighbours = new ArrayList<Node>();
		Point key = new Point();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (!(i == 0 && j == 0)) {
					key.x = i + center.x;
					key.y = j + center.y;
					if(Game.nodes.get(key) != null)
						neighbours.add(Game.nodes.get(key));
				}
			}
		}
		
		return neighbours;
	}
	
	public int getDistance(Node from, Node to) {
		int dstX = Math.abs(from.x - to.x);
		int dstY = Math.abs(from.y - to.y);
		
		if(dstX > dstY) {
			return 14*dstY + 10*(dstX-dstY);
		} else return 14*dstX + 10*(dstY-dstX);
	}
}
