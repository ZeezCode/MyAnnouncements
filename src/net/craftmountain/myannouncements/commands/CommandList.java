package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.MyAnnouncements;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandList implements MyAnnouncementsCommand {

    private String command;

    public CommandList(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.list"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else {
            List<String> announcements = MyAnnouncements.getInstance().getConfig().getStringList("announcements");
            sender.sendMessage(ChatColor.GREEN + "All Announcements:");
            for (int i = 0; i<announcements.size(); i++) {
                sender.sendMessage(ChatColor.GREEN + "" + (i+1) + ": " + ChatColor.YELLOW + ChatColor.translateAlternateColorCodes('&', announcements.get(i)));
            }
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
