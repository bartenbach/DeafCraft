package net.alureon.deafcraft.listeners;


import net.alureon.deafcraft.DeafCraft;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathListener implements Listener {


    private DeafCraft dc;


    public EntityDeathListener(DeafCraft dc) {
        this.dc = dc;
    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        for (Player p : dc.getServer().getOnlinePlayers()) {
            if (dc.getEntityMapHandler().getPlayerEntityMap(p).containsKey(event.getEntity().getUniqueId())) {
                dc.getEntityMapHandler().getPlayerEntityMap(p).remove(event.getEntity().getUniqueId());
                System.out.println("Removed entity " + event.getEntity().getUniqueId());
            }
        }
    }
}
