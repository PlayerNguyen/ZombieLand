package com.mygdx.zombieland;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.lwjgl.Version;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
    public static void main(String[] arg) {
        System.out.println("Game loading with lwjgl3 version " + Version.getVersion());
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        ZombieLandMain zombieLandMain = new ZombieLandMain();

        config.useVsync(true);
        config.setResizable(false);
        config.setForegroundFPS(60);
        config.setTitle(String.format("ZombieLand [ %s ]", ZombieLandMain.gameVersion));
        config.setWindowedMode(800, 600);

        new Lwjgl3Application(zombieLandMain, config);
    }
}
