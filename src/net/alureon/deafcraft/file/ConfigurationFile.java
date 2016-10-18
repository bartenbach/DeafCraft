package net.alureon.deafcraft.file;

import net.alureon.deafcraft.DeafCraft;
import org.bukkit.ChatColor;

public class ConfigurationFile {


    private DeafCraft dc;
    private String notificationMessage;
    private boolean usePermissions;


    public ConfigurationFile(DeafCraft dc) {
        this.dc = dc;
    }

    public void checkFiles() {
        if (!dc.getDataFolder().exists()) {
            dc.getDataFolder().mkdirs();
        }
        dc.getConfig().options().copyDefaults(true);
        dc.saveDefaultConfig();
    }

    public void loadConfig() {
        this.notificationMessage = ChatColor.translateAlternateColorCodes('&', dc.getConfig().getString(Config.notifySentence));
        this.usePermissions = dc.getConfig().getBoolean(Config.usePerms);
    }

    public String getNotificationMessage() {
        return this.notificationMessage;
    }

    public boolean getUsePermissions() {
        return this.usePermissions;
    }

}
