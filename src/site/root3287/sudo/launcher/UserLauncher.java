package site.root3287.sudo.launcher;

public class UserLauncher implements Runnable{

	private Thread thread;
	
	public UserLauncher() {
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		
	}

}
