package com.shell.env;

import java.util.HashMap;

public class EnvironmentVariable {
    private HashMap<String, String> envs;

    public EnvironmentVariable() {
        envs = new HashMap<>();
    }

    public void addEnv(String key, String value) {
        envs.put(key, value);
    }

    public String getValue(String key) {
        return envs.getOrDefault(key, null);
    }
}
