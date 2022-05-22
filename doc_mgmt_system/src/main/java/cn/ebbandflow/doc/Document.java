package cn.ebbandflow.doc;

import java.util.Map;

public class Document {
    private final Map<String, String> attributes;

    public Document(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    String getAttribute(final String attributeName) {
        return attributes.get(attributeName);
    }

    public static void main(String[] args) {
        String path = "catjpg.";
        System.out.println(path.lastIndexOf("."));
    }
}
