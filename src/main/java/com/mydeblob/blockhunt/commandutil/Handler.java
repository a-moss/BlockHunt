package com.mydeblob.blockhunt.commandutil;

import com.mydeblob.blockhunt.util.Lang;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;

/**
 * @author Mydeblob
 * @since 2/23/15
 */
public class Handler implements CommandExecutor, TabExecutor{

    //TODO
    private Set<Data> commands = Initializer.getCommands();

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        for(Data data:commands){
            com.mydeblob.blockhunt.commandutil.Command command = data.getCmd();
            if(cmd.getName().equalsIgnoreCase(command.name())){
                Info info = new Info(sender, args);
                if(args.length > 0){
                    if(info.hasArgs()){
                        if(args.length != info.argLength()) return true;
                        String[] cmdArgs = command.addRequiredArgs().split(" ");
                        int index = 0;
                        for(String s:cmdArgs){
                            if(!args[index].equalsIgnoreCase(s)) return true;
                            index++;
                        }
                        if(!info.isPlayer() && !command.allowConsole()){
                            info.sendMessage(Lang.NOT_A_PLAYER.toString());
                            return true;
                        }
                        if(!hasPermission(sender, command)){
                            info.sendMessage(Lang.NO_PERMS.toString());
                            return true;
                        }
                        try{
                            data.getMethod().invoke(data.getClazz().newInstance(), info);
                            return true;
                        }catch(Exception e){
                            Bukkit.getServer().getLogger().log(Level.SEVERE, "A critical error occurred when attempting to perform a command, report to the author:");
                            e.printStackTrace();
                            return true;
                        }
                    }
                } else{
                    if(!info.isPlayer() && !command.allowConsole()){
                        info.sendMessage(Lang.NOT_A_PLAYER.toString());
                        return true;
                    }
                    if(!hasPermission(sender, command)){
                        info.sendMessage(Lang.NO_PERMS.toString());
                        return true;
                    }
                    try{
                        data.getMethod().invoke(data.getClazz().newInstance(), info);
                        return true;
                    }catch(Exception e){
                        Bukkit.getServer().getLogger().log(Level.SEVERE, "A critical error occurred when attempting to perform a command, report to the author:");
                        e.printStackTrace();
                        return true;
                    }
                }
                if(!info.isPlayer() && !command.allowConsole()){
                    info.sendMessage(Lang.NOT_A_PLAYER.toString());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPermission(CommandSender sender, org.bukkit.command.Command cmd){
        if(sender.hasPermission(cmd.permission())) return true;
        if(cmd.addPermissions().length != 0){
            for(String s:cmd.addPermissions()){
                if(sender.hasPermission(s)) return true;
            }
        }
        return false;
    }
    @Override
    public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args){
        return null;
    }

}
