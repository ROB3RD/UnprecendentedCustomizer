package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class AdminCommand implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {

            Player player = (Player) sender;

            if (args.length != 1) {
                methods.sendMessage("", player);
                return false;
            }

        }else if (sender instanceof ConsoleCommandSender) {

            ConsoleCommandSender ccs = (ConsoleCommandSender) sender;

            if (args.length != 1) {
                methods.sendConsoleMessage("");
                return false;
            }



        }else return false;
        return true;
    }

}
