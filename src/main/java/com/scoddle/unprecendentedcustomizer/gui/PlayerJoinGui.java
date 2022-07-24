package com.scoddle.unprecendentedcustomizer.gui;

import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerJoinGui extends IGUI {

    public static PlayerJoinGui instance = new PlayerJoinGui();

    Inventory gui;

    ItemStack set_join;
    ItemStack get_join;

    ItemMeta join_meta;
    ItemMeta get_join_meta;

    @Override
    public void createGui(Player p, int size, String title) {
        gui = Bukkit.createInventory(p, size, title);

        super.createGui(p, size, title);
    }

    @Override
    protected void init() {
        gui.addItem(set_join);
        gui.addItem(get_join);
    }

    @Override
    protected void addItems() {
        set_join = new ItemStack(Material.PAPER);
        get_join = new ItemStack(Material.CARROT_ON_A_STICK);
    }

    @Override
    protected void addItemMeta() {
        join_meta = set_join.getItemMeta();
        get_join_meta = get_join.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        join_meta.setDisplayName("");
    }

    @Override
    protected void setItemMeta() {
        set_join.setItemMeta(join_meta);
        get_join.setItemMeta(get_join_meta);
    }

    @Override
    public void onClick(InventoryClickEvent e) {

    }
}
