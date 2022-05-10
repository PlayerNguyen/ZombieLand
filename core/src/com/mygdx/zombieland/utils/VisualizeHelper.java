package com.mygdx.zombieland.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.zombieland.location.Vector2D;

public class VisualizeHelper {
    private static final ShapeRenderer debugRenderer = new ShapeRenderer();

    public static void drawLine(Vector2D from , Vector2D to, int lineWidth, Matrix4 projectionMatrix) {
        Gdx.gl.glLineWidth(lineWidth);
        debugRenderer.setProjectionMatrix(projectionMatrix);
        debugRenderer.begin(ShapeRenderer.ShapeType.Line);
        debugRenderer.setColor(Color.BLACK);
        debugRenderer.line(new Vector2((float)from.x, (float)from.y),
                new Vector2((float) to.x, (float) to.y));
        debugRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

}
