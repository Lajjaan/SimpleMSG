package se.lsbmedia.msg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReplyCommand implements CommandExecutor {

    //       /reply <message>

    private Main main;

    public ReplyCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
                if (player.hasPermission(main.getConfig().getString("Reply"))) {
                    if (args.length >= 1) {
                        if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                            UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                            if (Bukkit.getPlayer(uuid) != null) {
                                Player target = Bukkit.getPlayer(uuid);

                                StringBuilder builder = new StringBuilder();
                                for (int i = 0; i < args.length; i++) {
                                    builder.append(args[i]).append(" ");
                                }

                                // Add some color cods and format the message mechanism here

                                // &8(&bYou &8» &7target.getName()&8) &f builder.toString
                                player.sendMessage(ChatColor.DARK_GRAY + "(" + ChatColor.AQUA + "You" + ChatColor.GRAY + " » " + ChatColor.GRAY + target.getName() + ChatColor.DARK_GRAY + ") " + ChatColor.WHITE + builder.toString());
                                // &8(&7target.getName() &8» &bYou&8) &f builder
                                target.sendMessage(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + player.getName() + ChatColor.GRAY + " » " + ChatColor.AQUA + "You" + ChatColor.DARK_GRAY + ") " + ChatColor.WHITE + builder);

                            } else {
                                player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("Offline")));
                            }
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("NoReply")));
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("WrongUsage")));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("NoPermission")));
                }
            main.saveConfig();
        }
        return false;
    }
}
