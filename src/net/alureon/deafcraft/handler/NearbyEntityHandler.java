package net.alureon.deafcraft.handler;


import net.alureon.deafcraft.DeafCraft;
import net.alureon.deafcraft.thread.NearbyEntityThread;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class NearbyEntityHandler {


    // the date objects aren't currently being used for this construct and could be removed
    private HashMap<Player, HashMap<UUID, Date>> nearbyEntityMap = new HashMap<Player, HashMap<UUID, Date>>();
    private DeafCraft dc;


    public NearbyEntityHandler(DeafCraft dc) {
        this.dc = dc;
    }

    public void addNearbyAlertedEntity(Player player, LivingEntity monster) {
        // if the player was already alerted about the entity, they don't care if it's been alerted
        if (dc.getEntityMapHandler().getPlayerEntityMap(player).containsKey(monster.getUniqueId())) {
            return;
        }

        // add this entity to the original map as well, since this one will be deleted shortly after creation
        // if we know it's already been alerted, there's no reason to notify about it again
        dc.getEntityMapHandler().getPlayerEntityMap(player).put(monster.getUniqueId(), new Date());

        // if the player doesn't have a nearby entity map, create one and add the nearby entity
        // this also starts the thread that will collect other nearby entities
        if (nearbyEntityMap.get(player) == null) {
            HashMap<UUID, Date> entityMap = new HashMap<UUID, Date>();
            entityMap.put(monster.getUniqueId(), new Date());
            nearbyEntityMap.put(player, entityMap);
            NearbyEntityThread nearbyEntityThread = new NearbyEntityThread(dc, player, monster);
            nearbyEntityThread.run();

        // if a nearby entity map was already created, add the new nearby entity to the map
        } else {
            HashMap<UUID, Date> entityMap = nearbyEntityMap.get(player);
            entityMap.put(monster.getUniqueId(), new Date());
        }
    }

    public HashMap<UUID, Date> getNearbyEntityMap(Player player) {
        return nearbyEntityMap.get(player);
    }

    public void deleteMap(Player player) {
        nearbyEntityMap.remove(player);
    }

}
