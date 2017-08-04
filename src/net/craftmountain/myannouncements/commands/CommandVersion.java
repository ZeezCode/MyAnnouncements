package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.MyAnnouncements;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class CommandVersion implements MyAnnouncementsCommand {

    private String command;

    public CommandVersion(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.version"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else {
            PluginDescriptionFile pdfFile = MyAnnouncements.getInstance().getDescription();
            sender.sendMessage(ChatColor.GREEN + pdfFile.getName() + " is currently running version v." + pdfFile.getVersion() + ".");
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
