package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatListener implements Listener, IReference {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        for(IGUI guis : plugin.getGuiManager().getGuis()) {
            guis.onChat(e);
        }
    }
}
