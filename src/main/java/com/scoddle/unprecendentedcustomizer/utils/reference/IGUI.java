package com.scoddle.unprecendentedcustomizer.utils.reference;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class IGUI implements IReference {

    String name, path;
    int size;

    public IGUI(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void createGui(Player player, String path) {
        this.path = path;
        addItems();
        addItemMeta();
        setDisplayName();
        setItemMeta();
        init();
    }

    protected abstract void init();

    protected abstract void addItems();

    protected abstract void addItemMeta();

    protected abstract void setDisplayName();

    protected abstract void setItemMeta();

    public abstract void onClick(InventoryClickEvent e);

    public abstract void onChat(AsyncPlayerChatEvent e);

    protected void fill(ItemStack m, Inventory gui) {
        for (int i = 0; i < getSize(); i++) {
            gui.setItem(i, m);
        }
    }

    protected void fill(ItemStack is, Inventory gui, int...ints) {
        for(int i : ints) {
            gui.setItem(i, is);
        }
    }

    protected void row(ItemStack is, Inventory inv, int start) {
        int end = start + 9;
        for(int i = start; i < end; i++) {
            inv.setItem(i, is);
        }
    }

    protected void column(ItemStack is, Inventory gui, int column, int size) {

        int next = column;

        if(size == 53 || size == 54) {

            for(int i = 0; i < 6; i++) {
                gui.setItem(next, is);
                next+=9;
            }
        }

        else if(size == 26 || size == 27) {

            for(int i = 0; i < 3; i++) {
                gui.setItem(next, is);
                next+=9;
            }
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

    public String getPath() {
        return path;
    }
}
