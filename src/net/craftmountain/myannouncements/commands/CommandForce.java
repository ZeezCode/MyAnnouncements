package net.craftmountain.myannouncements.commands;

import net.craftmountain.myannouncements.MyAnnouncements;
import net.craftmountain.myannouncements.utilities.Announcer;
import net.craftmountain.myannouncements.utilities.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandForce implements MyAnnouncementsCommand {

    private String command;

    public CommandForce(String command) {
        this.command = command;
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (!(Utilities.hasPermission(sender, "ma.force"))) {
            sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
        } else {
            Announcer ann = Announcer.getInstance();
            List<String> announcements = MyAnnouncements.getInstance().getConfig().getStringList("announcements");

            if (announcements.size() == 0) {
                sender.sendMessage(ChatColor.RED + "There are no announcements saved!");
                return;
            }

            boolean changed = !ann.isPaused(); //Changed if not paused, not changed if already paused
            if (changed)
                ann.setPaused(true);

            if (ann.getCurAnnouncement() >= announcements.size())
                ann.setCurAnnouncement(0);

            Announcer.announceMessage(announcements.get(ann.getCurAnnouncement()));
            ann.setCurAnnouncement(ann.getCurAnnouncement() + 1);

            if (changed) { //Set timer to unpause announcements (go back to how it was)
                Bukkit.getScheduler().runTaskLaterAsynchronously(MyAnnouncements.getInstance(), () -> {
                    if (ann.isPaused()) //In case someone already unpaused it manually
                        ann.setPaused(false);
                }, MyAnnouncements.getInstance().getConfig().getInt("delay") * 20);
            }
        }
    }

    @Override
    public String getCommand() {
        return command;
    }

}
