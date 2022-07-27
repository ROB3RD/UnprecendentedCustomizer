package com.scoddle.unprecendentedcustomizer;

import com.scoddle.unprecendentedcustomizer.commands.AB;
import com.scoddle.unprecendentedcustomizer.commands.AdminCommand;
import com.scoddle.unprecendentedcustomizer.commands.Reload;
import com.scoddle.unprecendentedcustomizer.commands.TestCommand;
import com.scoddle.unprecendentedcustomizer.listeners.InventoryClickListener;
import com.scoddle.unprecendentedcustomizer.listeners.PlayerChatListener;
import com.scoddle.unprecendentedcustomizer.listeners.PlayerJoinListener;
import com.scoddle.unprecendentedcustomizer.utils.GuiManager;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UnprecendentedCustomizer extends JavaPlugin implements IReference {

    private GuiManager guiManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        evfile.setup();
        evfile.get().options().copyDefaults();
        evfile.save();

        lang.setup();
        System.out.println("Setup files!");

        guiManager = new GuiManager();

        regListener(new PlayerJoinListener());
        regListener(new InventoryClickListener());
        regListener(new PlayerChatListener());

        addCommand("test", new TestCommand());
        addCommand("admin", new AdminCommand());
        addCommand("ucrl", new Reload());
        addCommand("AB", new AB());

        System.out.println("UC lodaded!");

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
