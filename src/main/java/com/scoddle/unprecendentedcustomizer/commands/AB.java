package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AB implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player) {

            Player player = (Player) sender;

            lang.msg("error", player);
            lang.msg("no-perm", player);
            lang.msg("player-not-exist", player);
            lang.msg("player-required", player);
            lang.msg("wrong-usage", player);
            lang.msg("admin-success", player);

        } else {
            lang.console("no-perm");
        }

        return true;
    }
}
