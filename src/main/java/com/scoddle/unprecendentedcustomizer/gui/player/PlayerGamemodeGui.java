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

public class PlayerGamemodeGui extends IGUI {

    public static PlayerGamemodeGui instance = new PlayerGamemodeGui();

    Inventory gui;

    ItemStack fill;

    ItemStack creative;
    ItemStack survival;
    ItemStack adv;
    ItemStack spec;

    ItemMeta creative_meta;
    ItemMeta survival_meta;
    ItemMeta adv_meta;
    ItemMeta spec_meta;

    public PlayerGamemodeGui() {
        super("Gamemode Switcher", 27);
    }

    @Override
    public void createGui(Player player) {
        gui = Bukkit.createInventory(player, getSize(), getName());

        addItems();
        addItemMeta();
        setDisplayName();
        setItemMeta();
        init();
    }

    @Override
    protected void init() {
        fill(fill, gui);

        gui.setItem(10, creative);
        gui.setItem(12, survival);
        gui.setItem(14, adv);
        gui.setItem(16, spec);
    }

    @Override
    protected void addItems() {

        fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

        creative = new ItemStack(Material.STONE);
        survival = new ItemStack(Material.GRASS_BLOCK);
        adv = new ItemStack(Material.STONE_SWORD);
        spec = new ItemStack(Material.ENDER_EYE);
    }

    @Override
    protected void addItemMeta() {
        creative_meta = creative.getItemMeta();
        survival_meta = survival.getItemMeta();
        adv_meta = adv.getItemMeta();
        spec_meta = spec.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        creative_meta.setDisplayName(methods.translate("&aCreative"));
        survival_meta.setDisplayName(methods.translate("&aSurvival"));
        adv_meta.setDisplayName(methods.translate("&aAdventure"));
        spec_meta.setDisplayName(methods.translate("&aSpectator"));
    }

    @Override
    protected void setItemMeta() {
        creative.setItemMeta(creative_meta);
        survival.setItemMeta(survival_meta);
        adv.setItemMeta(adv_meta);
        spec.setItemMeta(spec_meta);
    }

    @Override
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(getName())) {
            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {

                case STONE: {
                    evfile.setValue("join-gamemode", "CREATIVE");
                    methods.sendMessage("&aSelected Creative!", player);
                    e.setCancelled(true);
                    break;
                }

                case GRASS_BLOCK: {
                    evfile.setValue("join-gamemode", "SURVIVAL");
                    methods.sendMessage("&aSelected Survival!", player);
                    e.setCancelled(true);
                    break;
                }

                case STONE_SWORD: {
                    evfile.setValue("join-gamemode", "ADVENTURE");
                    methods.sendMessage("&aSelected Adventure!", player);
                    e.setCancelled(true);
                    break;
                }

                case ENDER_EYE: {
                    evfile.setValue("join-gamemode", "SPECTATOR");
                    methods.sendMessage("&aSelected Spectator!", player);
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
