package site.root3287.sudo.entities.Camera;

import java.util.HashMap;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo.terrain.Terrain;

public class Player extends Camera{
	private String username;
	public Player(Vector3f position, String username) {
		super(position);
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(HashMap<Integer, HashMap<Integer, Terrain>> terrain, float delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
