package sg.edu.np.twq2.e53listview;

import java.util.HashMap;
import java.util.Map;

public class Song {

    private String Artist;
    private String Duration;
    private String Title;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Song(String tt, String at, String dr)
    {
        Artist = at;
        Duration = dr;
        Title = tt;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        this.Artist = artist;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        this.Duration = duration;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}