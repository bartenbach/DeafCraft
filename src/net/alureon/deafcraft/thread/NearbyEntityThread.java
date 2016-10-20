package net.alureon.deafcraft.thread;


import net.alureon.deafcraft.DeafCraft;
import net.alureon.deafcraft.util.MonsterColor;
import net.alureon.deafcraft.util.MonsterName;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


public class NearbyEntityThread implements Runnable {


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
                // sleep and wait for list to populate
                Thread.sleep(1000);

                int monsters = dc.getNearbyEntityHandler().getNearbyEntityList(player).size();

                player.sendMessage(MonsterColor.getMonsterColor(monster) + MonsterName.getMonsterName(monster) +
                " alerted " + monsters + " nearby " + getReinforcementString(monsters));
                dc.getNearbyEntityHandler().removePlayerFromMap(player);

                // kill this thread
                exit = true;
            } catch (InterruptedException ex) {} catch (NullPointerException n) {}
        }
    }

    private String getReinforcementString(int reinforcements) {
        if (reinforcements > 1) {
            return "enemies";
        } else {
            return "enemy";
        }
    }
}
