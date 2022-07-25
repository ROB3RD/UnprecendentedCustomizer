package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.gui.PlayerCustomizerGui;
import com.scoddle.unprecendentedcustomizer.gui.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.entity.Player;
import org.bukkit.event.EventException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.Objects;

public class InventoryClickListener implements Listener, IReference {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        for(IGUI guis : plugin.getGuiManager().getGuis()) {
            guis.onClick(e);
        }


    }
}
