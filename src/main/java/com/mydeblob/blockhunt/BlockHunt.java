package com.mydeblob.blockhunt;

import com.mydeblob.blockhunt.commandutil.Initializer;
import com.mydeblob.blockhunt.io.FileManager;
import com.mydeblob.blockhunt.io.YmlFile;
import com.mydeblob.blockhunt.util.Messaging;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Level;

/**
 * @author Mydeblob
 * @since 2/18/15
 */
public class BlockHunt extends JavaPlugin{

    @Override
    public void onEnable() {
        FileManager.getInstance().loadFiles(this);
        Messaging.init(this);
        File config = new File(getDataFolder(), "config.yml");
        if(!config.exists()){
            Messaging.console(Level.INFO, "No config.yml found! Generating a new one.");
            saveDefaultConfig();
        }
        new Initializer().loadCommands(new Commands().getClass(), this);
        Messaging.console(Level.INFO, "Successfully enabled!");
    }

    @Override
    public void onDisable() {
        YmlFile.getFiles().forEach((yml) -> yml.saveConfig());
    }
}
