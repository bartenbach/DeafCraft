package net.alureon.deafcraft;

import net.alureon.deafcraft.handler.EntityMapHandler;
import net.alureon.deafcraft.listeners.EntityTargetListener;
import net.alureon.deafcraft.file.ConfigurationFile;
import net.alureon.deafcraft.listeners.PlayerJoinListener;
import net.alureon.deafcraft.util.MonsterColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class DeafCraft extends JavaPlugin {


    private final MonsterColor monsterColor = new MonsterColor(this);
    private final ConfigurationFile configurationFile = new ConfigurationFile(this);
    private final EntityMapHandler entityMapHandler = new EntityMapHandler();
    private final EntityTargetListener entityTargetListener = new EntityTargetListener(this, configurationFile);
    private final PlayerJoinListener playerJoinListener = new PlayerJoinListener(this);


    @Override
    public void onEnable() {
        // Configuration
        configurationFile.checkFiles();
        configurationFile.loadConfig();

        // Register Events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(entityTargetListener, this);
        pm.registerEvents(playerJoinListener, this);
    }

    public EntityMapHandler getEntityMapHandler() {
        return this.entityMapHandler;
    }

}
