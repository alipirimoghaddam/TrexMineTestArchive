package com.alizone.simpleBanTrexMine.listeners;

import com.alizone.simpleBanTrexMine.commands.FreezeCommand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementListener implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (FreezeCommand.frozen.contains(e.getPlayer().getUniqueId())) {
            e.setTo(e.getFrom()); // Cancel movement
        }
    }
}