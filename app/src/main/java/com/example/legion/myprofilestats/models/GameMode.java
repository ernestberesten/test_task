package com.example.legion.myprofilestats.models;

/**
 * Created by Ernest on 07.03.2018.
 */

public class GameMode {
    private String id;
    private String mode;

    public GameMode() {
    }

    public GameMode(String id, String mode) {
        this.id = id;
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
