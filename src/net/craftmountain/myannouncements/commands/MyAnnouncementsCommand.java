package net.craftmountain.myannouncements.commands;

import org.bukkit.command.CommandSender;

public interface MyAnnouncementsCommand {
    void onCommand(CommandSender sender, String[] args);
    String getCommand();
}
