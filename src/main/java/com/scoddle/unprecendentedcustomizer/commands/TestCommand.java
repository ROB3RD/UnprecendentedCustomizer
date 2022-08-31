package com.scoddle.unprecendentedcustomizer.commands;

import com.scoddle.unprecendentedcustomizer.gui.events.PlayerJoinGui;
import com.scoddle.unprecendentedcustomizer.gui.test.TestGui;
import com.scoddle.unprecendentedcustomizer.utils.reference.ICMD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestCommand implements ICMD {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length != 0) {

                if(args[0].equals("testgui")) {
                    TestGui gui = new TestGui();
                    gui.createGui(player, null);
                    player.openInventory(gui.getGui());
                }
                else if(args[0].equals("join_event")) {
                    PlayerJoinGui.instance.createGui(player, null);
                    player.openInventory(PlayerJoinGui.instance.getGui());
                }

            }
            else {
                methods.sendMessage("&7&lPLEase sPecIfy whIch gUi tO oPEn", player);
            }
        }
        else {
            methods.sendConsoleMessage("");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {

        if(args.length == 1) {
            List<String> argz = new ArrayList<>();
            argz.add("testgui");
            argz.add("join_event");

            return argz;
        }

        return null;
    }
}
