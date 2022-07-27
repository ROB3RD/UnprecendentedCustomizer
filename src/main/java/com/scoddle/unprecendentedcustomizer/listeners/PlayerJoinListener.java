package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener, IReference {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        e.setJoinMessage(methods.translatePlayerName(evfile.getValue("join-msg"), player));

        if(evfile.getValue("join-gamemode").equalsIgnoreCase("CREATIVE")) {
            player.setGameMode(GameMode.CREATIVE);
        }
        else if(evfile.getValue("join-gamemode").equalsIgnoreCase("SURVIVAL")) {
            player.setGameMode(GameMode.SURVIVAL);
        }
        else if(evfile.getValue("join-gamemode").equalsIgnoreCase("ADVENTURE")) {
            player.setGameMode(GameMode.ADVENTURE);
        }
        else if(evfile.getValue("join-gamemode").equalsIgnoreCase("SPECTATOR")) {
            player.setGameMode(GameMode.SPECTATOR);
        }

        if(evfile.getValue("on-join-msg").equalsIgnoreCase("true")) {
            player.sendMessage("MESSAGE ON JOIN");
        }
    }

}
