package com.scoddle.unprecendentedcustomizer;

import com.scoddle.unprecendentedcustomizer.commands.TestCommand;
import com.scoddle.unprecendentedcustomizer.listeners.PlayerJoinListener;
import com.scoddle.unprecendentedcustomizer.utils.LanguageFilesUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UnprecendentedCustomizer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        LanguageFilesUtils.loadValues();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        regListener(new PlayerJoinListener());

        addCommand("test", new TestCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void addCommand(String name, CommandExecutor executor) {
        getCommand(name).setExecutor(executor);
    }

    private void regListener(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }
}
