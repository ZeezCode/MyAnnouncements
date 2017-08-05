package net.craftmountain.myannouncements.utilities;

import net.craftmountain.myannouncements.MyAnnouncements;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utilities {

    /**
     * <p>Returns prefix set in plugin config file</p>
     *
     * @return String Prefix set in plugin config file
     */
    public static String getPrefix() {
        return MyAnnouncements.getInstance().getConfig().getString("prefix");
    }

    /**
     * <p>Returns whether or not sender has a permission</p>
     *
     * @param sender CommandSender to be checked
     * @param permission Permission we're checking for
     * @return boolean Whether or not CommandSender has the permission
     */
    public static boolean hasPermission(CommandSender sender, String permission) {
        return (!(sender instanceof Player)) || MyAnnouncements.getInstance().getPermissions().has(sender, permission);
    }

    /**
     * <p>Returns whether or not given String is a valid integer</p>
     *
     * @param toParse String to be parsed
     * @return boolean Whether or not given String is a valid integer
     */
    public static boolean isValidInt(String toParse) {
        try {
            Integer.parseInt(toParse);
        } catch(Exception e) {
            return false;
        }
        return true;
    }

}
