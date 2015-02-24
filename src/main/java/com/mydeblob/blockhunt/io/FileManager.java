package com.mydeblob.blockhunt.io;

import com.mydeblob.blockhunt.util.Lang;
import com.mydeblob.blockhunt.util.Messaging;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

/**
 * @author Mydeblob
 * @since 2/17/15
 */
public class FileManager{

    private static FileManager instance = new FileManager();


    public static FileManager getInstance(){
        return instance;
    }

    /**
     * Gets the file based on the name
     *
     * @param name - The name of the file
     * @return YmlFile - The file object
     */
    public YmlFile getFile(String name){
        for(YmlFile yml:YmlFile.getFiles()){
            if(yml.getName().equals(name)){
                return yml;
            }
        }
        return null;
    }

    /**
     * Loads the plugins
     *
     * @param plugin - The plugin that extends JavaPlugin
     */
    public void loadFiles(Plugin plugin){
        YmlFile.init(plugin);
        new YmlFile("arenas.yml");
        YmlFile lang = new YmlFile("messages.yml");
        new YmlFile("stats.yml");
        for(YmlFile yml:YmlFile.getFiles()){
            if(!yml.getFile().exists()){
                Messaging.console(Level.INFO, "The file, " + yml.getName() + ", doesn't exist. Generating a new one");
                if(plugin.getResource(yml.getName()) != null){
                    yml.saveDefaultConfig();
                }
            }
            yml.reloadConfig();
            yml.saveConfig();
        }
        Lang.setFile(YamlConfiguration.loadConfiguration(lang.getFile()));
    }


}
