package net.alureon.deafcraft.util;


import net.alureon.deafcraft.DeafCraft;
import net.alureon.deafcraft.file.Config;
import org.bukkit.ChatColor;
import org.bukkit.entity.LivingEntity;


public class MonsterColor {


    private static DeafCraft dc;


    public MonsterColor(DeafCraft dc) {
        this.dc = dc;
    }


    public static ChatColor getMonsterColor(LivingEntity monster) {
        switch (monster.getType()) {
            case CREEPER:
                return ChatColor.valueOf(dc.getConfig().getString(Config.creeperColor).toUpperCase().replace(" ", "_"));
            case SKELETON:
                return ChatColor.valueOf(dc.getConfig().getString(Config.skeletonColor).toUpperCase().replace(" ", "_"));
            case ENDER_DRAGON:
                return ChatColor.valueOf(dc.getConfig().getString(Config.enderDragonColor).toUpperCase().replace(" ", "_"));
            case CAVE_SPIDER:
                return ChatColor.valueOf(dc.getConfig().getString(Config.caveSpiderColor).toUpperCase().replace(" ", "_"));
            case PIG_ZOMBIE:
                return ChatColor.valueOf(dc.getConfig().getString(Config.pigZombieColor).toUpperCase().replace(" ", "_"));
            case BLAZE:
                return ChatColor.valueOf(dc.getConfig().getString(Config.blazeColor).toUpperCase().replace(" ", "_"));
            case ENDERMAN:
                return ChatColor.valueOf(dc.getConfig().getString(Config.endermanColor).toUpperCase().replace(" ", "_"));
            case GHAST:
                return ChatColor.valueOf(dc.getConfig().getString(Config.ghastColor).toUpperCase().replace(" ", "_"));
            case GUARDIAN:
                return ChatColor.valueOf(dc.getConfig().getString(Config.guardianColor).toUpperCase().replace(" ", "_"));
            case MAGMA_CUBE:
                return ChatColor.valueOf(dc.getConfig().getString(Config.magmaCubeColor).toUpperCase().replace(" ", "_"));
            case ENDERMITE:
                return ChatColor.valueOf(dc.getConfig().getString(Config.endermiteColor).toUpperCase().replace(" ", "_"));
            case SHULKER:
                return ChatColor.valueOf(dc.getConfig().getString(Config.shulkerColor).toUpperCase().replace(" ", "_"));
            case SILVERFISH:
                return ChatColor.valueOf(dc.getConfig().getString(Config.silverfishColor).toUpperCase().replace(" ", "_"));
            case SLIME:
                return ChatColor.valueOf(dc.getConfig().getString(Config.slimeColor).toUpperCase().replace(" ", "_"));
            case SPIDER:
                return ChatColor.valueOf(dc.getConfig().getString(Config.spiderColor).toUpperCase().replace(" ", "_"));
            case WITCH:
                return ChatColor.valueOf(dc.getConfig().getString(Config.witchColor).toUpperCase().replace(" ", "_"));
            case WITHER:
                return ChatColor.valueOf(dc.getConfig().getString(Config.witherColor).toUpperCase().replace(" ", "_"));
            case ZOMBIE:
                return ChatColor.valueOf(dc.getConfig().getString(Config.zombieColor).toUpperCase().replace(" ", "_"));
            default:
                return ChatColor.GREEN;
        }
    }

}
