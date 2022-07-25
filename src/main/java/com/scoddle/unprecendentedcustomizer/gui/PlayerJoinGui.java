package com.scoddle.unprecendentedcustomizer.gui;

import com.scoddle.unprecendentedcustomizer.listeners.PlayerJoinListener;
import com.scoddle.unprecendentedcustomizer.utils.reference.IGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

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

    ItemMeta join_meta;
    ItemMeta get_join_meta;
    ItemMeta prop_meta;

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
    }

    @Override
    protected void addItems() {
        set_join = new ItemStack(Material.PAPER);
        properties = new ItemStack(Material.PLAYER_HEAD);
        get_join = new ItemStack(Material.CARROT_ON_A_STICK);

        fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
    }

    @Override
    protected void addItemMeta() {
        join_meta = set_join.getItemMeta();
        get_join_meta = get_join.getItemMeta();
        prop_meta = properties.getItemMeta();
    }

    @Override
    protected void setDisplayName() {
        join_meta.setDisplayName(methods.translate("&r&aSet"));
        get_join_meta.setDisplayName(methods.translate("&r&cGet"));
        prop_meta.setDisplayName(methods.translate("&r&7Player Properties"));
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
                    methods.sendMessage("&4Doesnt work yet so fuck off", player);
                    player.closeInventory();
                    methods.sendMessage("&7Please type the new Join message in chat.", player);
                    methods.sendMessage("&7You can use <player> to set the name of the player who joined", player);
                    capture = true;
                    e.setCancelled(true);
                    break;
                }

                case CARROT_ON_A_STICK: {

                    try {
                        methods.sendMessage("Message: " + ev.getJoinMessage(), player);
                    }
                    catch (NullPointerException exc) {
                        methods.sendMessage("&cA player needs to join, for this to work", player);
                    }

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

            PersistentDataContainer dataContainer = playr.getPersistentDataContainer();
            dataContainer.set(new NamespacedKey(plugin, "join-message"), PersistentDataType.STRING, message);

            join_msg = message;
            capture = false;
            e.setCancelled(true);
        }

        methods.sendMessage("Set new join message", player);

    }

    public void onPlayerJoin(PlayerJoinEvent e) {
        ev = e;
        playr = e.getPlayer();
        join_msg = e.getJoinMessage();
        assert join_msg != null;
        e.setJoinMessage(methods.translatePlayerName(join_msg, playr));
    }

    public Inventory getGui() {
        return gui;
    }
}
