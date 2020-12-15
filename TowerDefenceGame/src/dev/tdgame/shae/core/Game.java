package dev.tdgame.shae.core;

import static org.lwjgl.glfw.GLFW.GLFW_KEY_A;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_D;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_ESCAPE;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_S;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_W;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;
import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_RIGHT;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;
import static org.lwjgl.glfw.GLFW.glfwGetPrimaryMonitor;
import static org.lwjgl.glfw.GLFW.glfwGetVideoMode;
import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSetErrorCallback;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.glfw.GLFW.glfwSetMouseButtonCallback;
import static org.lwjgl.glfw.GLFW.glfwSetWindowShouldClose;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwTerminate;

import java.awt.Point;
import java.util.HashMap;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import dev.tdgame.shae.entity.BulletWand;
import dev.tdgame.shae.entity.Oil;
import dev.tdgame.shae.entity.Player;
import dev.tdgame.shae.entity.Spawner;
import dev.tdgame.shae.gfx.Window;
import dev.tdgame.shae.pathfinding.Node;
import dev.tdgame.shae.render.Renderer;

public class Game {

	private GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);

	protected Window window;
	protected Timer timer;

	private Renderer r;

	public static Player p;

	private Oil oilCan;

	private boolean running;

	private GLFWKeyCallback movement;

	public static boolean w = false, s = false, a = false, d = false;

	public static boolean[] keys = new boolean[512];

	public static int width, height;

	public static float mouseX, mouseY;

	private GLFWCursorPosCallback cpCallback;

	private GLFWMouseButtonCallback mbCallback;
	
	public static BulletWand bw;

	public static boolean rightMouseDown = false, leftMouseDown = false;

	public Spawner es;
	
	public World m;
	
	public static HashMap<Point, Node> nodes = new HashMap<Point, Node>();

	// test code area


	// end test code area

	public Game() {
		timer = new Timer();
	}

	private void init() {
		// set the error callback
		glfwSetErrorCallback(errorCallback);

		// init glfw
		if (!glfwInit()) {
			throw new IllegalStateException("Ur done for");
		}

		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

		width = vidmode.width();
		height = vidmode.height();

		// create window
		window = new Window(width, height, "TDGame", true);

		setCallback();
				
		for (int j = 0; j < height / 32 + 1; j++) {
			for (int i = 0; i < width / 32; i++) {
				nodes.put(new Point(i, j), new Node(i, j));
			}
		}

		r = new Renderer();
		r.init();

		p = new Player(width / 2 - 16, height / 2 - 16);
		p.init();
		
		m = new World();
		m.init();

		oilCan = new Oil();
		oilCan.init();


		bw = new BulletWand(r, 26, 1);
		
		es = new Spawner();
		es.init();

		running = true;
	}

	private void dispose() {
		r.dispose();

		p.delete();

		es.dispose();

		oilCan.dispose();

		window.destroy();
		movement.free();

		glfwTerminate();
		errorCallback.free();
	}

	private void gameLoop() {
		float delta;

		while (running) {
			if (window.isClosing())
				running = false;

			delta = timer.getDelta();

			input();

			update(delta);
			timer.updateUPS();

			render();
			timer.updateFPS();

			timer.update();

			window.update();
		}
	}

	private void render() {
		r.clear();

		m.render(r);

		oilCan.render(r);
		
		es.render(r);

		p.render(r);

		bw.render(r);

		glfwSwapBuffers(window.getID());
	}

	private void update(float delta) {
		oilCan.tick(delta);
		p.tick(delta);
		bw.tick();
		es.tick();
	}

	private void input() {
		glfwPollEvents();
	}

	public void start() {
		init();
		gameLoop();
		dispose();
	}

	public static boolean isDefaultContext() {
		return GL.getCapabilities().OpenGL32;
	}

	private void setCallback() {
		movement = new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS) {
					glfwSetWindowShouldClose(window, true);
				}

				if (action == GLFW_PRESS) {
					keys[key] = true;
				}
				if (action == GLFW_RELEASE) {
					keys[key] = false;
				}

				if (key == GLFW_KEY_W && action == GLFW_PRESS)
					w = true;
				else if (key == GLFW_KEY_W && action == GLFW_RELEASE)
					w = false;

				if (key == GLFW_KEY_S && action == GLFW_PRESS)
					s = true;
				else if (key == GLFW_KEY_S && action == GLFW_RELEASE)
					s = false;

				if (key == GLFW_KEY_A && action == GLFW_PRESS)
					a = true;
				else if (key == GLFW_KEY_A && action == GLFW_RELEASE)
					a = false;

				if (key == GLFW_KEY_D && action == GLFW_PRESS)
					d = true;
				else if (key == GLFW_KEY_D && action == GLFW_RELEASE)
					d = false;
			}
		};
		GLFW.glfwSetCursorPosCallback(window.getID(), cpCallback = new GLFWCursorPosCallback() {

			public void invoke(long window, double xpos, double ypos) {
				mouseX = (float) xpos;
				mouseY = (float) ((float) height - ypos - 1);
			}
		});
		glfwSetMouseButtonCallback(window.getID(), mbCallback = new GLFWMouseButtonCallback() {
			public void invoke(long window, int button, int action, int mods) {
				if (button == GLFW_MOUSE_BUTTON_LEFT) {
					if (action == GLFW_PRESS)
						leftMouseDown = true;
					else if (action == GLFW_RELEASE)
						leftMouseDown = false;
				} else if (button == GLFW_MOUSE_BUTTON_RIGHT) {
					if (action == GLFW_PRESS)
						rightMouseDown = true;
					else if (action == GLFW_RELEASE)
						rightMouseDown = false;
				}
			}
		});
		glfwSetKeyCallback(window.getID(), movement);
	}
}
