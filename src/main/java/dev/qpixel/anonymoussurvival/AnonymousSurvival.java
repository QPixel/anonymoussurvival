package dev.qpixel.anonymoussurvival;


import com.destroystokyo.paper.profile.PlayerProfile;
import dev.qpixel.anonymoussurvival.commands.CommandObfuscatePlayers;
import dev.qpixel.anonymoussurvival.commands.CommandObfuscateSelf;
import dev.qpixel.anonymoussurvival.commands.CommandRealName;
import dev.qpixel.anonymoussurvival.commands.CommandTeleport;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.logging.Logger;


public final class AnonymousSurvival extends JavaPlugin {
    public Logger logger = getLogger();
    public HashMap<String, PlayerProfile> obfuscatedPlayers = new HashMap();
    @Override
    public void onEnable() {
        // Plugin startup logic
        setup();
        addCommands();
        addEventListeners();
        logger.info("AnonymousSurvival 1.0 has loaded");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        logger.info("AnonymousSurvival 1.0 has unloaded");
    }


    private void addEventListeners() {
        getServer().getPluginManager().registerEvents(new Events(this.logger), this);
    }

    private void addCommands() {
        this.getCommand("teleport").setExecutor(new CommandTeleport());
        this.getCommand("realname").setExecutor(new CommandRealName());
        this.getCommand("obfuscate").setExecutor(new CommandObfuscatePlayers());
        this.getCommand("obfself").setExecutor(new CommandObfuscateSelf(this));
    }

    private void setup() {
        // Makes sure the advancements announcements are false. Because why not
        Bukkit.getServer().getWorlds().get(0).setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        if (Bukkit.getScoreboardManager().getMainScoreboard().getTeam("hidePlayers") != null) {
            return;
        }
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.registerNewTeam("hidePlayers");
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
    }
}
