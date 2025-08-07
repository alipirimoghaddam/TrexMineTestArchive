package com.alizone.simpleBanTrexMine.listeners;

import com.alizone.simpleBanTrexMine.SimpleBanTrexMine;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (SimpleBanTrexMine.instance.mutes.contains(e.getPlayer().getUniqueId().toString())) {
            e.setCancelled(true);
            e.getPlayer().sendMessage("§cYou are muted.");
        }
    }
}