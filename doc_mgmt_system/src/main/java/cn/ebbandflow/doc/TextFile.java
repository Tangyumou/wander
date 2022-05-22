package cn.ebbandflow.doc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class TextFile {
    private final Map<String, String> attributes = new HashMap<>();
    private final List<String> lines;
    private final File file;

    public TextFile(File file) throws IOException {
        this.file = file;
        lines = Files.readAllLines(file.toPath());
    }

    public void addLineSuffix(final String prefixName, final String attributeName) {
        for (String line : lines) {
            if (line.startsWith(prefixName)) {
                attributes.put(attributeName, line.substring(prefixName.length()).trim());
                break;
            }
        }
    }

    public int addLines(int start, Predicate<String> isEnd, String attributeName) {
        StringBuilder accumulator = new StringBuilder();
        int lineNum = start;
        for (; lineNum < lines.size(); lineNum++) {
            if(isEnd.test(lines.get(lineNum))){
                break;
            }
            accumulator.append(lines.get((lineNum)));
            accumulator.append("\n");
        }
        attributes.put(attributeName,accumulator.toString().trim());
        return lineNum;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }
}
