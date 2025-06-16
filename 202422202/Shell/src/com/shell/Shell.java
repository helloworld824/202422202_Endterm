package com.shell;

import com.shell.env.EnvironmentVariable;
import com.shell.commands.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Shell {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EnvironmentVariable env = new EnvironmentVariable();
        ArrayList<Command> cmdList = new ArrayList<>();
        History history = new History(env);
        cmdList.add(new Echo(env));
        cmdList.add(new Cat(env));
        cmdList.add(history);

        while (true) {
            System.out.print("$> ");
            String input = scanner.nextLine();

            if (input.trim().equalsIgnoreCase("exit")) break;

            Parser parser = new Parser(input);
            history.add(input);

            boolean executed = false;
            for (Command cmd : cmdList) {
                if (parser.getCommand().equalsIgnoreCase(cmd.getClass().getSimpleName().toLowerCase())) {
                    try {
                        cmd.execute(parser);
                    } catch (Exception e) {
                        System.out.println("Shell: Fail to execute " + parser.getCommand());
                    }
                    executed = true;
                    break;
                }
            }

            if (!executed) {
                if (parser.isEnv()) {
                    String[] tokens = parser.getArgs();
                    if (tokens.length >= 2) {
                        env.addEnv(tokens[0], tokens[1]);
                    }
                } else {
                    System.out.println("Shell: command not found (" + parser.getCommand() + ")");
                }
            }
        }
        scanner.close();
    }
}
