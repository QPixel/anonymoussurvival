package dev.qpixel.anonymoussurvival;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.UUID;


public class Util {
    static final String texture = "ewogICJ0aW1lc3RhbXAiIDogMTYyMzk0MjU2MDA1MCwKICAicHJvZmlsZUlkIiA6ICI1ZjE4ZmY4NTEyZGQ0NmQ1OGY1MjJlYjg2MDg2ZjU5MiIsCiAgInByb2ZpbGVOYW1lIiA6ICJUYWtlb3MiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTM4OTgxZmEzNzhiYWIxNTA2OWZiOWJjMzQ0NWEwNjljNjRjNThiZWQxZjU5NTViZmQwMTUwOGJmNGVhYzRlZSIKICAgIH0KICB9Cn0=";
    static final String sig = "SybAg8WYS8O7SAQPZv1aU89YGQNjBbh1j4dNnVLOFdkfRIpwL90OEA6ARWy3ujoGYFnIp36YH7vTgaGxFSzhRC+Rzj9iJpBFhs1dAU5t8wxQmHHBxZyTNCBV/QIHHLKF9hxi57RGEXbICRXvlZE3fQh6Bp+lALP88iH84IelbX9Nk5+mCG1/DDtI8EDsdONmfENEzfE2pJA/P3onLs0jRc+GOw8GZEobob7c+ptldTyCNBWS0tROVV9gMXcclOR4+OQ6tCdEasytiw5tguFxOaLWBzQTS55JGVqAQlFSNSo3WEeeK0kD93iKn3HwcQyH1RLrHF1XEWHxgUkZ6gZLwQDOaE4f5hqUKEIbdDL2Jd2BcQ2u2ExpPE5s+J9R5ETK6kSANFAEn1ayLIE+6euNObOCVEM64vLPG8YEkvEq//vsnevICxwWZ3hyPFqAenTFi/bIiptTCWWWGgaAj33btAiOesyra12aFL4iSAS9Y3AzfcnDop4WONOg5yuU6VEep8Afn12/1o5m2vwu9HOjSsGO3QRP+SGlYfM6pVEp47ZdbGOz06QrD7AdG8Cg2TvtobYUz+gfA0uA5mjCwgTeffm2AOGDQIWQ+YfLkdGoPzJJlovny4TrU13IDkCvlwdIb+nfPJim9RIA6vRifr9spJEeektj/HWBSahpwzuN1mY=";
    public static void obfuscatePlayer(Player player, PlayerProfile profile, Team team) {
        // Add Player to hidePlayerNameTags team
        if (team != null) {
            team.addEntry(player.getName());
        }
        String username = UUID.randomUUID().toString().substring(0, 7);
        // Set players skin to a steve and set name to something random
        profile.setProperty(new ProfileProperty("textures", texture, sig));
        profile.setName(username);
        player.setPlayerProfile(profile);
        player.playerListName(Component.text(username).color(NamedTextColor.GRAY));
    }
}
