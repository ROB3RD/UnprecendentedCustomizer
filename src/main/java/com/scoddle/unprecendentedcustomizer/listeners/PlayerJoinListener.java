package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.gui.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlayerJoinListener implements Listener, IReference {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        String joinMessage;

        try {
            joinMessage = dataContainer.get(new NamespacedKey(plugin, "join-message"), PersistentDataType.STRING);
        }catch (NullPointerException exception) {
            e.setJoinMessage(methods.translate("&a" + player.getDisplayName() + " &7has joined the server."));
            return;
        }

        e.setJoinMessage(methods.translatePlayerName(joinMessage, player));

        //e.setJoinMessage(methods.translate("&a" + player.getDisplayName() + " &7has joined the server."));

        PlayerJoinGui.instance.onPlayerJoin(e);
    }

}
