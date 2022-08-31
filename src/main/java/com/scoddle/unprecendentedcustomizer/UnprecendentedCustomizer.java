package com.scoddle.unprecendentedcustomizer;

import com.scoddle.unprecendentedcustomizer.commands.AB;
import com.scoddle.unprecendentedcustomizer.commands.AdminCommand;
import com.scoddle.unprecendentedcustomizer.commands.Reload;
import com.scoddle.unprecendentedcustomizer.commands.TestCommand;
import com.scoddle.unprecendentedcustomizer.listeners.InventoryClickListener;
import com.scoddle.unprecendentedcustomizer.listeners.PlayerChatListener;
import com.scoddle.unprecendentedcustomizer.listeners.PlayerJoinListener;
import com.scoddle.unprecendentedcustomizer.utils.GuiManager;
import com.scoddle.unprecendentedcustomizer.utils.files.XFile;
import com.scoddle.unprecendentedcustomizer.utils.files.XFileManager;
import com.scoddle.unprecendentedcustomizer.utils.files.eventfiles.PlayerJoinEventFile;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class UnprecendentedCustomizer extends JavaPlugin implements IReference {

    private GuiManager guiManager;
    private XFileManager xFileManager;

    @Override
    public void onEnable() {
        // Plugin startup logic

        xFileManager = new XFileManager();
        guiManager = new GuiManager();

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        /*evfile.setup();
        evfile.get().options().copyDefaults();
        evfile.save();*/

        lang.setup();

        XFile testFile = getxFileManager().getFileByClass(PlayerJoinEventFile.class);
/*        System.out.println(testFile.getFileName());
        System.out.println(testFile.getFile());
        System.out.println(testFile.get());     */

        System.out.println();

        for(XFile files : xFileManager.getFiles()) {
            files.setup();
            files.get().options().copyDefaults();
            files.save();

            // Debug
            System.out.println(files.get().toString());
        }

        System.out.println("Setup files!");

        regListener(new PlayerJoinListener());
        regListener(new InventoryClickListener());
        regListener(new PlayerChatListener());

        addCommand("test", new TestCommand());
        addCommand("admin", new AdminCommand());
        addCommand("ucrl", new Reload());
        addCommand("AB", new AB());

        System.out.println("UC loaded!");

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

    public XFileManager getxFileManager() {
        return xFileManager;
    }
}
