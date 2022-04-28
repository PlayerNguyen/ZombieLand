package com.mygdx.zombieland;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class ZombieLandMain extends ApplicationAdapter {

	SpriteBatch batch;
	World world;

	@Override
	public void create () {
		this.batch = new SpriteBatch();
		this.world = new World(batch);
		this.world.create();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 1, 1);
		this.world.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		this.world.dispose();
	}
}
