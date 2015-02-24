package com.mydeblob.blockhunt.arena;

import org.bukkit.entity.Player;

import java.util.Optional;

/**
 * @author Mydeblob
 * @since 2/20/15
 */
public class ArenaManager {

    private static ArenaManager instance = new ArenaManager();

    public static ArenaManager getInstance(){
        return instance;
    }

    public boolean arenaExists(String name){
        return Arena.getArenas().stream().filter(aName -> aName.getArenaName().equalsIgnoreCase(name)).findFirst().isPresent();
    }

    public Arena getArena(String name){
        Optional<Arena> op = Arena.getArenas().stream().filter(aName -> aName.getArenaName().equalsIgnoreCase(name)).findFirst();
        return op.isPresent() ? op.get() : null;
    }

    public boolean inArena(Player p){
        Optional<Arena> a = Arena.getArenas().stream().filter(aName -> aName.getAllPlayers().contains(p.getName())).findFirst();
        return a.isPresent();
    }

    public Arena getArena(Player p){
        Optional<Arena> a = Arena.getArenas().stream().filter(aName -> aName.getAllPlayers().contains(p.getName())).findFirst();
        return a.isPresent() ? a.get() : null;
    }


}
