package com.scoddle.unprecendentedcustomizer.utils.files;

import com.scoddle.unprecendentedcustomizer.utils.reference.IReference;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class EventFile implements IReference {

    private File file;
    private FileConfiguration customFile;

    // Finds/Generates file
    public void setup() {

        file = new File(plugin.getDataFolder(), "eventconfig.yml");

        try {
            if(!file.exists()) {
                InputStream in = plugin.getResource("eventconfig.yml");
                Files.copy(in, file.toPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        customFile = YamlConfiguration.loadConfiguration(file);

    }

    // Get Customfile
    public FileConfiguration get() {
        return customFile;
    }

    public String getValue(String valuename) {
        return get().getString(valuename);
    }

    public void  setValue(String valuename, String toset) {
        get().set(valuename, toset);
    }

    // Save file
    public void save() {
        try {
            customFile.save(file);
        }
        catch (IOException e) {
            System.out.println("Couldn't save file!");
        }
    }

    public void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public void getValue(String language, String valuename) {
        File langFile = new File(plugin.getDataFolder() + "/lang", language + ".yml");

    }
}
