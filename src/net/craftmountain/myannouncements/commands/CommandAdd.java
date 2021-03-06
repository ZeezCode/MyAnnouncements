package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.MyAnnouncements;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class CommandAdd implements MyAnnouncementsCommand {

    private String command;

    public CommandAdd(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.admin"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else { //Sender does have permission
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "No message specified!");
            } else { //Message specified
                StringBuilder message = new StringBuilder();
                for (int i=1; i<args.length; i++) {
                    String str = args[i];
                    message.append(str).append(" ");
                }

                FileConfiguration config = MyAnnouncements.getInstance().getConfig();
                List<String> announcements = config.getStringList("announcements");
                announcements.add(message.toString().substring(0, message.length()-1));
                config.set("announcements", announcements);
                MyAnnouncements.getInstance().saveConfig();

                sender.sendMessage(ChatColor.GREEN + "You have successfully saved a new announcement!");
            }
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
