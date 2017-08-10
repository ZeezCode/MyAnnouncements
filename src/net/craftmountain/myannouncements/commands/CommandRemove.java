package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.MyAnnouncements;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class CommandRemove implements MyAnnouncementsCommand {

    private String command;

    public CommandRemove(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.admin"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else {
            if (args.length < 2) { //If not enough args
                sender.sendMessage(ChatColor.RED + "No announcement ID specified! Use /ma list to get an announcement's ID.");
            } else if (!Utilities.isValidInt(args[1])) { //If arg is not an integer
                sender.sendMessage(ChatColor.RED + "Invalid announcement ID specified! Use /ma list to get an announcement's ID.");
            } else { //Arg is integer
                int id = Integer.parseInt(args[1]);
                FileConfiguration config = MyAnnouncements.getInstance().getConfig();
                List<String> announcements = config.getStringList("announcements");
                if (id < 1 || id > announcements.size()) { //Arg does not represent valid announcement
                    sender.sendMessage(ChatColor.RED + "Invalid announcement ID specified! Use /ma list to get an announcement's ID.");
                } else { //Arg is 100% valid
                    announcements.remove(id - 1);
                    config.set("announcements", announcements);
                    MyAnnouncements.getInstance().saveConfig();

                    sender.sendMessage(ChatColor.GREEN + "The announcement has been removed!");
                }
            }
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
