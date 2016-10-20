package net.alureon.deafcraft.handler;


import net.alureon.deafcraft.DeafCraft;
import net.alureon.deafcraft.thread.NearbyEntityThread;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class NearbyEntityHandler {


    private HashMap<Player, ArrayList<UUID>> nearbyEntityMap = new HashMap<Player, ArrayList<UUID>>();
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
        if (!nearbyEntityMap.containsKey(player)) {
            ArrayList<UUID> entityList = new ArrayList<UUID>();
            entityList.add(monster.getUniqueId());
            nearbyEntityMap.put(player, entityList);
            new Thread(new NearbyEntityThread(dc, player, monster)).start();


        // if a nearby entity map was already created, add the new nearby entity to the list
        } else {
            nearbyEntityMap.get(player).add(monster.getUniqueId());
        }
    }

    public ArrayList<UUID> getNearbyEntityList(Player player) {
        return nearbyEntityMap.get(player);
    }

    public void removePlayerFromMap(Player player) {
        nearbyEntityMap.remove(player);
    }

}
