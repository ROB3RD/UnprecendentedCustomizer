package com.scoddle.unprecendentedcustomizer.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Methods {

    public void sendMessage(String text, Player receiver) {
        receiver.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public void sendConsoleMessage(String text) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public void broadcastMessage(String text) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', text));
    }

    public String translate(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
