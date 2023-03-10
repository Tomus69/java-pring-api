package fr.m2i.javaspringapi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SongsController {

    private static final ArrayList<Song> songs = ResourcesManager.getSongsFromJSON();

    @GetMapping("/songs")
    protected String songsBeforeDate(@RequestParam(value = "date", required = false) Integer date, Model model) {
        ArrayList<List<String>> filteredSongs = new ArrayList<>();
        ArrayList<String> fieldNames = new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {
            int songYear = Integer.parseInt(songs.get(i).getYear());
            if (date == null || songYear < date) {
                fieldNames.clear();
                Field[] fields = songs.get(i).getClass().getDeclaredFields();
                List<String> fieldValues = new ArrayList<>();
                for (Field field : fields) {
                    try {
                        field.setAccessible(true);
                        fieldValues.add(field.get(songs.get(i)).toString());
                        fieldNames.add(field.getName());
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
                filteredSongs.add(fieldValues);
            }
        }
        model.addAttribute("songs", filteredSongs);
        model.addAttribute("names", fieldNames);
        model.addAttribute("date", date);
        return "songs";
    }

    @PostMapping("/songs")
    protected String songsYearInput(@RequestParam(value = "date") Integer date, Model model) {
        model.addAttribute("year", date);
        return "songs";
    }

}
