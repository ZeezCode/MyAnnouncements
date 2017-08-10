package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.utilities.Announcer;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandToggle implements MyAnnouncementsCommand {

    private String command;

    public CommandToggle(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.admin"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else {
            boolean paused;
            Announcer.getInstance().setPaused(paused = !Announcer.getInstance().isPaused());
            sender.sendMessage(ChatColor.GREEN + "You have successfully " + (paused ? "paused" : "unpaused") + " the announcements!");
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
