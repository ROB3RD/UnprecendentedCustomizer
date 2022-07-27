package com.scoddle.unprecendentedcustomizer.listeners;

import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener, IReference {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        for(IGUI guis : plugin.getGuiManager().getGuis()) {
            guis.onClick(e);
        }


    }
}
