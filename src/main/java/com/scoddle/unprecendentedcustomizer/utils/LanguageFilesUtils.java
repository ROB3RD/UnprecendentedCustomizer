package com.scoddle.unprecendentedcustomizer.utils;

import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class LanguageFilesUtils implements IReference {

    private static Map<File, Map<String, String>> languages = new HashMap<>();

    public static String currentLang = "";

    public static String getValue(String language, String valueName) {
        File langFile = new File(plugin.getDataFolder() + "/lang", language + ".yml");
        return languages.get(langFile).get(valueName);
    }

    public static void loadValues() {
        File langFolder = new File(plugin.getDataFolder() + "/lang");
        if (!langFolder.exists()) {
            langFolder.mkdir();
        }

        File enFile = new File(langFolder, "eng.yml");
        File geFile = new File(langFolder, "ger.yml");

        // ENGLISH
        try {
            if (!enFile.exists()) {
                InputStream in = plugin.getResource("eng.yml");
                Files.copy(in, enFile.toPath());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        // GERMAN
        try {
            if (!geFile.exists()) {
                InputStream in = plugin.getResource("ger.yml");
                Files.copy(in, geFile.toPath());
            }
        }catch (IOException e) {
            e.printStackTrace();
        }

        for (File file : langFolder.listFiles()) {
            Map<String, String> values = new HashMap<>();
            FileConfiguration lang = YamlConfiguration.loadConfiguration(file);
            for (String key : lang.getKeys(false)) {
                for (String valueName : lang.getConfigurationSection(key).getKeys(false)) {
                    String value = ChatColor.stripColor(lang.getString(key + "." + valueName));
                    values.put(valueName, value);
                }
            }
            languages.put(file, values);
            String prefix = plugin.getConfig().getString("prefix");
            Bukkit.getConsoleSender().sendMessage(methods.translate(prefix + " &a" + file.getName() + " loaded!"));
        }
    }
}