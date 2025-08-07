package com.alizone.simpleBanTrexMine;

import com.alizone.simpleBanTrexMine.commands.*;
import com.alizone.simpleBanTrexMine.listeners.ChatListener;
import com.alizone.simpleBanTrexMine.listeners.MovementListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class SimpleBanTrexMine extends JavaPlugin {
    public static SimpleBanTrexMine instance;
    public FileConfiguration bans, mutes;
    public File bansFile, mutesFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "[TrexMine Test]" + ChatColor.GRAY + " Roshan shod. sakhte shode tavasot Alizone!");
        instance = this;
        saveDefaultConfig();
        createCustomConfigs();

        getCommand("ban").setExecutor(new BanCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("kick").setExecutor(new KickCommand());
        getCommand("mute").setExecutor(new MuteCommand());
        getCommand("unmute").setExecutor(new UnmuteCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());

        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new MovementListener(), this);
    }

    private void createCustomConfigs() {
        bansFile = new File(getDataFolder(), "bans.yml");
        mutesFile = new File(getDataFolder(), "mutes.yml");

        if (!bansFile.exists()) saveResource("bans.yml", false);
        if (!mutesFile.exists()) saveResource("mutes.yml", false);

        bans = YamlConfiguration.loadConfiguration(bansFile);
        mutes = YamlConfiguration.loadConfiguration(mutesFile);
    }

    public void saveBans() {
        try {
            bans.save(bansFile);
        } catch (Exception ignored) {
        }
    }

    public void saveMutes() {
        try {
            mutes.save(mutesFile);
        } catch (Exception ignored) {
        }
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[TrexMine Test]" + ChatColor.GRAY + " Khamosh shod. sakhte shode tavasot Alizone!");
    }
}
