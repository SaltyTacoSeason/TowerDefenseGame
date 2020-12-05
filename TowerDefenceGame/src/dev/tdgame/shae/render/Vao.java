package dev.tdgame.shae.render;

import static org.lwjgl.opengl.GL30.*;

public class Vao {
	
	private final int id;
	
	public Vao() {
		id = glGenVertexArrays();
	}
	
	public void bind() {
		glBindVertexArray(id);
	}
	
	public void delete() {
		glDeleteVertexArrays(id);
	}
	
	public int getID() {
		System.out.println("You naughty boy!");
		return id;
	}
}
