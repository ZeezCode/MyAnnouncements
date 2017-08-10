package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.utilities.Menu;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMenu implements MyAnnouncementsCommand {

    private String command;

    public CommandMenu(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.admin"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else {
            if (sender instanceof Player) {
                Menu.openGUI((Player) sender);
            } else {
                sender.sendMessage(ChatColor.RED + "You must be a player to use this command!");
            }
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
