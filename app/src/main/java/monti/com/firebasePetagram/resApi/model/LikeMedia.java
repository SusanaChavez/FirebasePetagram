package monti.com.firebasePetagram.resApi.model;

/**
 * Created by Susana on 15/01/2017.
 */

public class LikeMedia {
    private String media[];
    private String data;

    public LikeMedia(String[] media, String data) {
        this.media = media;
        this.data = data;
    }

    public String[] getMedia() {
        return media;
    }

    public void setMedia(String[] media) {
        this.media = media;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
