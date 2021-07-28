package io.github.jaronz.jzlib.command;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.utils.TextFormat;
import com.sun.istack.internal.Nullable;

import java.util.HashMap;
import java.util.Map;

public abstract class Command extends cn.nukkit.command.Command {
    private boolean hasMinArgs = false;
    private boolean hasMaxArgs = false;
    private boolean consoleOnly = false;
    private boolean playerOnly = false;
    private int minArgs;
    private int maxArgs;

    public Command(String name){
        super(name);
    }

    public Command(String name, String description){
        super(name, description);
    }

    public Command(String name, String description, String usageMessage){
        super(name, description, usageMessage);
    }

    public Command(String name, String description, String usageMessage, String[] aliases){
        super(name, description, usageMessage, aliases);
    }

    private void sendError(CommandSender target, String message){
        target.sendMessage(TextFormat.RED + message);
    }

    private void sendArgsError(CommandSender target, String message){
        this.sendError(target, message + TextFormat.RESET + "\nUsage: " + this.getUsage());
    }

    public boolean testCommand(CommandSender sender, String[] args){
        if(!this.testPermission(sender)) return false;
        if(this.hasMinArgs && args.length < this.minArgs){
            this.sendArgsError(sender, "Not enough arguments!");
            return false;
        }
        if(this.hasMaxArgs && args.length > this.maxArgs){
            this.sendArgsError(sender, "Too many arguments!");
            return false;
        }
        boolean isPlayer = sender instanceof Player;
        if(!isPlayer && playerOnly){
            this.sendError(sender, "This command can only be used by players!");
            return false;
        }
        if(isPlayer && consoleOnly){
            this.sendError(sender, "This command can only be used in the console!");
            return false;
        }
        return true;
    }

    public @Nullable int getMinArgs(){
        return this.hasMinArgs ? this.minArgs : null;
    }

    public void setMinArgs(int minArgs){
        this.minArgs = minArgs;
        this.hasMinArgs = true;
    }

    public @Nullable int getMaxArgs(){
        return this.hasMaxArgs ? this.maxArgs : null;
    }

    public void setMaxArgs(int maxArgs){
        this.maxArgs = minArgs;
        this.hasMaxArgs = true;
    }

    public void setArgsRange(int minArgs, int maxArgs){
        this.setMinArgs(minArgs);
        this.setMaxArgs(maxArgs);
    }

    public boolean isConsoleOnly(){
        return this.consoleOnly;
    }

    public void setConsoleOnly(boolean consoleOnly){
        this.consoleOnly = consoleOnly;
        if(consoleOnly) this.playerOnly = false;
    }

    public boolean isPlayerOnly() {
        return this.playerOnly;
    }

    public void setPlayerOnly(boolean playerOnly){
        this.playerOnly = playerOnly;
        if(playerOnly) this.consoleOnly = false;
    }

    public void setCommandParameters(CommandParameter[] commandParameters) {
        super.setCommandParameters(new HashMap(){{ put("default", commandParameters); }});
    }
}
