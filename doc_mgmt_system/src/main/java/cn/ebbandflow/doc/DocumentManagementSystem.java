package cn.ebbandflow.doc;

import cn.ebbandflow.doc.exception.UnknowFileTypeException;
import cn.ebbandflow.doc.importer.ImageImporter;
import cn.ebbandflow.doc.importer.Importer;
import cn.ebbandflow.doc.importer.LetterImporter;
import cn.ebbandflow.doc.importer.ReportImporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentManagementSystem {
    private final Map<String, Importer> extensionToImporter = new HashMap<>();
    private List<Document> documents = new ArrayList<>();

    public DocumentManagementSystem() {
        extensionToImporter.put("letter", new LetterImporter());
        extensionToImporter.put("report", new ReportImporter());
        extensionToImporter.put("jpg", new ImageImporter());
    }

    void importFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(path);
        }
        final int seperatorIndex = path.lastIndexOf(".");
        if (seperatorIndex == -1 || seperatorIndex == path.length() - 1) {
            throw new UnknowFileTypeException("No extension found for file: " + path);
        }
        final String extension = path.substring(seperatorIndex);
        final Importer importer = extensionToImporter.get(extension);
        if (importer == null) {
            throw new UnknowFileTypeException("No extension found for file: " + path);
        }
        Document document = importer.importFile(file);
        documents.add(document);
    }

    List<Document> contents() {
        return documents;
    }


}
