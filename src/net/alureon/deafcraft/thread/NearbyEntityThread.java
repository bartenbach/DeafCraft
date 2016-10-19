package net.alureon.deafcraft.thread;


import net.alureon.deafcraft.DeafCraft;
import net.alureon.deafcraft.util.MonsterColor;
import net.alureon.deafcraft.util.MonsterName;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


public class NearbyEntityThread extends Thread {


    private DeafCraft dc;
    private Player player;
    private LivingEntity monster;
    private boolean exit = false;


    public NearbyEntityThread(DeafCraft dc, Player player, LivingEntity monster) {
        this.dc = dc;
        this.player = player;
        this.monster = monster;
    }

    @Override
    public void run() {
        while (!exit) {
            try {
                // sleep for half a second to collect additional nearby entities before announcing
                Thread.sleep(500);

                int monsters = dc.getNearbyEntityHandler().getNearbyEntityMap(player).size();
                player.sendMessage(MonsterColor.getMonsterColor(monster) + MonsterName.getMonsterName(monster) +
                " alerted " + monsters + " nearby " + getReinforcementString(monsters));
                dc.getNearbyEntityHandler().deleteMap(player);

                // kill this thread
                exit = true;
            } catch (InterruptedException ex) {} catch (NullPointerException n) {}
        }
    }

    public String getReinforcementString(int reinforcements) {
        if (reinforcements > 1) {
            return "enemies";
        } else {
            return "enemy";
        }
    }
}
