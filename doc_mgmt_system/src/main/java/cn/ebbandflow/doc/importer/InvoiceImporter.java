package cn.ebbandflow.doc.importer;

import cn.ebbandflow.doc.Document;
import cn.ebbandflow.doc.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static cn.ebbandflow.doc.Attributes.*;

public class InvoiceImporter implements Importer {

    @Override
    public Document importFile(File file) throws IOException {
        TextFile textFile = new TextFile(file);
        textFile.addLineSuffix(NAME_PREFIX, PATIENT);
        textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);
        final Map<String, String> attributes = textFile.getAttributes();
        attributes.put(TYPE,"INVOICE");
        return new Document(attributes);
    }
}
