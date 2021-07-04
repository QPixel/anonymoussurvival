package dev.qpixel.anonymoussurvival;

import com.destroystokyo.paper.profile.PlayerProfile;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.logging.Logger;

public class Events implements Listener {
    private Logger logger;
    public Events(Logger lg) {
        this.logger = lg;
    }
    // Handle join events
    // Hides the name tag, sets skin to steve
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent evnt) {
        evnt.joinMessage(null);
        Player player = evnt.getPlayer();
        // OP Players bypass
        if (player.isOp()) return;
        PlayerProfile profile = player.getPlayerProfile();
        Util.obfuscatePlayer(player, profile, Bukkit.getScoreboardManager().getMainScoreboard().getTeam("hidePlayers"));
        player.playerListName(player.playerListName().color(NamedTextColor.GRAY));
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent evnt) {
        evnt.quitMessage(null);
    }
    // Disable Chat
    // Idk if i need the async check
    @EventHandler
    public void AsyncChatEvent(AsyncChatEvent evnt) {
        if (evnt.getPlayer().isOp()) return;
        if (evnt.isAsynchronous()) {
            evnt.getPlayer().sendMessage(ChatColor.RED + "You are unable to send messages silly!");
            evnt.setCancelled(true);
        }
        if (evnt.isCancelled()) {
            return;
        }
        evnt.getPlayer().sendMessage(ChatColor.RED + "You are unable to send messages silly!");
        evnt.setCancelled(true);
        return;
    }
    // Disable commands for non ops
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommandPreprocess(PlayerCommandPreprocessEvent e) {
        if (e.getPlayer().isOp()) {
            return;
        }
        e.setCancelled(true);
        e.getPlayer().sendMessage(Component.text("You can't use commands silly!").color(NamedTextColor.RED).decoration(TextDecoration.BOLD, true));
    }

    // Don't send command list to players anyways
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandSend(PlayerCommandSendEvent e) {
        if (e.getPlayer().isOp()) {
            return;
        }
        e.getCommands().clear();
        return;
    }
}
