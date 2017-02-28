package site.root3287.lwjgl.engine.render;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import site.root3287.lwjgl.component.TransformationComponent;
import site.root3287.lwjgl.entities.Entity;
import site.root3287.lwjgl.model.RawModel;
import site.root3287.lwjgl.model.TexturedModel;
import site.root3287.lwjgl.shader.shaders.StaticShader;
import site.root3287.lwjgl.texture.ModelTexture;
import site.root3287.lwjgl.utils.LWJGLMaths;

public class EntityRender {
	private Matrix4f projectionMatrix;
	private StaticShader shader;

	public EntityRender(StaticShader shader, Matrix4f projectionMatrix) {
		this.shader = shader;
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		this.projectionMatrix = projectionMatrix;
		shader.start();
		shader.loadProjectionMatrix(projectionMatrix);
		shader.stop();
	}

	public void render(Map<TexturedModel, List<Entity>> entities) {
		for (TexturedModel model : entities.keySet()) {
			prepareTexturedModel(model);
			List<Entity> batch = entities.get(model);
			for (Entity entity : batch) {
				prepareInstance(entity);
				GL11.glDrawElements(GL11.GL_TRIANGLES, model.getRawModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			}
			unbindTexturedModel();
		}
	}

	private void prepareTexturedModel(TexturedModel model) {
		RawModel rawModel = model.getRawModel();
		GL30.glBindVertexArray(rawModel.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		ModelTexture texture = model.getTexture();
		shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getTextureID());
	}

	private void unbindTexturedModel() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}

	private void prepareInstance(Entity entity) {
		Matrix4f transformationMatrix = LWJGLMaths.createTransformationMatrix(
				((TransformationComponent)entity.getComponent("transformation")).position, 
				((TransformationComponent)entity.getComponent("transformation")).rotation.x, 
				((TransformationComponent)entity.getComponent("transformation")).rotation.y, 
				((TransformationComponent)entity.getComponent("transformation")).rotation.z, 
				((TransformationComponent)entity.getComponent("transformation")).scale);
		shader.loadTransformationMatrix(transformationMatrix);
	}
}
