package com.alizone.simpleBanTrexMine.commands;

import com.alizone.simpleBanTrexMine.SimpleBanTrexMine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;

        Player target = Bukkit.getPlayer(args[0]);
        String reason = String.join(" ", args).substring(args[0].length() + 1);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        SimpleBanTrexMine plugin = SimpleBanTrexMine.instance;
        plugin.bans.set(target.getUniqueId().toString(), reason);
        plugin.saveBans();

        String msg = plugin.getConfig().getString("messages.ban")
                .replace("{player}", target.getName())
                .replace("{reason}", reason);

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
        target.kickPlayer("Banned: " + reason);
        return true;
    }
}