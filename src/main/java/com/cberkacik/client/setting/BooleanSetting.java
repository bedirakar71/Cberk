package com.cberkacik.client.setting;

public class BooleanSetting {
    private String name;
    private boolean enabled;

    public BooleanSetting(String name, boolean defaultValue) {
        this.name = name;
        this.enabled = defaultValue;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }
}
