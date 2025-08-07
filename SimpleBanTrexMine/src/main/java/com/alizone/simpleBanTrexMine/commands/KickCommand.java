package com.alizone.simpleBanTrexMine.commands;

import com.alizone.simpleBanTrexMine.SimpleBanTrexMine;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) return false;

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Player not found.");
            return true;
        }

        String reason = String.join(" ", args).substring(args[0].length() + 1);
        String msg = SimpleBanTrexMine.instance.getConfig().getString("messages.kick")
                .replace("{player}", target.getName())
                .replace("{reason}", reason);

        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', msg));
        target.kickPlayer("Kicked: " + reason);
        return true;
    }
}