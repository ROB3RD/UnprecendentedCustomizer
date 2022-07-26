package com.scoddle.unprecendentedcustomizer;

import com.nthbyte.dialogue.DialogueAPI;
import com.scoddle.unprecendentedcustomizer.commands.TestCommand;
import com.scoddle.unprecendentedcustomizer.listeners.InventoryClickListener;
import com.scoddle.unprecendentedcustomizer.listeners.PlayerChatListener;
import com.scoddle.unprecendentedcustomizer.listeners.PlayerJoinListener;
import com.scoddle.unprecendentedcustomizer.utils.GuiManager;
import com.scoddle.unprecendentedcustomizer.utils.LanguageFilesUtils;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UnprecendentedCustomizer extends JavaPlugin {

    private GuiManager guiManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        guiManager = new GuiManager();

        DialogueAPI.hook(this);

        //LanguageFilesUtils.loadValues();

        regListener(new PlayerJoinListener());
        regListener(new InventoryClickListener());
        regListener(new PlayerChatListener());

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

    public GuiManager getGuiManager() {
        return guiManager;
    }
}
