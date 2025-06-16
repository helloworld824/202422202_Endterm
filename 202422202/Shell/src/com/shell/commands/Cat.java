package com.shell.commands;

import com.shell.Command;
import com.shell.Parser;
import com.shell.env.EnvironmentVariable;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class Cat extends Command {
    public Cat(EnvironmentVariable env) {
        super(env);
    }

    @Override
    public void execute(Parser parser) {
        String[] args = parser.getArgs();
        for (int i = 1; i < args.length; i++) {
            String filename = resolve(args[i]);
            try {
                System.out.print(FileRead(filename));
            } catch (NoSuchFileException e) {
                System.out.println("Shell: File not found " + filename);
                return;
            } catch (IOException e) {
                // 다른 IOException은 상위 메서드로 전달 (예: Shell.java가 catch)
                throw new RuntimeException(e);
            }
        }
    }

    private String resolve(String token) {
        if (token.startsWith("$")) {
            String val = getEnv(token.substring(1));
            return val != null ? val : token;
        }
        return token;
    }
}
