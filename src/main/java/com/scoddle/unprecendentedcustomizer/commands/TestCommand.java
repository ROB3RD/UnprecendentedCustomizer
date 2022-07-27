package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.gui.events.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            PlayerJoinGui.instance.createGui(player);

            player.openInventory(PlayerJoinGui.instance.getGui());
        }
        else {
            methods.sendConsoleMessage("");
        }

        return true;
    }
}
