package com.shell.commands;

import com.shell.Command;
import com.shell.Parser;
import com.shell.env.EnvironmentVariable;

public class Echo extends Command {
	public Echo(EnvironmentVariable env) {
		super(env);
	}

	@Override
	public void execute(Parser parser) {
	    String[] args = parser.getArgs();
	    try {
	        for (int i = 1; i < args.length; i++) {
	            if (args[i].equals(">") || args[i].equals(">>")) {
	                StringBuilder sb = new StringBuilder();
	                for (int j = 1; j < i; j++) {
	                    sb.append(resolve(args[j])).append(" ");
	                }
	                String filename = args[i + 1];
	                String output = sb.toString().stripTrailing() + "\n";
	                if (args[i].equals(">")) {
	                    FileWrite(filename, output);
	                } else {
	                    FileAppend(filename, output);
	                }
	                return;
	            }
	        }

	        // 리디렉션 없을 경우 그냥 출력
	        for (int i = 1; i < args.length; i++) {
	            System.out.print(resolve(args[i]) + " ");
	        }
	        System.out.println();

	    } catch (Exception e) {
	        // 메시지 출력 제거 → 예외는 main에서 처리
	        throw new RuntimeException(e);
	    }
	}


	private String resolve(String token) {
		if (token.startsWith("$")) {
			String val = getEnv(token.substring(1));
			if (val == null) {
				throw new RuntimeException("undefined variable: " + token);
			}
			return val;
		}
		return token;
	}

}