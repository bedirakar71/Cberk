package com.cberkacik.client.module;

public class Module {
    private String name;
    private Kind kind;
    private boolean enabled;

    public Module(String name, Kind kind) {
        this.name = name;
        this.kind = kind;
        this.enabled = false;
    }

    public String getName() {
        return name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void toggle() {
        this.enabled = !this.enabled;
    }

    public enum Kind {
        jb6475520,
        other
    }
}
