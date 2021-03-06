package dev.qpixel.anonymoussurvival.commands;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false;
        if (!sender.isOp()) {
            sender.sendMessage(Component.text("You do not have enough perms").color(NamedTextColor.RED));
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(Component.text("You did not supply enough args").color(NamedTextColor.RED));
            return true;
        }
        Player invokerPlayer = (Player) sender;
        if (args.length == 2) {
            for (Player target1 : Bukkit.getOnlinePlayers()) {
                if (target1.getName().equals(args[0])) {
                    for (Player target2: Bukkit.getOnlinePlayers()) {
                        if (target2.getName().equals(args[1])) {
                            target1.teleport(target2.getLocation());
                            invokerPlayer.sendMessage(
                                    Component.text(target1.getName())
                                    .append(Component.text(" has been teleported to "))
                                    .append(Component.text(target2.getName()))
                                    .color(NamedTextColor.DARK_AQUA)
                                    .decoration(TextDecoration.ITALIC, true));
                            return true;
                        }
                    }
                }
            }
            sender.sendMessage(Component.text("PlayerID did not match any online players").color(NamedTextColor.RED));
            return true;
        } else {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getName().equals(args[0])) {
                    invokerPlayer.teleport(p.getLocation());
                    invokerPlayer.sendMessage(ChatColor.LIGHT_PURPLE + "Teleported to " + p.getName());
                    invokerPlayer.sendMessage(Component.text("Real Name is ").
                            append(p.displayName()
                                    .decoration(TextDecoration.ITALIC, true)
                                    .decoration(TextDecoration.BOLD, true))
                            .decoration(TextDecoration.ITALIC, true)
                            .color(NamedTextColor.DARK_AQUA));
                    return true;
                }
            }
        }

        sender.sendMessage(Component.text("PlayerID did not match any online players").color(NamedTextColor.RED));
        return true;
    }
}
