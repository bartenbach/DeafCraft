package net.alureon.deafcraft.listeners;

import net.alureon.deafcraft.DeafCraft;
import net.alureon.deafcraft.util.MonsterColor;
import net.alureon.deafcraft.util.MonsterName;
import net.alureon.deafcraft.file.ConfigurationFile;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import java.util.Date;

public class EntityTargetListener implements Listener {


    private DeafCraft dc;
    private ConfigurationFile cf;


    public EntityTargetListener(DeafCraft dc, ConfigurationFile cf) {
        this.dc = dc;
        this.cf = cf;
    }


    //TODO ghast not working, magma cube not working
    @EventHandler
    public void onMonsterTarget(final EntityTargetLivingEntityEvent event) {

        // Most monsters are instanceof Monster, except like four...those don't count for some reason
        if ((event.getTarget() instanceof Player) && (event.getEntity() instanceof Monster
                || event.getEntity().getType() == EntityType.GHAST
                || event.getEntity().getType() == EntityType.MAGMA_CUBE
                || event.getEntity().getType() == EntityType.SHULKER
                || event.getEntity().getType() == EntityType.SLIME)) {
            final Player player = (Player) event.getTarget();
            final LivingEntity monster = (LivingEntity) event.getEntity();

            // check if either they have the permission or we're not using permissions at all
            if (player.hasPermission("deafcraft.notify") || !cf.getUsePermissions()) {
                if (event.getReason() == EntityTargetEvent.TargetReason.TARGET_ATTACKED_NEARBY_ENTITY) {
                    dc.getNearbyEntityHandler().addNearbyAlertedEntity(player, monster);
                } else {
                    checkEntitymap(player, monster);
                }
            }
        }
    }

    private void checkEntitymap(Player player, LivingEntity monster) {
        //System.out.println(monster.getUniqueId());
        if (dc.getEntityMapHandler().getPlayerEntityMap(player).containsKey(monster.getUniqueId())) {
            if ((new Date().getTime() - 20000) > dc.getEntityMapHandler().getPlayerEntityMap(player).get(monster.getUniqueId()).getTime()) {
                dc.getEntityMapHandler().getPlayerEntityMap(player).put(monster.getUniqueId(), new Date());
                ChatColor monsterColor = MonsterColor.getMonsterColor(monster);
                sendNotification(player, monster, monsterColor);
            }
        } else {
            dc.getEntityMapHandler().addEntityToMap(player, monster.getUniqueId());
            ChatColor monsterColor = MonsterColor.getMonsterColor(monster);
            sendNotification(player, monster, monsterColor);
        }
    }

    private void sendNotification(Player player, LivingEntity monster, ChatColor monstercolor) {
        StringBuilder sb = new StringBuilder();
        sb.append(monstercolor);
        sb.append(cf.getNotificationMessage().replace("%MONSTER%", MonsterName.getMonsterName(monster)));
        player.sendMessage(sb.toString());
    }

 }
