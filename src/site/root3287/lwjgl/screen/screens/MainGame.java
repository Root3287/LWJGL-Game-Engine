package site.root3287.lwjgl.screen.screens;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.lwjgl.engine.DisplayManager;
import site.root3287.lwjgl.engine.Loader;
import site.root3287.lwjgl.engine.render.Render;
import site.root3287.lwjgl.entities.Camera;
import site.root3287.lwjgl.entities.Light;
import site.root3287.lwjgl.screen.Screen;
import site.root3287.lwjgl.terrain.Terrain;
import site.root3287.lwjgl.texture.ModelTexture;

public class MainGame extends Screen{
	private List<Terrain> allTerrain = new ArrayList<Terrain>();
	private Light light;
	private Camera c;
	private Terrain[][] terrainForCollision;
	int frames = 0;
	long lastFPSTime;
	public MainGame() {
		
	}
	
	public MainGame(Render render, Loader loader) {
		super(render, loader);
	}

	
	@Override
	public void init() {
		this.light = new Light(new Vector3f(0, 1000, 0), new Vector3f(1, 1, 1));
		this.c = new Camera(new Vector3f(10, 10, 0));
		Mouse.setGrabbed(this.c.isGrabbed());
		
		terrainForCollision = new Terrain[255][255];
		
		int seed = new Random().nextInt();
		
		for(int tX = 0; tX <= 5; tX++){
        	for(int tY = 0; tY <= 5; tY++){
        		System.out.println("Processing terrain for "+tX+" "+tY);
        		Terrain t1 = new Terrain(
        				tX,
						tY, 
						this.loader, 
						new ModelTexture(
								this.loader.loadTexture("res/image/grass.png")
						), 
						64, 
						seed
        				);
        		allTerrain.add(t1);
        		terrainForCollision[tX][tY] = t1;
        	}
        }
	}

	@Override
	public void update() {
		long currentTime = DisplayManager.getTime();
		frames++;
		if (currentTime - lastFPSTime >= 1.0 ){ // If last prinf() was more than 1 sec ago
	         // printf and reset timer
	         System.out.println(frames);
	         frames = 0;
	         lastFPSTime = currentTime;
	     }
		this.c.update(this.terrainForCollision, DisplayManager.getDelta());
	}

	@Override
	public void render() {
		for(Terrain t:allTerrain){
			this.render.processTerrain(t);
		}
		this.render.render(light, this.c);
	}

	@Override
	public void dispose() {
		this.render.dispose();
		this.loader.destory();
	}

}
