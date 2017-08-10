package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.MyAnnouncements;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandReload implements MyAnnouncementsCommand {

    private String command;

    public CommandReload(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.admin"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else {
            MyAnnouncements.getInstance().reloadAnnouncer();
            sender.sendMessage(ChatColor.GREEN + "You have successfully reloaded MyAnnouncer!");
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
