package com.stiggles.items;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    NamespacedKey tagKey = new NamespacedKey(this, "tag");
    NamespacedKey classKey = new NamespacedKey(this, "class");

    public NamespacedKey getTagKey () { return tagKey; }
    public NamespacedKey getClassKey () { return classKey; }
    @Override
    public void onEnable() {
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
