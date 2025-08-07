package com.alizone.simpleBanTrexMine.commands;

import com.alizone.simpleBanTrexMine.SimpleBanTrexMine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class UnbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) return false;

        SimpleBanTrexMine plugin = SimpleBanTrexMine.instance;
        String targetName = args[0];
        String uuid = Bukkit.getOfflinePlayer(targetName).getUniqueId().toString();

        if (plugin.bans.contains(uuid)) {
            plugin.bans.set(uuid, null);
            plugin.saveBans();
            String msg = plugin.getConfig().getString("messages.unban")
                    .replace("{player}", targetName);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            sender.sendMessage(ChatColor.RED + "Player is not banned.");
        }

        return true;
    }
}