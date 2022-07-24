package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.gui.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        PlayerJoinGui.instance.onClick(e);
    }
}
