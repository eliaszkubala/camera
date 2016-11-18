package kamera.common.model;

/**
 * Created by TheKing on 03.02.2016.
 */
public class ListWrapper {
    private String data;
    private String name;

    public ListWrapper(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getName() {
        return name;
    }
}
