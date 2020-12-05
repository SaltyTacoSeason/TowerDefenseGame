package dev.tdgame.shae.core;

import org.lwjgl.glfw.GLFW;

public class Timer {
	private double lastLoopTime;
	private float timeCount;
	private int fps, fpsCount, ups, upsCount;
	
	public void init() {
		lastLoopTime = GLFW.glfwGetTime();
	}
	
	public float getDelta() {
		double time = GLFW.glfwGetTime();
		float delta = (float)(time - lastLoopTime);
		lastLoopTime = time;
		timeCount += delta;
		return delta;
	}
	
	public void updateFPS() {
		fpsCount++;
	}
	
	public void updateUPS() {
		upsCount++;
	}
	
	public void update() {
		if(timeCount > 1f) {
			fps = fpsCount;
			fpsCount = 0;
			
			ups = upsCount;
			upsCount = 0;
			
			timeCount -= 1f;
		}
	}
	
	public int getFPS() {
        return fps > 0 ? fps : fpsCount;
    }

    public int getUPS() {
        return ups > 0 ? ups : upsCount;
    }
    
    public double getLastLoopTime() {
        return lastLoopTime;
    }
}
