package com.scoddle.unprecendentedcustomizer.gui.player;

import com.scoddle.unprecendentedcustomizer.utils.files.XFile;
import com.scoddle.unprecendentedcustomizer.utils.files.eventfiles.PlayerJoinEventFile;
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

    ItemStack blue;
    ItemStack light_blue;
    ItemStack green;
    ItemStack lime;
    ItemStack gray;
    ItemStack magenta;
    ItemStack purple;
    ItemStack yellow;
    ItemStack orange;
    ItemStack white;

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
    public void createGui(Player player, String path) {
        gui = Bukkit.createInventory(player, getSize(), getName());

        addItems();
        addItemMeta();
        setDisplayName();
        setItemMeta();
        init();
    }

    @Override
    protected void init() {

        fill(blue, gui, 0, 18);
        fill(light_blue, gui, 1, 19);
        fill(green, gui, 2, 20);
        fill(lime, gui, 3, 21);
        fill(gray, gui, 4, 22);
        fill(magenta, gui, 5, 23);
        fill(purple, gui, 6, 24);
        fill(yellow, gui, 7, 25);
        fill(orange, gui, 8, 26);

        row(white, gui, 9);

        gui.setItem(10, creative);
        gui.setItem(12, survival);
        gui.setItem(14, adv);
        gui.setItem(16, spec);
    }

    @Override
    protected void addItems() {

        blue = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        light_blue = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        green = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        lime = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        gray = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        magenta = new ItemStack(Material.MAGENTA_STAINED_GLASS_PANE);
        purple = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
        yellow = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        orange = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
        white = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

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

    PlayerCustomizerGui playerCustomizerGui = new PlayerCustomizerGui();

    @Override
    public void onClick(InventoryClickEvent e) {

        XFile file = plugin.getxFileManager().getFileByClass(PlayerJoinEventFile.class);

        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(getName())) {
            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {

                case STONE: {
                    file.setValue(playerCustomizerGui.getPath() + "-gamemode", "CREATIVE");
                    methods.sendMessage("&aSelected Creative!", player);
                    e.setCancelled(true);
                    break;
                }

                case GRASS_BLOCK: {
                    file.setValue(playerCustomizerGui.getPath() + "-gamemode", "SURVIVAL");
                    methods.sendMessage("&aSelected Survival!", player);
                    e.setCancelled(true);
                    break;
                }

                case STONE_SWORD: {
                    file.setValue(playerCustomizerGui.getPath() + "-gamemode", "ADVENTURE");
                    methods.sendMessage("&aSelected Adventure!", player);
                    e.setCancelled(true);
                    break;
                }

                case ENDER_EYE: {
                    file.setValue(playerCustomizerGui.getPath() + "-gamemode", "SPECTATOR");
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
