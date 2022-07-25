package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.naming.Name;

public class AdminCommand implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length != 1) {
                methods.sendMessage("", player);
                return false;
            }

            Player target;

            try {
                target = server.getPlayerExact(args[0]);
            }catch (NullPointerException exception) {
                methods.sendMessage("", player);
                return false;
            }

            PersistentDataContainer dataContainer;
            try {
                dataContainer = target.getPersistentDataContainer();
            }catch (NullPointerException exception) {
                methods.sendMessage("", player);
                return false;
            }

            int admin;

            try {
                admin = dataContainer.get(new NamespacedKey(plugin, "admin"), PersistentDataType.INTEGER);
            }catch (NullPointerException exception) {
                admin = 0;
            }

            if (admin == 0) {
                dataContainer.set(new NamespacedKey(plugin, "admin"), PersistentDataType.INTEGER, 1);
                methods.sendMessage(methods.translatePlayerName("", target), player);
                return true;
            }else if (admin == 1) {
                dataContainer.set(new NamespacedKey(plugin, "admin"), PersistentDataType.INTEGER, 0);
                methods.sendMessage(methods.translatePlayerName("", target), player);
                return true;
            }

        }else if (sender instanceof ConsoleCommandSender) {

            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;

            if (args.length != 1) {
                methods.sendConsoleMessage("");
                return false;
            }

            Player target;

            try {
                target = server.getPlayerExact(args[0]);
            }catch (NullPointerException exception) {
                methods.sendConsoleMessage("not_exist");
                return false;
            }

            PersistentDataContainer dataContainer;
            try {
                dataContainer = target.getPersistentDataContainer();
            }catch (NullPointerException exception) {
                methods.sendConsoleMessage("error");
                return false;
            }

            int admin;

            try {
                admin = dataContainer.get(new NamespacedKey(plugin, "admin"), PersistentDataType.INTEGER);
            }catch (NullPointerException exception) {
                admin = 0;
            }

            if (admin == 0) {
                dataContainer.set(new NamespacedKey(plugin, "admin"), PersistentDataType.INTEGER, 1);
                methods.sendConsoleMessage(methods.translatePlayerName("admin_message1", target));
                return true;
            }else if (admin == 1) {
                dataContainer.set(new NamespacedKey(plugin, "admin"), PersistentDataType.INTEGER, 0);
                methods.sendConsoleMessage(methods.translatePlayerName("admin_message2", target));
                return true;
            }

        }else return false;
        return true;
    }

}
