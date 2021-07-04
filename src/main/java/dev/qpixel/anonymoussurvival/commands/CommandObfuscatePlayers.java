package dev.qpixel.anonymoussurvival.commands;

import dev.qpixel.anonymoussurvival.Util;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandObfuscatePlayers implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage(Component.text("You do not have enough perms").color(NamedTextColor.RED));
            return true;
        }
        Player player = (Player) sender;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.isOp()){
                Util.obfuscatePlayer(p, p.getPlayerProfile(), Bukkit.getScoreboardManager().getMainScoreboard().getTeam("hidePlayers"));
            }
        }
        player.sendMessage(Component.text("Players reobfuscated!").color(NamedTextColor.LIGHT_PURPLE));
        return true;
    }
}
