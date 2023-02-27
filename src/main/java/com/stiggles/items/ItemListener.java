package com.stiggles.items;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;


public class ItemListener implements Listener {


    Main main;

    public ItemListener (Main main) {
        this.main = main;
    }
    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent e) {
        if (!e.hasItem() || !e.getItem().hasItemMeta())
            return;

        ItemMeta im = e.getItem().getItemMeta();
        PersistentDataContainer meta = e.getItem ().getItemMeta ().getPersistentDataContainer ();

        if (!meta.has (main.getClassKey(), PersistentDataType.STRING) || !meta.has (main.getTagKey(), PersistentDataType.STRING))
            return;

        String class_type = meta.get (main.getClassKey(), PersistentDataType.STRING);
        Player p = e.getPlayer();

        if (class_type.equals("vehicle")) {
            if (p.isInsideVehicle())
                return;
        }





    }
}
