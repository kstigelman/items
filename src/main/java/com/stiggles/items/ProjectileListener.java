package com.stiggles.items;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ProjectileListener implements Listener {


    private boolean isBow(ItemStack item){
        return(item.hasItemMeta()
                && item.getItemMeta().hasLocalizedName()
                && item.getItemMeta().getLocalizedName().equals("boom_bow"));
    }

    Main main;

    ProjectileListener (Main main) {this.main = main;}

    @EventHandler
    public void onShoot(EntityShootBowEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        if (isBow(e.getBow())){
            e.getProjectile().getPersistentDataContainer().set(main.getTagKey(), PersistentDataType.STRING, "boom_arrow");
        }
    }

    @EventHandler
    public void onArrowDamage (ProjectileHitEvent e) {
        if (e.getHitEntity() == null)
            return;

        PersistentDataContainer container = e.getEntity().getPersistentDataContainer();

        if (!container.has(main.getTagKey(), PersistentDataType.STRING)) return;
        if (container.get(main.getTagKey(), PersistentDataType.STRING).equals("boom_arrow")) {
            Entity hitEntity = e.getHitEntity();
            // TNTPrimed tnt = (TNTPrimed) hitEntity.getWorld().spawnEntity(hitEntity.getLocation(), EntityType.PRIMED_TNT);
            // tnt.setFuseTicks(0);
            hitEntity.getWorld().createExplosion(hitEntity.getLocation(), .5F);
        }
    }
}
