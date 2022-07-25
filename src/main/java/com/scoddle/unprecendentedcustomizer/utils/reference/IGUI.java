package com.scoddle.unprecendentedcustomizer.utils.reference;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class IGUI implements IReference{

    String name;
    int size;

    public IGUI(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void createGui(Player player) {
    }

    protected abstract void init();

    protected abstract void addItems();
    protected abstract void addItemMeta();
    protected abstract void setDisplayName();
    protected abstract void setItemMeta();
    public abstract void onClick(InventoryClickEvent e);
    public abstract void onChat(AsyncPlayerChatEvent e);

    protected void fill(ItemStack m, Inventory gui) {
        for(int i = 0; i < getSize(); i++) {
            gui.setItem(i, m);
        }
    }

    protected void addItem(ItemStack stack, String displayName, Material material) {
        stack.setType(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(displayName);
        stack.setItemMeta(meta);
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
