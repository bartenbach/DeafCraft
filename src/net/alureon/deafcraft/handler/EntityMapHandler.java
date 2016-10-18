package net.alureon.deafcraft.handler;


import org.bukkit.entity.Player;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class EntityMapHandler {


    private HashMap<Player, HashMap<UUID, Date>> playerMap = new HashMap<Player, HashMap<UUID, Date>>();


    public void addPlayerToMap(Player player) {
        if (!playerMap.containsKey(player)) {
            playerMap.put(player, new HashMap<UUID, Date>());
        }
    }

    public void addEntityToMap(Player player, UUID uuid) {
        playerMap.get(player).put(uuid, new Date());
    }

    public HashMap<UUID, Date> getPlayerEntityMap(Player player) {
        return playerMap.get(player);
    }

}
