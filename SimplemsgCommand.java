package se.lsbmedia.msg;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SimplemsgCommand implements CommandExecutor {

    private Main main;

    public SimplemsgCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission(main.getConfig().getString("SimpleMSG"))) {
                player.sendMessage("SimpleMSG" + "\n" + "/message <name> <message> - Send a message to a player." + "\n" + "/reply <message> - Reply to a player.");
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', main.getConfig().getString("NoPermission")));
            }
            main.saveConfig();
        }
        return false;
    }
}
