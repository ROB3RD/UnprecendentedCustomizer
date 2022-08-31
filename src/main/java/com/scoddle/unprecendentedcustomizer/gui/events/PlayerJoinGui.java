package com.scoddle.unprecendentedcustomizer.gui.events;

import com.scoddle.unprecendentedcustomizer.gui.player.PlayerCustomizerGui;
import com.scoddle.unprecendentedcustomizer.utils.files.XFile;
import com.scoddle.unprecendentedcustomizer.utils.files.eventfiles.PlayerJoinEventFile;
import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Objects;

public class PlayerJoinGui extends IGUI {

    public static PlayerJoinGui instance = new PlayerJoinGui();

    XFile file = plugin.getxFileManager().getFileByClass(PlayerJoinEventFile.class);

    Player playr;
    PlayerJoinEvent ev;
    String join_msg;

    boolean capture = false;

    Inventory gui;

    // Fill Items
    ItemStack gray;
    ItemStack yellow;
    ItemStack blue;
    ItemStack light_blue;
    ItemStack cyan;

    // Click Items
    ItemStack set_join;
    ItemStack get_join;
    ItemStack properties;
    ItemStack msg_on_join;

    boolean msg_onjoin = false;

    ItemMeta join_meta;
    ItemMeta get_join_meta;
    SkullMeta prop_meta;
    ItemMeta msg_join_meta;

    public PlayerJoinGui() {
        super("Join Event Config", 54);
    }

    @Override
    public void createGui(Player p, String path) {
        gui = Bukkit.createInventory(p, getSize(), getName());
        addItems();
        addItemMeta();
        setDisplayName();
        prop_meta.setOwningPlayer(p);
        setItemMeta();
        init();
    }

    @Override
    protected void init() {

        fill(yellow, gui, 0, 1, 4, 7, 8, 9, 17, 36, 44, 45, 46, 49, 52, 53);
        fill(light_blue, gui, 2, 6, 12, 13, 14, 18, 26, 27, 35, 39, 40, 41, 47, 51);
        fill(blue, gui, 3, 5, 10, 16, 37, 43, 48, 50);
        fill(cyan, gui, 11, 15, 19, 20, 21, 22, 23, 25, 28, 30, 31, 32, 34, 38, 42);

        gui.setItem(20, set_join);
        gui.setItem(24, properties);
        gui.setItem(29, get_join);
        gui.setItem(45, msg_on_join);
    }

    @Override
    protected void addItems() {
        set_join = new ItemStack(Material.PAPER);
        properties = new ItemStack(Material.PLAYER_HEAD);
        get_join = new ItemStack(Material.CARROT_ON_A_STICK);
        msg_on_join = new ItemStack(Material.GRAY_WOOL);

        gray = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        yellow = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
        blue = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
        light_blue = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        cyan = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
    }

    @Override
    protected void addItemMeta() {
        join_meta = set_join.getItemMeta();
        get_join_meta = get_join.getItemMeta();
        prop_meta = (SkullMeta) properties.getItemMeta();
        msg_join_meta = msg_on_join.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        join_meta.setDisplayName(methods.translate("&r&aSet Join Message"));
        get_join_meta.setDisplayName(methods.translate("&r&cGet Join Message"));

        prop_meta.setDisplayName(methods.translate("&r&7Player Properties"));

        msg_join_meta.setDisplayName(methods.translate("&7Receive Message on Join"));
    }

    @Override
    protected void setItemMeta() {
        set_join.setItemMeta(join_meta);
        get_join.setItemMeta(get_join_meta);
        properties.setItemMeta(prop_meta);
    }

    @Override
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(getName())) {

            switch (Objects.requireNonNull(e.getCurrentItem()).getType()) {

                case PAPER: {
                    player.closeInventory();
                    methods.sendMessage("&7Please type the new Join message in chat.", player);
                    methods.sendMessage("&7You can use <player> to set the name of the player who joined", player);
                    capture = true;
                    e.setCancelled(true);
                    break;
                }

                case CARROT_ON_A_STICK: {

                    methods.sendMessage("Current Join message is: ", player);
                    methods.sendMessage(file.getValue("join-msg"), player);

                    e.setCancelled(true);
                    break;
                }

                case PLAYER_HEAD: {

                    player.closeInventory();
                    PlayerCustomizerGui playerCustomizerGui = new PlayerCustomizerGui();
                    playerCustomizerGui.createGui(player, "join-event");
                    player.openInventory(playerCustomizerGui.getGui());

                    e.setCancelled(true);
                    break;
                }

                case GRAY_WOOL: {

                    if(file.getValue("on-join-msg").equalsIgnoreCase("true")) {
                        file.setValue("on-join-msg", "false");
                        methods.sendMessage("&7Message on join: &coff", player);
                    }
                    else {
                        file.setValue("on-join-msg", "true");
                        methods.sendMessage("&7Message on join: &aon", player);
                    }


                    e.setCancelled(true);
                    break;
                }

                case CYAN_STAINED_GLASS_PANE: {
                    e.setCancelled(true);
                    break;
                }

                case YELLOW_STAINED_GLASS_PANE: {
                    e.setCancelled(true);
                    break;
                }

                case LIGHT_BLUE_STAINED_GLASS_PANE: {
                    e.setCancelled(true);
                    break;
                }

                case BLUE_STAINED_GLASS_PANE: {
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
        String message = e.getMessage();
        Player player = e.getPlayer();

        if(capture) {

            file.setValue("join-msg", message);

            capture = false;
            e.setCancelled(true);

            methods.sendMessage("&aSet new join message to: " + message, player);
        }
    }

    public Inventory getGui() {
        return gui;
    }
}
