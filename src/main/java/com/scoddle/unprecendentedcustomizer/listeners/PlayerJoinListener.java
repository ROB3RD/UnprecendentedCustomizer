package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.utils.files.XFile;
import com.scoddle.unprecendentedcustomizer.utils.files.eventfiles.PlayerJoinEventFile;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener, IReference {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        XFile join = plugin.getxFileManager().getFileByClass(PlayerJoinEventFile.class);

        Player player = e.getPlayer();

        e.setJoinMessage(methods.translatePlayerName(join.getValue("join-msg"), player));

        if(join.getValue("join-gamemode").equalsIgnoreCase("CREATIVE")) {
            player.setGameMode(GameMode.CREATIVE);
        }
        else if(join.getValue("join-gamemode").equalsIgnoreCase("SURVIVAL")) {
            player.setGameMode(GameMode.SURVIVAL);
        }
        else if(join.getValue("join-gamemode").equalsIgnoreCase("ADVENTURE")) {
            player.setGameMode(GameMode.ADVENTURE);
        }
        else if(join.getValue("join-gamemode").equalsIgnoreCase("SPECTATOR")) {
            player.setGameMode(GameMode.SPECTATOR);
        }

        if(join.getValue("on-join-msg").equalsIgnoreCase("true")) {
            player.sendMessage("MESSAGE ON JOIN");
        }

    }

}
