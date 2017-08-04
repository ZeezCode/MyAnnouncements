package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.MyAnnouncements;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandMyAnnouncements implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!Utilities.hasPermission(sender, "ma.base")) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            return true;
        }

        if (args.length == 0) {
            String message = ChatColor.GREEN + "-=MyAnnouncements Base Command=-\n"
                    + "/ma version " + ChatColor.WHITE + "View plugin version\n"
                    + ChatColor.GREEN + "/ma reload " + ChatColor.WHITE + "Reload plugin config\n"
                    + ChatColor.GREEN + "/ma toggle " + ChatColor.WHITE + "Pause/resume announcements\n"
                    + ChatColor.GREEN + "/ma broadcast " + ChatColor.WHITE + "Send a custom broadcast\n"
                    + ChatColor.GREEN + "/ma force " + ChatColor.WHITE + "Force the next broadcast";
            sender.sendMessage(message);
            return true;
        }

        MyAnnouncementsCommand executor = MyAnnouncements.getInstance().getExecutor(args[0]);
        if (executor == null)
            sender.sendMessage(ChatColor.RED + "Unrecognized command! Type /ma for help.");
        else
            executor.onCommand(sender, args);

        return true;
    }

}
