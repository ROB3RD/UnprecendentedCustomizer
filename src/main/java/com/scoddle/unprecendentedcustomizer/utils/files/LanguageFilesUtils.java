package com.scoddle.unprecendentedcustomizer.utils.files;

import com.scoddle.unprecendentedcustomizer.UnprecendentedCustomizer;
import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LanguageFilesUtils implements IReference {

    FileConfiguration translations;
    File defaultLanguageFile;

    HashMap<String, String> translationMap = new HashMap<>();

    public void setup() {
        File languageDirectory = new File(plugin.getDataFolder(), "languages/");
        defaultLanguageFile = new File(plugin.getDataFolder(), "languages/eng.yml");

        if (!languageDirectory.isDirectory()) {
            languageDirectory.mkdir();
        }

        if(!defaultLanguageFile.exists()) {
            InputStream stream = plugin.getResource("eng.yml");
            copyInputStreamToFile(stream, defaultLanguageFile);
        }

        if (plugin.getConfig().getString("lang") != null && !Objects.equals(plugin.getConfig().getString("lang"), "eng")) {
            translations = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "languages/" + plugin.getConfig().getString("lang") + ".yml"));
            for (String translation : translations.getKeys(false)){
                translationMap.put(translation, translations.getString(translation));
            }
        }
        else {
            translations = YamlConfiguration.loadConfiguration(defaultLanguageFile);
            for (String translation : translations.getKeys(false)){
                translationMap.put(translation, translations.getString(translation));
            }
        }

    }

    public void reload() {
        if (plugin.getConfig().getString("lang") != null && !Objects.equals(plugin.getConfig().getString("lang"), "eng")) {
            translations = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "languages/" + plugin.getConfig().getString("lang") + ".yml"));
            for (String translation : translations.getKeys(false)){
                translationMap.put(translation, translations.getString(translation));
            }
        }
        else {
            translations = YamlConfiguration.loadConfiguration(defaultLanguageFile);
            for (String translation : translations.getKeys(false)){
                translationMap.put(translation, translations.getString(translation));
            }
        }
    }

    public String getValue(String path) {
        String a = translationMap.get(path);
        return a;
    }

    public void msg(String path, Player player) {
        String a = translationMap.get(path);
        player.sendMessage(methods.translate(a));
    }

    public void console(String path) {
        String a = translationMap.get(path);
        Bukkit.getConsoleSender().sendMessage(a);
    }

    private void copyInputStreamToFile(InputStream in, File path) {
        try {
            Files.copy(in, path.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}