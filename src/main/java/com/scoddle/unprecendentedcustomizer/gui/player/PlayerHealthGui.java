package com.scoddle.unprecendentedcustomizer.gui.player;

import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class PlayerHealthGui extends IGUI {

    public static PlayerHealthGui instance = new PlayerHealthGui();

    Inventory gui;

    ItemStack red;
    ItemStack lime;
    ItemStack magenta;

    ItemStack add;
    ItemStack subtract;
    ItemStack multiply;
    ItemStack divide;

    ItemMeta add_meta;
    ItemMeta subtract_meta;
    ItemMeta multiply_meta;
    ItemMeta divide_meta;

    public PlayerHealthGui() {
        super("Player Health", 27);
    }

    @Override
    public void createGui(Player player, String path) {
        gui = Bukkit.createInventory(player, getSize(), getName());
        super.createGui(player, path);
    }

    @Override
    protected void init() {

        fill(red, getGui(), 0, 8, 13, 18, 26);

        fill(lime, getGui(), 2, 6, 9, 17, 20, 24);
        column(lime, getGui(), 1, getSize());
        column(lime, getGui(), 7, getSize());

        fill(magenta, getGui(), 3, 4, 5, 11, 15, 21, 22, 23);

        gui.setItem(10, add);
        gui.setItem(12, subtract);
        gui.setItem(14, multiply);
        gui.setItem(16, divide);
    }

    @Override
    protected void addItems() {
        red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        lime = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        magenta = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);

        add = new ItemStack(Material.COBBLESTONE);
        subtract = new ItemStack(Material.LEVER);
        multiply = new ItemStack(Material.COBWEB);
        divide = new ItemStack(Material.ARROW);
    }

    @Override
    protected void addItemMeta() {
        add_meta = add.getItemMeta();
        subtract_meta = subtract.getItemMeta();
        multiply_meta = multiply.getItemMeta();
        divide_meta = divide.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        add_meta.setDisplayName(methods.translate("&aAdd (+)"));
        subtract_meta.setDisplayName(methods.translate("&cSubtract (-)"));
        multiply_meta.setDisplayName(methods.translate("&dMultiply (*)"));
        divide_meta.setDisplayName(methods.translate("&bDivide (/)"));
    }

    @Override
    protected void setItemMeta() {
        add.setItemMeta(add_meta);
        subtract.setItemMeta(subtract_meta);
        multiply.setItemMeta(multiply_meta);
        divide.setItemMeta(divide_meta);
    }

    boolean capture_add = false;
    boolean capture_subtract = false;
    boolean capture_multiply = false;
    boolean capture_divide = false;

    @Override
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(getName())) {
            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {
                case COBBLESTONE: {

                    player.closeInventory();
                    methods.sendMessage("&7Enter amount to add to the player's health", player);
                    capture_add = true;

                    e.setCancelled(true);
                    break;
                }

                case LEVER: {

                    player.closeInventory();
                    methods.sendMessage("&7Enter amount to subtract from the player's health", player);
                    capture_subtract = true;

                    e.setCancelled(true);
                    break;
                }

                case COBWEB: {

                    player.closeInventory();
                    methods.sendMessage("&7Enter amount to multiply the player's health", player);
                    capture_multiply = true;

                    e.setCancelled(true);
                    break;
                }

                case ARROW: {

                    player.closeInventory();
                    methods.sendMessage("&7Enter amount to divide the player's health by", player);
                    capture_divide = true;

                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    @Override
    public void onChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage();
        Player player = e.getPlayer();

        if(capture_add) {

            evfile.setValue("join-add", message);

            capture_add = false;
            e.setCancelled(true);

            methods.sendMessage("&7When a player joins, their health will be &aadded &7by: &b" + message, player);
        }

        if(capture_subtract) {

            evfile.setValue("join-subtract", message);

            capture_subtract = false;
            e.setCancelled(true);

            methods.sendMessage("&7When a player joins, their health will be &asubtracted &7by: &b" + message, player);
        }

        if(capture_multiply) {

            evfile.setValue("join-multiply", message);

            capture_multiply = false;
            e.setCancelled(true);

            methods.sendMessage("&7When a player joins, their health will be &amultiplied &7by: &b" + message, player);
        }

        if(capture_divide) {

            evfile.setValue("join-divide", message);

            capture_divide = false;
            e.setCancelled(true);

            methods.sendMessage("&7When a player joins, their health will be &adivided &7by: &b" + message, player);
        }
    }

    public Inventory getGui() {
        return gui;
    }
}
