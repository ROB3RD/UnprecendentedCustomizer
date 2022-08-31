package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.utils.files.XFile;
import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Reload implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        Player player = (Player) sender;

        if(player.hasPermission(perm.addPerm("reload"))) {

            //evfile.reload();
            plugin.reloadConfig();
            lang.reload();

            for(XFile x : plugin.getxFileManager().getFiles()) {
                x.reload();
            }

            methods.sendMessage("&aReloaded &cU&bC!", player);
        }
        else {
            methods.sendMessage(no_perm, player);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
