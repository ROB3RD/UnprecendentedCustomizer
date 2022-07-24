package com.scoddle.unprecendentedcustomizer.utils.reference;

import com.scoddle.unprecendentedcustomizer.UnprecendentedCustomizer;
import com.scoddle.unprecendentedcustomizer.utils.LanguageFilesUtils;
import com.scoddle.unprecendentedcustomizer.utils.Methods;

public interface IReference {

    UnprecendentedCustomizer plugin = UnprecendentedCustomizer.getPlugin(UnprecendentedCustomizer.class);
    Methods methods = new Methods();

    String no_perm = LanguageFilesUtils.getValue(plugin.getConfig().getString("language"), "no-perm");
    String not_exist = LanguageFilesUtils.getValue(plugin.getConfig().getString("language"), "player-not-exist");
    String error = LanguageFilesUtils.getValue(plugin.getConfig().getString("language"), "error");
    String player_req = LanguageFilesUtils.getValue(plugin.getConfig().getString("language"), "player-required");
    String wrong_usage = LanguageFilesUtils.getValue(plugin.getConfig().getString("language"), "wrong-usage");

}
