package net.craftmountain.myannouncements.utilities;

import net.craftmountain.myannouncements.MyAnnouncements;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu implements Listener {

    private static String inventoryTitle = ChatColor.BLUE + "" + ChatColor.BOLD + "MyAnnouncements Menu";

    public static void openGUI(Player ply) {
        Inventory inv = Bukkit.createInventory(null, 9, inventoryTitle);
        boolean isPaused = Announcer.getInstance().isPaused();

        ItemStack toggleItem = new ItemStack(Material.INK_SACK, 1, (short) (isPaused ? 8 : 10));
        ItemMeta toggleMeta = toggleItem.getItemMeta();
        toggleMeta.setDisplayName(ChatColor.GOLD + (isPaused ? "Unpause" : "Pause"));
        toggleItem.setItemMeta(toggleMeta);
        inv.setItem(3, toggleItem);

        ItemStack reloadItem = new ItemStack(Material.BUCKET, 1);
        ItemMeta reloadMeta = reloadItem.getItemMeta();
        reloadMeta.setDisplayName(ChatColor.GOLD + "Reload");
        reloadItem.setItemMeta(reloadMeta);
        inv.setItem(4, reloadItem);

        ItemStack forceItem = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta forceMeta = forceItem.getItemMeta();
        forceMeta.setDisplayName(ChatColor.GOLD + "Force");
        forceItem.setItemMeta(forceMeta);
        inv.setItem(5, forceItem);

        ply.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getInventory().getName().equals(inventoryTitle)) {
            e.setCancelled(true);

            ItemStack item = e.getCurrentItem();
            if (item == null || item.getType().equals(Material.AIR)) return;

            String dName = item.getItemMeta().getDisplayName();
            if (dName != null) {
                if (dName.equals(ChatColor.GOLD + "Pause")) {
                    MyAnnouncements.getInstance().getServer().dispatchCommand(e.getWhoClicked(), "ma toggle");

                    ItemMeta clickedMeta = item.getItemMeta();
                    clickedMeta.setDisplayName(ChatColor.GOLD + "Unpause");
                    item.setItemMeta(clickedMeta);
                    item.setDurability((short) 8);
                } else if (dName.equals(ChatColor.GOLD + "Unpause")) {
                    MyAnnouncements.getInstance().getServer().dispatchCommand(e.getWhoClicked(), "ma toggle");

                    ItemMeta clickedMeta = item.getItemMeta();
                    clickedMeta.setDisplayName(ChatColor.GOLD + "Pause");
                    item.setItemMeta(clickedMeta);
                    item.setDurability((short) 10);
                } else if (dName.equals(ChatColor.GOLD + "Reload")) {
                    MyAnnouncements.getInstance().getServer().dispatchCommand(e.getWhoClicked(), "ma reload");
                } else if (dName.equals(ChatColor.GOLD + "Force")) {
                    MyAnnouncements.getInstance().getServer().dispatchCommand(e.getWhoClicked(), "ma force");
                }
            }
        }
    }

}
