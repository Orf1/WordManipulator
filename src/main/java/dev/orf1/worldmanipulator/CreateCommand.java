package dev.orf1.worldmanipulator;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Player;

public class CreateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can't be used from console.");
            return false;
        }

        if (args.length != 8) {
            sender.sendMessage("Please use /create [id] [x1] [y1] [z2] [x2] [y2] [z2] [a/b]");
            return false;
        }

        Player player = (Player) sender;
        World world = player.getWorld();

        int id = Integer.parseInt(args[0]);
        byte data = (byte) 0;

        int startX = Integer.parseInt(args[1]);
        int startY = Integer.parseInt(args[2]);
        int startZ = Integer.parseInt(args[3]);

        int endX = Integer.parseInt(args[4]);
        int endY = Integer.parseInt(args[5]);
        int endZ = Integer.parseInt(args[6]);

        int totalX = endX - startX;
        int totalY = endY - startY;
        int totalZ = endZ - startZ;

        long taskStart;
        long taskEnd;

        String method = args[7];

        player.sendMessage("Starting operation.");
        System.out.println("Starting operation.");

        Manipulator.setIbd(net.minecraft.server.v1_16_R2.Block.getByCombinedId(id + (data << 12)));
        Manipulator.setNmsWorld(((CraftWorld) world).getHandle());


        if (method.equalsIgnoreCase("a")) {
            taskStart = System.nanoTime();
            for (int x = startX; x < endX; x++) {
                for (int z = startZ; z < endZ; z++) {
                    for (int y = startY; y < endY; y++) {
                        Manipulator.setBlockInNativeChunkSection(x, y, z);
                    }
                }
            }
            taskEnd = System.nanoTime();
        } else if (method.equalsIgnoreCase("b")) {
            taskStart = System.nanoTime();
            for (int x = startX; x < endX; x++) {
                for (int z = startZ; z < endZ; z++) {
                    for (int y = startY; y < endY; y++) {
                        Manipulator.setBlockInNativeDataPalette(x, y, z);
                    }
                }
            }
            taskEnd = System.nanoTime();
        } else {
            sender.sendMessage("Please use /create [id] [x1] [y1] [z2] [x2] [y2] [z2] [a/b]");
            return false;
        }

        long ns = (taskEnd - taskStart);
        long ms = (taskEnd - taskStart) / 1000000;
        int total = (totalX * totalY * totalZ);

        player.sendMessage("Operation completed in: " + ms + " ms(" + ns + " ns)");
        player.sendMessage("Created " + total + " blocks!");
        System.out.println("Operation completed in: " + ms + " ms(" + ns + " ns)");
        System.out.println("Created " + total + " blocks!");

        return true;
    }
}
