package site.root3287.lwjgl.Entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import sun.awt.AWTAccessor.KeyboardFocusManagerAccessor;

public class Camera{
	
	private Vector3f position;
	private float pitch, yaw, roll;
	private boolean isGrabbed = true;
	private float sensitivity = 0.25f;
	private float distance = 10f;
	
	public Camera(Vector3f position) {
		this.position = position;
	}

	public void update(float delta) {
		move(delta);
	}
	
	private void move(float delta){
		
		if(isGrabbed){
			this.pitch -= Mouse.getDY() * sensitivity;
			this.yaw += Mouse.getDX() * sensitivity;
		}
		
		float finalDistance = this.distance*delta;
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			position.x += finalDistance * (float)Math.sin(Math.toRadians(yaw));
		    position.z -= finalDistance * (float)Math.cos(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			position.x -= finalDistance * (float)Math.sin(Math.toRadians(yaw));
		    position.z += finalDistance * (float)Math.cos(Math.toRadians(yaw));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			position.x -= finalDistance * (float)Math.sin(Math.toRadians(yaw+90));
		    position.z += finalDistance * (float)Math.cos(Math.toRadians(yaw+90));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			position.x -= finalDistance * (float)Math.sin(Math.toRadians(yaw-90));
		    position.z += finalDistance * (float)Math.cos(Math.toRadians(yaw-90));
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			if(isGrabbed){
				this.isGrabbed = false;
			}else{
				this.isGrabbed = true;
			}
			Mouse.setGrabbed(this.isGrabbed);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public Vector3f getPosition() {
		return this.position;
	}
	
	public void setRoll(float roll) {
		this.roll = roll;
	}

	public float getRoll() {
		return this.roll;
	}
	
	public void setGrabbed(boolean grabbed){
		this.isGrabbed = grabbed;
	}
	
	public boolean isGrabbed(){
		return this.isGrabbed;
	}
}
