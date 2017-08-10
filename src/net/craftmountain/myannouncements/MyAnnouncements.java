package net.craftmountain.myannouncements;

import net.craftmountain.myannouncements.commands.*;
import net.craftmountain.myannouncements.utilities.Announcer;
import net.craftmountain.myannouncements.utilities.Menu;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class MyAnnouncements extends JavaPlugin {

    private ArrayList<MyAnnouncementsCommand> executors;
    private static MyAnnouncements plugin;
    private Permission permission = null;
    private static Announcer announcer;

    public void onEnable() {
        PluginDescriptionFile pdfFile = getDescription();
        getLogger().info("Enabling " + pdfFile.getName() + " v." + pdfFile.getVersion() + "...");

        saveDefaultConfig();
        plugin = this;
        announcer = new Announcer();
        getCommand("myannouncements").setExecutor(new CommandMyAnnouncements());
        setupPermissions();
        Bukkit.getPluginManager().registerEvents(new Menu(), this);

        executors = new ArrayList<>();
        executors.add(new CommandVersion("version"));
        executors.add(new CommandReload("reload"));
        executors.add(new CommandToggle("toggle"));
        executors.add(new CommandBroadcast("broadcast"));
        executors.add(new CommandForce("force"));
        executors.add(new CommandAdd("add"));
        executors.add(new CommandList("list"));
        executors.add(new CommandRemove("remove"));
        executors.add(new CommandMenu("menu"));

        getLogger().info(pdfFile.getName() + " has been successfully enabled!");
    }

    public void onDisable() {
        PluginDescriptionFile pdfFile = getDescription();
        getLogger().info("Disabling " + pdfFile.getName() + "!");

        announcer.cancelAnnouncementTask();
        announcer = null;

        getLogger().info(pdfFile.getName() + " has been successfully disabled!");
    }

    public MyAnnouncementsCommand getExecutor(String command) {
        return executors.stream().filter(executor -> executor.getCommand().equalsIgnoreCase(command)).findFirst().orElse(null);
    }

    public Permission getPermissions() {
        return permission;
    }

    public static MyAnnouncements getInstance() {
        return plugin;
    }

    public void reloadAnnouncer() {
        announcer.cancelAnnouncementTask();
        reloadConfig();
        announcer = new Announcer();
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if (permissionProvider != null) {
            permission = permissionProvider.getProvider();
        }
        return (permission != null);
    }

}
