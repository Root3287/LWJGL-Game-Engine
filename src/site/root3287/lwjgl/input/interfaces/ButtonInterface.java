package site.root3287.lwjgl.input.interfaces;

import java.util.List;

import site.root3287.lwjgl.texture.Texture2D;

public interface ButtonInterface {
	void onClick(ButtonInterface b);
	void onHover(ButtonInterface b);
	void offHover(ButtonInterface b);
	void whileHover(ButtonInterface b);
	void show(List<Texture2D> guiTextures);
	void hide(List<Texture2D> guiTextures);
	
	void update();
}
