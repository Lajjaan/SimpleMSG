package se.lsbmedia.msg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    //       /message <name> <message>

    private Main main;

    public MessageCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(main.getConfig().getString("Message"))) {
                if (args.length >= 2) {
                    if (Bukkit.getPlayerExact(args[0]) != null) {
                        Player target = Bukkit.getPlayerExact(args[0]);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            builder.append(args[i]).append(" ");
                        }

                        // Add some color cods and format the message mechanism here
                        player.sendMessage(ChatColor.DARK_GRAY + "(" + ChatColor.AQUA + "You" + ChatColor.GRAY + " » " + ChatColor.GRAY + target.getName() + ChatColor.DARK_GRAY + ") " + ChatColor.WHITE + builder.toString());
                        target.sendMessage(ChatColor.DARK_GRAY + "(" + ChatColor.GRAY + player.getName() + ChatColor.GRAY + " » " + ChatColor.AQUA + "You" + ChatColor.DARK_GRAY + ") " + ChatColor.WHITE + builder);

                        main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("NotFound")));
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
