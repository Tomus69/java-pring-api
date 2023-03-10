package fr.m2i.javaspringapi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ResourcesManager {

    protected static ArrayList<Song> getSongsFromJSON() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\main\\resources\\static\\json\\songs.json");
        try {
            Songs songs = objectMapper.readValue(file, Songs.class);
            return songs.getSongs();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
