package dev.tdgame.shae.render;

import static org.lwjgl.opengl.GL30.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Shader {
	
	private final int id;
	
	public Shader(int type) {
		id = glCreateShader(type);
	}
	
	public void setSource(CharSequence source) {
		glShaderSource(id, source);
	}
	
	public void compile() {
		glCompileShader(id);
		
		checkStatus();
	}
	
	public void checkStatus() {
		int status = glGetShaderi(id, GL_COMPILE_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(id));
        } else System.out.println("Shader A OK");
	}
	
	public void delete() {
        glDeleteShader(id);
    }
	
	public int getID() {
        return id;
    }
	
	public static Shader createShader(int type, CharSequence source) {
		Shader shader = new Shader(type);
        shader.setSource(source);
        shader.compile();

        return shader;
	}
	
	public static Shader loadShader(int type, String path) {
		StringBuilder builder = new StringBuilder();
       
		System.out.println(Shader.class.getResource(path).getPath());

        String totalpath = Shader.class.getResource(path).getPath();
        
        try (InputStream in = new FileInputStream(totalpath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Failed to load a shader file!"
                                       + System.lineSeparator() + ex.getMessage());
        }
        CharSequence source = builder.toString();

        return createShader(type, source);
    }
}
