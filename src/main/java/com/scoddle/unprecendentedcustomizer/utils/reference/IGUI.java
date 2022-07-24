package com.scoddle.unprecendentedcustomizer.utils.reference;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class IGUI {


    public void createGui(Player p, int size, String title) {
        init();
        addItems();
        addItemMeta();
        setDisplayName();
        setItemMeta();
    }

    protected abstract void init();

    protected abstract void addItems();
    protected abstract void addItemMeta();
    protected abstract void setDisplayName();
    protected abstract void setItemMeta();

    protected  void addItem(ItemStack stack, String displayName, Material material) {
        stack.setType(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        stack.setItemMeta(meta);
    }
}
