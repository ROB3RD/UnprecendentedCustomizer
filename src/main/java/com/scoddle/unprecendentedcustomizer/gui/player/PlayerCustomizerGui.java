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

public class PlayerCustomizerGui extends IGUI {

    Inventory gui;

    ItemStack red;
    ItemStack pink;
    ItemStack magenta;
    ItemStack purple;
    ItemStack light_blue;
    ItemStack blue;

    ItemStack gamemode;
    ItemStack effect;
    ItemStack health;

    ItemMeta gm_meta;
    ItemMeta effect_meta;
    ItemMeta health_meta;

    public PlayerCustomizerGui() {
        super("Player Properties", 54);
    }

    @Override
    public void createGui(Player p, String path) {
        gui = Bukkit.createInventory(p, getSize(), getName());

        addItems();
        addItemMeta();
        setDisplayName();
        setItemMeta();
        init();
    }

    @Override
    protected void init() {
        row(red, gui, 0);
        row(pink, gui, 9);
        row(magenta, gui, 18);
        row(purple, gui, 27);
        row(light_blue, gui, 36);
        row(blue, gui, 45);

        gui.setItem(38, gamemode);
        gui.setItem(11, effect);
        gui.setItem(15, health);
    }

    @Override
    protected void addItems() {
        red = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        pink = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
        magenta = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        purple = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        light_blue = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        blue = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);

        gamemode = new ItemStack(Material.STONE);
        effect = new ItemStack(Material.POTION);
        health = new ItemStack(Material.RED_DYE);
    }

    @Override
    protected void addItemMeta() {
        gm_meta = gamemode.getItemMeta();
        effect_meta = effect.getItemMeta();
        health_meta = health.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        gm_meta.setDisplayName(methods.translate("&r&bGamemode"));
        effect_meta.setDisplayName(methods.translate("&r&1Potion Effect"));
        health_meta.setDisplayName(methods.translate("&cHealth"));
    }

    @Override
    protected void setItemMeta() {
        gamemode.setItemMeta(gm_meta);
        effect.setItemMeta(effect_meta);
        health.setItemMeta(health_meta);
    }

    @Override
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase("Player Properties")) {

            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {

                case POTION: {

                    methods.sendMessage("Gives player who joined a potion effect :DDDD (doesnt work xD)", player);

                    e.setCancelled(true);
                    break;
                }

                case STONE: {

                    player.closeInventory();
                    PlayerGamemodeGui.instance.createGui(player, getPath());
                    player.openInventory(PlayerGamemodeGui.instance.getGui());

                    e.setCancelled(true);
                    break;
                }

                case RED_DYE: {

                    player.closeInventory();
                    PlayerHealthGui.instance.createGui(player, getPath());
                    player.openInventory(PlayerHealthGui.instance.getGui());

                    e.setCancelled(true);
                    break;
                }

                case GRAY_STAINED_GLASS_PANE: {
                    e.setCancelled(true);
                    break;
                }
            }
        }
    }

    @Override
    public void onChat(AsyncPlayerChatEvent e) {

    }

    public Inventory getGui() {
        return gui;
    }
}
