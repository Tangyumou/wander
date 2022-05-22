package cn.ebbandflow.doc.exception;

import java.io.IOException;

public class UnknowFileTypeException extends IOException {
    public UnknowFileTypeException(String message) {
        super(message);
    }
}
