package com.shell;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.shell.env.EnvironmentVariable;

public abstract class Command implements FileManager {
    protected EnvironmentVariable env;

    public Command(EnvironmentVariable env) {
        this.env = env;
    }

    public abstract void execute(Parser parser);

    protected String getEnv(String key) {
        return env.getValue(key);
    }

    protected boolean isEnv(String str) {
        return str != null && env.getValue(str) != null;
    }

    @Override
    public void FileWrite(String filename, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        }
    }

    @Override
    public void FileAppend(String filename, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(content);
        }
    }

    @Override
    public String FileRead(String filename) throws IOException {
        return Files.readString(Paths.get(filename));
    }
}