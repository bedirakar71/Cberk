package com.cberkacik.client.module;

import java.util.ArrayList;
import java.util.List;

public class Mm {
    private static final List<Module> modules = new ArrayList<>();

    public static void init() {
        modules.add(new Fa());
        modules.add(new As());
        modules.add(new Tg());
    }

    public static List<Module> getModules() {
        return modules;
    }
}
