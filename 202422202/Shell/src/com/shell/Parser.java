package com.shell;

public class Parser {
    private String[] args;
    private String command;
    private String commandInput;
    private boolean isEnv = false;

    public Parser(String input) {
        this.commandInput = input;
        if (input.contains("=")) {
            isEnv = true;
        }
        parsing();
    }

    public void parsing() {
        this.args = commandInput.split("\\s+|=");
        if (args.length > 0) {
            this.command = args[0];
        }
    }

    public int nr_args() {
        return args.length;
    }

    public String[] getArgs() {
        return args;
    }

    public String getCommand() {
        return command;
    }

    public boolean isEnv() {
        return isEnv;
    }

    public String getRawInput() {
        return commandInput;
    }
}
