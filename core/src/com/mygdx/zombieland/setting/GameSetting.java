package com.mygdx.zombieland.setting;

public class GameSetting {

    private float musicSoundLevel = 1f;
    private float vfxSoundLevel = 0.4f;
    private float hudVisibleLevel = 0.6f;

    public GameSetting(float musicSoundLevel, float vfxSoundLevel) {
        this.musicSoundLevel = musicSoundLevel;
        this.vfxSoundLevel = vfxSoundLevel;
    }

    public GameSetting() {
    }

    public float getMusicSoundLevel() {
        return musicSoundLevel;
    }

    public void setMusicSoundLevel(float musicSoundLevel) {
        this.musicSoundLevel = musicSoundLevel;
    }

    public float getVfxSoundLevel() {
        return vfxSoundLevel;
    }

    public void setVfxSoundLevel(float vfxSoundLevel) {
        this.vfxSoundLevel = vfxSoundLevel;
    }

    public float getHudVisibleLevel() {
        return hudVisibleLevel;
    }

    public void setHudVisibleLevel(float hudVisibleLevel) {
        this.hudVisibleLevel = hudVisibleLevel;
    }
}
