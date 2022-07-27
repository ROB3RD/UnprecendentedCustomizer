package com.scoddle.unprecendentedcustomizer.utils.reference;

import com.scoddle.unprecendentedcustomizer.UnprecendentedCustomizer;
import com.scoddle.unprecendentedcustomizer.utils.Methods;
import com.scoddle.unprecendentedcustomizer.utils.Perm;
import com.scoddle.unprecendentedcustomizer.utils.files.EventFile;
import com.scoddle.unprecendentedcustomizer.utils.files.LanguageFilesUtils;
import org.bukkit.Bukkit;
import org.bukkit.Server;

public interface IReference {

    UnprecendentedCustomizer plugin = UnprecendentedCustomizer.getPlugin(UnprecendentedCustomizer.class);
    Methods methods = new Methods();

    Server server = Bukkit.getServer();

    EventFile evfile = new EventFile();

    String prefix = plugin.getConfig().getString("prefix");

    LanguageFilesUtils lang = new LanguageFilesUtils();

    Perm perm = new Perm();

    String no_perm = lang.getValue("no-perm");
    String not_exist = lang.getValue("player-not-exist");
    String error = lang.getValue("error");
    String player_req = lang.getValue("player-required");
    String wrong_usage = lang.getValue("wrong-usage");


}
