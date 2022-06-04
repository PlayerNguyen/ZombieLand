package com.mygdx.zombieland.setting;

public class GameSetting {

    private float musicSoundLevel = 0.2f;
    private float vfxSoundLevel = 0.4f;

    public GameSetting(float musicSoundLevel, float vfxSoundLevel) {
        this.musicSoundLevel = musicSoundLevel;
        this.vfxSoundLevel = vfxSoundLevel;
    }

    public GameSetting() {
    }

    public float getMusicSoundLevel() {
        return musicSoundLevel;
    }

    public float getVfxSoundLevel() {
        return vfxSoundLevel;
    }
}
