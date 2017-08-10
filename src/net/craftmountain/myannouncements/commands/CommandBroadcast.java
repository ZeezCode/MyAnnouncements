package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.utilities.Announcer;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandBroadcast implements MyAnnouncementsCommand {

    private String command;

    public CommandBroadcast(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.admin"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else { //Sender has permission
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "No message specified!");
            } else { //Message specified
                StringBuilder message = new StringBuilder();
                for (int i=1; i<args.length; i++) {
                    String str = args[i];
                    message.append(str).append(" ");
                }
                Announcer.announceMessage(message.toString());
            }
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
