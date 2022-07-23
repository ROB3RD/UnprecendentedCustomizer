package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.gui.PlayerCustomizerGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TestCommand implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            PlayerCustomizerGui.instance.createGui(player, 27, "Test Gui");

            player.openInventory(PlayerCustomizerGui.instance.getGui());
        }
        else {
            methods.sendConsoleMessage(player_req);
        }

        return true;
    }
}
