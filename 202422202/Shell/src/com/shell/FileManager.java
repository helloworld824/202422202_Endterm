package com.shell;

import java.io.IOException;

public interface FileManager {
    void FileWrite(String filename, String content) throws IOException;
    void FileAppend(String filename, String content) throws IOException;
    String FileRead(String filename) throws IOException;
}