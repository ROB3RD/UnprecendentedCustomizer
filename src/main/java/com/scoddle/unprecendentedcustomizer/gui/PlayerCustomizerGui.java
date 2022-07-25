package com.scoddle.unprecendentedcustomizer.gui;

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

    ItemStack fill;

    ItemStack gamemode;
    ItemStack effect;

    ItemMeta gm_meta;
    ItemMeta effect_meta;

    public static PlayerCustomizerGui instance = new PlayerCustomizerGui();

    public PlayerCustomizerGui() {
        super("Player Properties", 54);
    }

    @Override
    public void createGui(Player p) {
        gui = Bukkit.createInventory(p, getSize(), getName());

        addItems();
        addItemMeta();
        setDisplayName();
        setItemMeta();

        //addItem(gamemode, "Test", Material.LIME_WOOL);
        init();
    }

    @Override
    protected void init() {

        fill(fill, gui);

        gui.setItem(47, gamemode);
        gui.setItem(29, effect);
    }

    @Override
    protected void addItems() {

        fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        gamemode = new ItemStack(Material.STONE);
        effect = new ItemStack(Material.POTION);
    }

    @Override
    protected void addItemMeta() {
        gm_meta = gamemode.getItemMeta();
        effect_meta = effect.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        gm_meta.setDisplayName(methods.translate("&r&bGamemode"));
        effect_meta.setDisplayName(methods.translate("&r&1Potion Effect"));
    }

    @Override
    protected void setItemMeta() {
        gamemode.setItemMeta(gm_meta);
        effect.setItemMeta(effect_meta);
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

                    methods.sendMessage("&bChanges gamemode of player who joined :DDDD (doesnt work xD)", player);

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
