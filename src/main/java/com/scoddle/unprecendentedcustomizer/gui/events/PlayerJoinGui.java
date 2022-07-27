package com.scoddle.unprecendentedcustomizer.gui.events;

import com.scoddle.unprecendentedcustomizer.gui.player.PlayerCustomizerGui;
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

import java.util.Objects;

public class PlayerJoinGui extends IGUI {

    public static PlayerJoinGui instance = new PlayerJoinGui();

    Player playr;
    PlayerJoinEvent ev;
    String join_msg;

    boolean capture = false;

    Inventory gui;

    ItemStack fill;

    ItemStack set_join;
    ItemStack get_join;
    ItemStack properties;
    ItemStack msg_on_join;
    boolean msg_onjoin = false;

    ItemMeta join_meta;
    ItemMeta get_join_meta;
    ItemMeta prop_meta;
    ItemMeta msg_join_meta;

    public PlayerJoinGui() {
        super("Join Event Config", 54);
    }

    @Override
    public void createGui(Player p) {
        gui = Bukkit.createInventory(p, getSize(), getName());
        addItems();
        addItemMeta();
        setDisplayName();
        setItemMeta();
        init();
    }

    @Override
    protected void init() {
        fill(fill, getGui());

        gui.setItem(20, set_join);
        gui.setItem(22, properties);
        gui.setItem(38, get_join);
        gui.setItem(45, msg_on_join);
    }

    @Override
    protected void addItems() {
        set_join = new ItemStack(Material.PAPER);
        properties = new ItemStack(Material.PLAYER_HEAD);
        get_join = new ItemStack(Material.CARROT_ON_A_STICK);
        msg_on_join = new ItemStack(Material.GRAY_WOOL);

        fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    }

    @Override
    protected void addItemMeta() {
        join_meta = set_join.getItemMeta();
        get_join_meta = get_join.getItemMeta();
        prop_meta = properties.getItemMeta();
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
                    methods.sendMessage(evfile.getValue("join-msg"), player);

                    e.setCancelled(true);
                    break;
                }

                case PLAYER_HEAD: {

                    player.closeInventory();
                    PlayerCustomizerGui.instance.createGui(player);
                    player.openInventory(PlayerCustomizerGui.instance.getGui());

                    e.setCancelled(true);
                    break;
                }

                case GRAY_WOOL: {

                    if(evfile.getValue("on-join-msg").equalsIgnoreCase("true")) {
                        evfile.setValue("on-join-msg", "false");
                        methods.sendMessage("&7Message on join: &coff", player);
                    }
                    else {
                        evfile.setValue("on-join-msg", "true");
                        methods.sendMessage("&7Message on join: &aon", player);
                    }


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

            evfile.setValue("join-msg", message);
            evfile.save();

            capture = false;
            e.setCancelled(true);

            methods.sendMessage("&aSet new join message to: " + message, player);
        }
    }

    public Inventory getGui() {
        return gui;
    }
}
