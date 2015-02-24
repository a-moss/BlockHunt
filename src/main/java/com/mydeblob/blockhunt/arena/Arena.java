package com.mydeblob.blockhunt.arena;

import org.bukkit.Location;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Mydeblob
 * @since 2/20/15
 */
public class Arena{

    private static Set<Arena> arenas = new HashSet<Arena>();

    private String arenaName;
    private Location lobby;
    private int minPlayers;
    private int maxPlayers;
    private ArenaState state = ArenaState.DISABLED;
    private Location hiderSpawn;
    private Location seekerSpawn;
    private Set<String> hiders = new HashSet<String>();
    private Set<String> seekers = new HashSet<String>();

    public Arena(Location hiderSpawn, Location seekerSpawn, int minPlayers, int maxPlayers){
        this.hiderSpawn = hiderSpawn;
        this.seekerSpawn = seekerSpawn;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        arenas.add(this);

    }

    public static Set<Arena> getArenas() {
        return arenas;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public Set<String> getSeekers() {
        return seekers;
    }

    public void setSeekers(Set<String> seekers) {
        this.seekers = seekers;
    }

    public Set<String> getHiders() {
        return hiders;
    }

    public void setHiders(Set<String> hiders) {
        this.hiders = hiders;
    }

    public Location getSeekerSpawn() {
        return seekerSpawn;
    }

    public Set<String> getAllPlayers(){
        Set<String> s = new HashSet<String>();
        s.addAll(getSeekers());
        s.addAll(getHiders());
        return s;
    }

    public void setSeekerSpawn(Location seekerSpawn) {
        this.seekerSpawn = seekerSpawn;
    }

    public Location getHiderSpawn() {
        return hiderSpawn;
    }

    public void setHiderSpawn(Location hiderSpawn) {
        this.hiderSpawn = hiderSpawn;
    }

    public ArenaState getState() {
        return state;
    }

    public void setState(ArenaState state) {
        this.state = state;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public Location getLobby() {
        return lobby;
    }

    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

}
