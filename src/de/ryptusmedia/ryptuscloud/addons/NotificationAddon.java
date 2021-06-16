package de.ryptusmedia.ryptuscloud.addons;

import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.addon.CloudAddon;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.api.CloudAPI;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.utilities.annotions.Addon;
import de.ryptusmedia.ryptuscloud.addons.config.NotificationConfig;
import de.ryptusmedia.ryptuscloud.addons.event.CloudBungeeEvent;
import de.ryptusmedia.ryptuscloud.addons.event.CloudVelocityEvent;

@Addon(name = "NotificationAddon", version = 1.0, authors = {"StonksCloud"}, description = "Receive messages when a service connects, comes online, stops or has stopped.")
public class NotificationAddon extends CloudAddon {

    private static final NotificationAddon addon = new NotificationAddon();

    public NotificationAddon(Class<?> clazz) {
        super(clazz);
    }
	
    public NotificationAddon() {}

    @Override
    public void onEnable() {
        this.setConfig(new NotificationConfig("&8┃ &fNotify &8➜ &7Service &a%serviceName% &7is now &astarting&7.", "&8┃ &fNotify &8➜ &7Service &2%serviceName% &7is now &2online&7.", "&8┃ &fNotify &8➜ &7Service &c%serviceName% &7is now &cstopping&7.", "&8┃ &fNotify &8➜ &7Service &4%serviceName% &7is now &4offline&7."));
        switch (CloudAPI.getAPI().getLibraryType()) {
            case BUNGEECORD:
                CloudAPI.getAPI().getEventManager().registerEvent(new CloudBungeeEvent());
                break;
            case VELOCITY:
                CloudAPI.getAPI().getEventManager().registerEvent(new CloudVelocityEvent());
                break;
        }
    }

    public static NotificationAddon getAddon() {
        return addon;
    }

}
