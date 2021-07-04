package dev.qpixel.anonymoussurvival.commands;

import com.destroystokyo.paper.profile.PlayerProfile;
import dev.qpixel.anonymoussurvival.AnonymousSurvival;
import dev.qpixel.anonymoussurvival.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandObfuscateSelf implements CommandExecutor {
    private AnonymousSurvival plugin;
    public CommandObfuscateSelf(AnonymousSurvival anonymousSurvival) {
        this.plugin = anonymousSurvival;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        if (!sender.isOp()) {
            sender.sendMessage(Component.text("You do not have enough perms").color(NamedTextColor.RED));
            return true;
        }
        Player player = (Player) sender;
        String playerName = player.getUniqueId().toString();
        if (plugin.obfuscatedPlayers.containsKey(playerName)) {
            PlayerProfile profile = plugin.obfuscatedPlayers.get(playerName);
            player.setPlayerProfile(profile);
            player.playerListName(Component.text(profile.getName()));
            player.sendMessage(Component.text("You have been unobfuscated!").color(NamedTextColor.RED));
            plugin.obfuscatedPlayers.remove(playerName);
            return true;
        }
        plugin.obfuscatedPlayers.put(playerName, player.getPlayerProfile());
        Util.obfuscatePlayer(player, player.getPlayerProfile(), Bukkit.getScoreboardManager().getMainScoreboard().getTeam("hidePlayers"));
        player.sendMessage(Component.text("You have been obfuscated!").color(NamedTextColor.RED));
        return true;
    }
}
