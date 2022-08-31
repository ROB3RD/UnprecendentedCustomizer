package com.scoddle.unprecendentedcustomizer.gui.test;

import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TestGui extends IGUI {

    Inventory gui;

    ItemStack test;
    ItemStack test1;
    ItemStack test2;

    ItemMeta meta;
    ItemMeta meta1;
    ItemMeta meta2;

    public TestGui() {
        super("Test Gui", 54);
    }

    @Override
    public void createGui(Player player, String path) {

        gui = Bukkit.createInventory(player, getSize(), getName());

        super.createGui(player, path);
    }

    @Override
    protected void init() {
        column(test, gui, 0, getSize());
        column(test1, gui, 2, getSize());
        column(test2, gui, 7, getSize());
    }

    @Override
    protected void addItems() {
        test = new ItemStack(Material.GOLDEN_SHOVEL);
        test1 = new ItemStack(Material.DARK_OAK_PRESSURE_PLATE);
        test2 = new ItemStack(Material.CARROT);
    }

    @Override
    protected void addItemMeta() {
        meta = test.getItemMeta();
        meta1 = test1.getItemMeta();
        meta2 = test2.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        meta.setDisplayName("TEST");
        meta1.setDisplayName("TEST 1");
        meta2.setDisplayName("TEST 2");
    }

    @Override
    protected void setItemMeta() {
        test.setItemMeta(meta);
        test1.setItemMeta(meta1);
        test2.setItemMeta(meta2);
    }

    @Override
    public void onClick(InventoryClickEvent e) {

    }

    @Override
    public void onChat(AsyncPlayerChatEvent e) {

    }

    public Inventory getGui() {
        return gui;
    }
}
