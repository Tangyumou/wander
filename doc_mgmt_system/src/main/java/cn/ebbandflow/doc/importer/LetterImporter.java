package cn.ebbandflow.doc.importer;

import cn.ebbandflow.doc.Document;
import cn.ebbandflow.doc.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static cn.ebbandflow.doc.Attributes.*;

public class LetterImporter implements Importer {
    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        int lineNum = textFile.addLines(2, String::isEmpty, ADDRESS);
        textFile.addLines(lineNum + 1, line -> line.startsWith("regards,"), BODY);
        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE, "LETTER");
        return new Document(attributes);
    }
}
