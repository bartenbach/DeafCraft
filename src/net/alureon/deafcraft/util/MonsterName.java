package net.alureon.deafcraft.util;


import org.apache.commons.lang.WordUtils;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Zombie;

public class MonsterName {


    public static String getMonsterName(Monster monster) {
        StringBuilder sb = new StringBuilder();
        switch (monster.getType()) {
            case ZOMBIE:
                Zombie zombie = (Zombie) monster;
                if (zombie.isBaby()) {
                    sb.append("Baby ");
                }
                switch (zombie.getVillagerProfession()) {
                    case BLACKSMITH:
                        sb.append("Blacksmith Zombie");
                        break;
                    case FARMER:
                        sb.append("Farmer Zombie");
                        break;
                    case BUTCHER:
                        sb.append("Butcher Zombie");
                        break;
                    case HUSK:
                        sb.append("Husk Zombie");
                        break;
                    case LIBRARIAN:
                        sb.append("Librarian Zombie");
                        break;
                    case NORMAL:
                        sb.append("Zombie");
                        break;
                    case PRIEST:
                        sb.append("Priest Zombie");
                        break;
                }
                break;
            default:
                sb.append(WordUtils.capitalizeFully(monster.getName().toLowerCase().replace("_", " ")));
                break;
        }
        return sb.toString();
    }


}
