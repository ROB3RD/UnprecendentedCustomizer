package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        if(player.hasPermission(perm.addPerm("reload"))) {

            evfile.reload();
            plugin.reloadConfig();
            lang.reload();

            methods.sendMessage("&aReloaded &cU&bC!", player);
        }
        else {
            methods.sendMessage(no_perm, player);
        }

        return true;
    }
}
