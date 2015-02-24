package com.mydeblob.blockhunt.util;

import com.mydeblob.blockhunt.arena.Arena;
import com.mydeblob.blockhunt.arena.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

/**
 * @author Mydeblob
 * @since 2/20/15
 */
public class Messaging {

    private static Plugin p;
    public static void init(Plugin plugin){
        p = plugin;
    }

    public static void console(Level level, String msg){
        Bukkit.getServer().getLogger().log(level, "[" + p.getDescription().getName() + "]" + msg);
    }

    public static void player(Player p, String msg){
        p.sendMessage(Lang.PREFIX.toString() + ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void player(Player p, String msg, boolean prefix){
        if(prefix)
            player(p, msg);
        else
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void debug(String msg){
        Bukkit.broadcastMessage(msg);
    }

    public static void arena(String name, String msg){
        Arena a = ArenaManager.getInstance().getArena(name);
        if(a != null){
            a.getAllPlayers().forEach(s -> {
                if(Bukkit.getPlayer(s) != null)
                    Bukkit.getPlayer(s).sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
            });
        }
    }
}
