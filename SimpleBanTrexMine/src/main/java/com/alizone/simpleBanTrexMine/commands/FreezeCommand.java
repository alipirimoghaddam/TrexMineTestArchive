package com.alizone.simpleBanTrexMine.commands;

import com.alizone.simpleBanTrexMine.SimpleBanTrexMine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class FreezeCommand implements CommandExecutor {
    public static Set<UUID> frozen = new HashSet<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1) return false;

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        UUID uuid = target.getUniqueId();
        if (frozen.contains(uuid)) {
            frozen.remove(uuid);
            sender.sendMessage(ChatColor.GREEN + "Unfroze " + target.getName());
            return true;
        }

        frozen.add(uuid);
        sender.sendMessage(ChatColor.AQUA + "Froze " + target.getName());

        new BukkitRunnable() {
            @Override
            public void run() {
                if (!frozen.contains(uuid) || !target.isOnline()) {
                    cancel();
                    return;
                }
                target.getWorld().spawnParticle(Particle.SNOWFLAKE, target.getLocation(), 20, 0.5, 1, 0.5, 0.01);
            }
        }.runTaskTimer(SimpleBanTrexMine.instance, 0L, 20L);
        return true;
    }
}
