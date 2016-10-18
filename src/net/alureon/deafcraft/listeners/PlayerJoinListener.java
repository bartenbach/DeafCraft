package net.alureon.deafcraft.listeners;


import net.alureon.deafcraft.DeafCraft;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {


    private DeafCraft dc;


    public PlayerJoinListener(DeafCraft dc) {
        this.dc = dc;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        dc.getEntityMapHandler().addPlayerToMap(event.getPlayer());
    }
}
