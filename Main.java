package se.lsbmedia.msg;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;
import java.util.HashMap;

public final class Main extends JavaPlugin implements Listener {

    private HashMap<UUID, UUID> recentMessages;

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        reloadConfig();

        getCommand("message").setExecutor(new MessageCommand(this));
        getCommand("msg").setExecutor(new MessageCommand(this));
        getCommand("reply").setExecutor(new ReplyCommand(this));
        getCommand("r").setExecutor(new ReplyCommand(this));
        getCommand("simplemsg").setExecutor(new SimplemsgCommand(this));

        recentMessages = new HashMap<>();

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }

    }

