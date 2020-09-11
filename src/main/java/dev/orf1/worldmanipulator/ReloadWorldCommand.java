package dev.orf1.worldmanipulator;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadWorldCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("kill")) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    player.kickPlayer("World Reloading!");
                });
                Bukkit.unloadWorld("world", false);
                Bukkit.createWorld(new WorldCreator("world"));
            }
        }
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.kickPlayer("World Reloading!");
        });
        Bukkit.unloadWorld("world", true);
        Bukkit.createWorld(new WorldCreator("world"));
        return false;
    }
}