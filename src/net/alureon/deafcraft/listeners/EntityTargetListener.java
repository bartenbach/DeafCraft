package net.alureon.deafcraft.listeners;

import net.alureon.deafcraft.DeafCraft;
import net.alureon.deafcraft.util.MonsterColor;
import net.alureon.deafcraft.util.MonsterName;
import net.alureon.deafcraft.file.ConfigurationFile;
import org.bukkit.ChatColor;
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

    @EventHandler
    public void onMonsterTarget(final EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player && event.getEntity() instanceof Monster) {
            final Player player = (Player) event.getTarget();
            final Monster monster = (Monster) event.getEntity();

            if (player.hasPermission("deafcraft.notify")) {
                if (event.getReason() == EntityTargetEvent.TargetReason.REINFORCEMENT_TARGET) {
                    System.out.println("Reinforcement called");
                }
                checkEntitymap(player, monster);
            }
        }
    }

    private void checkEntitymap(Player player, Monster monster) {
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

    private void sendNotification(Player player, Monster monster, ChatColor monstercolor) {
        StringBuilder sb = new StringBuilder();
        sb.append(monstercolor);
        sb.append(cf.getNotificationMessage().replace("%MONSTER%", MonsterName.getMonsterName(monster)));
        player.sendMessage(sb.toString());
    }

 }
