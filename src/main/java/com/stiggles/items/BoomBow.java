package com.stiggles.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class BoomBow implements Listener {


    Main main;
    private static final String tag = "pendant";
    private static final String pendantName = ChatColor.RED + "Boom Bow";

    public BoomBow (Main main) {
        this.main = main;
    }
    private ItemStack getBow(){
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();
        meta.setUnbreakable(true);
        meta.setDisplayName(("Boom Bow"));
        meta.setLore(Arrays.asList("This bow is boomin!"));
        meta.setLocalizedName("boom_bow");
        bow.setItemMeta(meta);
        return bow;
    }

    public static String getName () {
        return pendantName;
    }

    public void givePlayerItem (Player p) {
        p.getInventory().addItem(getBow ());
    }

}
