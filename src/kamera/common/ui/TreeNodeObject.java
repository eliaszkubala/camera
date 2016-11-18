package kamera.common.ui;

/**
 * Created by TheKing on 06.02.2016.
 */
public class TreeNodeObject {
    private final String label;
    private final String data;

    public TreeNodeObject(String label, String data) {
        this.label = label;
        this.data = data;
    }

    public String getLabel() {
        return label;
    }

    public String getData() {
        return data;
    }
}
