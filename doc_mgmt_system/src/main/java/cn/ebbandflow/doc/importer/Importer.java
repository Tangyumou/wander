package cn.ebbandflow.doc.importer;

import cn.ebbandflow.doc.Document;

import java.io.File;
import java.io.IOException;

public interface Importer {
    Document importFile(File file) throws IOException;
}
