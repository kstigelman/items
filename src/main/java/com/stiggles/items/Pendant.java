package com.stiggles.items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.spigotmc.event.entity.EntityDismountEvent;

import java.util.UUID;
import java.util.jar.Attributes;

public class Pendant implements Listener {

    Main main;
    private static final String tag = "pendant";
    private static final String pendantName = ChatColor.LIGHT_PURPLE + "Natalie's Pendant";
    private static ItemStack pendantItem = new ItemStack(Material.CHARCOAL);

    public Pendant (Main main) {
        this.main = main;
        createItem ();
    }

    private void createItem () {
        ItemStack is = pendantItem;
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(pendantName);
        im.getPersistentDataContainer().set(main.getTagKey(), PersistentDataType.STRING, tag);
        im.getPersistentDataContainer().set(main.getClassKey(), PersistentDataType.STRING, "vehicle");
        is.setItemMeta(im);
        pendantItem = is;
    }

    public static String getName () {
        return pendantName;
    }
    public static void givePlayerItem (Player p) {
        if (pendantItem == null)
            return;
        p.getInventory().addItem(pendantItem);
    }


    public boolean listen (PlayerInteractEvent e) {
        return true;
    }

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent e) {

        Player p = e.getPlayer();

        ItemMeta im = e.getItem().getItemMeta();
        if (!im.getPersistentDataContainer().has(main.getTagKey(), PersistentDataType.STRING))
            return;

        if (!im.getPersistentDataContainer().get(main.getTagKey(), PersistentDataType.STRING).equals(tag))
            return;


        Horse natalie = p.getWorld().spawn(p.getLocation(), Horse.class);

        natalie.setStyle(Horse.Style.WHITE_DOTS);
        natalie.setColor(Horse.Color.GRAY);
        natalie.setCustomName(ChatColor.DARK_PURPLE + "Natalie");


        natalie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        natalie.setJumpStrength(0.7);

        natalie.setCustomNameVisible(true);
        natalie.getPersistentDataContainer().set(main.getTagKey(), PersistentDataType.STRING, tag);
        natalie.setOwner(p);
        natalie.setBreed(false);
        natalie.setAgeLock(true);
        natalie.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        natalie.getInventory().setArmor (new ItemStack(Material.DIAMOND_HORSE_ARMOR));
        natalie.addPassenger(p);
        Bukkit.getConsoleSender().sendMessage("Spawned " + natalie.getCustomName() + "!");
        Bukkit.getConsoleSender().sendMessage(p.getName() + "'s UUID: " + p.getUniqueId());
    }

    @EventHandler
    public void onDismountEvent (EntityDismountEvent e) {
        if (!(e.getEntity() instanceof Player))
            return;

        if (!(e.getDismounted() instanceof Horse))
            return;

        Horse dismounted = (Horse) e.getDismounted();
        if (!dismounted.getPersistentDataContainer().has (main.getTagKey(), PersistentDataType.STRING))
            return;

        String thisTag = dismounted.getPersistentDataContainer().get(main.getTagKey(), PersistentDataType.STRING);
        if (!tag.equals (thisTag))
            return;

        dismounted.remove();
        Bukkit.getConsoleSender().sendMessage("Removed " + dismounted.getCustomName());

    }
}
