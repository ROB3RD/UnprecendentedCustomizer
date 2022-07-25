package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.gui.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener, IReference {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        //e.setJoinMessage(methods.translate("&a" + player.getDisplayName() + " &7has joined the server."));

        PlayerJoinGui.instance.onPlayerJoin(e);
    }

}
