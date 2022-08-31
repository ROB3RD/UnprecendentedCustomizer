package com.scoddle.unprecendentedcustomizer.utils.files;

import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class XFile implements IReference {

    String fileName;
    File file;
    FileConfiguration fileConfiguration;

    public XFile(String str) {
        this.fileName = str;
    }

    // Finds/Generates file
    public void setup() {

        File eventDirectory = new File(plugin.getDataFolder(), "events/");
        file = new File(plugin.getDataFolder(), "events/" + fileName + ".yml");

        if (!eventDirectory.isDirectory()) {
            eventDirectory.mkdir();
        }

        try {
            if(!file.exists()) {
                InputStream in = plugin.getResource("events/" + fileName + ".yml");
                Files.copy(in, file.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    // Get Customfile
    public FileConfiguration get() {
        return fileConfiguration;
    }

    public String getValue(String valuename) {
        return get().getString(valuename);
    }

    public void setValue(String valuename, String toset) {
        get().set(valuename, toset);
        save();
    }

    // Save file
    public void save() {
        try {
            fileConfiguration.save(file);
        }
        catch (IOException e) {
            System.out.println("Couldn't save file!");
        }
    }

    public void reload() {
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public String getFileName() {
        return fileName;
    }

    public File getFile() {
        return file;
    }
}
