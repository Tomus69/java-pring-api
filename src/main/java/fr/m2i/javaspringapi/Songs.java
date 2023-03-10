package fr.m2i.javaspringapi;

import java.util.ArrayList;

public class Songs {

    private ArrayList<Song> songs = new ArrayList<>();

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
