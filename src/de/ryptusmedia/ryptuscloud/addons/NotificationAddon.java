package de.ryptusmedia.ryptuscloud.addons;

import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.addon.CloudAddon;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.api.CloudAPI;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.utilities.annotions.Addon;
import de.ryptusmedia.ryptuscloud.addons.event.CloudBungeeEvent;
import de.ryptusmedia.ryptuscloud.addons.event.CloudVelocityEvent;

@Addon
(
    name = "NotificationAddon",
    version = 1.0,
    authors = {"StonksCloud"},
    description = "Receive messages when a service connects, comes online, stops or has stopped."
)
public class NotificationAddon extends CloudAddon {

    private static final NotificationAddon addon = new NotificationAddon();

    public NotificationAddon(Class<?> clazz) {
        super(clazz);
    }
	
    public NotificationAddon() {}

    @Override
    public void onEnable() {
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
