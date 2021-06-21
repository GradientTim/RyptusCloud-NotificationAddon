package de.ryptusmedia.ryptuscloud.addons.config;

import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.utilities.annotions.AddonConfig;

@AddonConfig(configName = "NotificationAddon")
public class NotificationConfig {

    private final String starting, online, stopping, offline;

    public NotificationConfig() {
        this.starting = "&8┃ &fNotify &8➜ &7Service &a%serviceName% &7is now &astarting&7.";
        this.online = "&8┃ &fNotify &8➜ &7Service &2%serviceName% &7is now &2online&7.";
        this.stopping = "&8┃ &fNotify &8➜ &7Service &c%serviceName% &7is now &cstopping&7.";
        this.offline = "&8┃ &fNotify &8➜ &7Service &4%serviceName% &7is now &4offline&7.";
    }

    public String getStarting() {
        return starting;
    }

    public String getOnline() {
        return online;
    }

    public String getStopping() {
        return stopping;
    }

    public String getOffline() {
        return offline;
    }

}
