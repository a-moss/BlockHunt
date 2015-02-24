package com.mydeblob.blockhunt.io;

import com.mydeblob.blockhunt.util.Messaging;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/**
 * @author Mydeblob
 * @since 2/18/15
 */
public class YmlFile {

    private static Set<YmlFile> files = new HashSet<YmlFile>();
    private String name;
    private File file;
    private FileConfiguration fc;
    private static Plugin p;

    public static void init(Plugin plugin){
        p = plugin;
    }

    public YmlFile(String name){
        file = new File(p.getDataFolder(), name);
        files.add(this);
    }

    /**
     * Reloads the config, and if it doesn't exist generates a new one
     *
     * This will bug out if the file isn't saved as UTF8
     */
    public void reloadConfig(){
        if (file == null) {
            file = new File(p.getDataFolder(), name + ".yml");
        }
        fc = YamlConfiguration.loadConfiguration(file);

        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(p.getResource(name), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Messaging.console(Level.SEVERE, "The character encoding for the file " + name + " is not supported!");
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            fc.setDefaults(defConfig);
        }
    }

    /**
     * Gets the config
     *
     * @return FileConfiguration - The configuration of the file
     */
    public FileConfiguration getConfig() {
        if (file == null) {
            this.reloadConfig();
        }
        return fc;
    }

    /**
     * Saves the config
     */
    public void saveConfig() {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(p, () -> {
            if (file == null || fc == null) {
                return;
            }
            try {
                getConfig().save(file);
            } catch (IOException e) {
                Messaging.console(Level.SEVERE, "Could not save the following file: " + name);
                e.printStackTrace();
            }
        });
    }

    /**
     * Saves the default configuration from the plugin resources
     */
    public void saveDefaultConfig() {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(p, () -> {
            if (file == null) {
                file = new File(p.getDataFolder(), this.name + ".yml");
            }
            if (!file.exists()) {
                p.saveResource(this.name + ".yml", false);
            }
        });
    }

    /**
     * Gets the files name WITHOUT the .yml
     *
     * @return String - The file name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Gets the file
     *
     * @return File - The file (Should always be initialized)
     */
    public File getFile(){
        return this.file;
    }

    /**
     * Gets the file objects
     *
     * @return -- A set of file objects
     */
    public static Set<YmlFile> getFiles(){
        return files;
    }

}
