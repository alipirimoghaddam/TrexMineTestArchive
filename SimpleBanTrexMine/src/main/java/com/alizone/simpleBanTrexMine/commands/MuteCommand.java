package com.alizone.simpleBanTrexMine.commands;

import com.alizone.simpleBanTrexMine.SimpleBanTrexMine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length != 1) return false;

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        String uuid = target.getUniqueId().toString();
        if (!SimpleBanTrexMine.instance.mutes.contains(uuid)) {
            SimpleBanTrexMine.instance.mutes.set(uuid, true);
            SimpleBanTrexMine.instance.saveMutes();
            String msg = SimpleBanTrexMine.instance.getConfig().getString("messages.mute")
                    .replace("{player}", target.getName());
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            sender.sendMessage(ChatColor.YELLOW + "Player is already muted.");
        }

        return true;
    }
}