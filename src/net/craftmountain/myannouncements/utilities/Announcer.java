package net.craftmountain.myannouncements.utilities;

import net.craftmountain.myannouncements.MyAnnouncements;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

import java.util.List;

public class Announcer {

    private static Announcer announcer;
    private BukkitTask announcementTask;
    private int curAnnouncement = 0;

    private boolean isPaused;

    public Announcer() {
        announcer = this;
        isPaused = false;

        int delay = MyAnnouncements.getInstance().getConfig().getInt("delay");
        announcementTask = Bukkit.getScheduler().runTaskTimerAsynchronously(MyAnnouncements.getInstance(), () -> {
            List<String> announcements = MyAnnouncements.getInstance().getConfig().getStringList("announcements");
            if (!isPaused && announcements.size() > 0) {
                if (curAnnouncement >= announcements.size())
                    curAnnouncement = 0;

                announceMessage(announcements.get(curAnnouncement));
                curAnnouncement++;
            }
        }, delay * 20, delay * 20);
    }

    public static Announcer getInstance() {
        if (announcer == null)
            announcer = new Announcer();
        return announcer;
    }

    public static void announceMessage(String message) {
        message = ChatColor.translateAlternateColorCodes('&', Utilities.getPrefix() + ChatColor.YELLOW + message);
        Bukkit.broadcastMessage(message);
    }

    public int getCurAnnouncement() {
        return curAnnouncement;
    }


    public void setCurAnnouncement(int curAnnouncement) {
        this.curAnnouncement = curAnnouncement;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public void cancelAnnouncementTask() {
        announcementTask.cancel();
        announcementTask = null;
    }

}
