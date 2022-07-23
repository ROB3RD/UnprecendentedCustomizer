package com.scoddle.unprecendentedcustomizer.gui;

import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerCustomizerGui extends IGUI {

    ItemStack gamemode;
    public static PlayerCustomizerGui instance = new PlayerCustomizerGui();

    @Override
    public void createGui(Player p, int size, String title) {
        super.createGui(p, size, title);

        addItem(gamemode, "Test", Material.LIME_WOOL);
        getGui().addItem(gamemode);
    }

    @Override
    protected void addItems() {

    }

    @Override
    protected void addItemMeta() {

    }

    @Override
    protected void setDisplayName() {

    }

    @Override
    protected void setItemMeta() {

    }
}
