package de.ryptusmedia.ryptuscloud.addons.event;

import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.VelocityCloudLibrary;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.api.CloudAPI;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.event.LibraryEvent;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.player.NodePlayer;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.utilities.enums.ServiceConnectionType;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.utilities.network.service.Service;
import de.ryptusmedia.ryptuscloud.addons.NotificationAddon;
import de.ryptusmedia.ryptuscloud.addons.config.NotificationConfig;
import net.kyori.text.TextComponent;

public class CloudVelocityEvent extends LibraryEvent {

    @Override
    public void onServiceResponseEvent(Service service, ServiceConnectionType statusType) {
        this.notifyPlayers(service, statusType);
    }

    public void notifyPlayers(Service service, ServiceConnectionType serviceConnectionType) {
        NotificationConfig notificationConfig = NotificationAddon.getAddon().getConfig(new NotificationConfig());
        if (notificationConfig == null) return;
        VelocityCloudLibrary.getService().getVelocityServer().getAllPlayers().forEach(player -> {
            NodePlayer nodePlayer = CloudAPI.getAPI().getNodePlayer(player.getUniqueId());
            if (nodePlayer == null) {
                return;
            }
            switch (serviceConnectionType) {
                case STARTING:
                    if (nodePlayer.hasPermission("notification.notify.*") || nodePlayer.hasPermission("notification.notify.starting")) {
                        player.sendMessage(replaceMessage(service, notificationConfig.getStarting()));
                    }
                    break;
                case ONLINE:
                    if (nodePlayer.hasPermission("notification.notify.*") || nodePlayer.hasPermission("notification.notify.online")) {
                        player.sendMessage(replaceMessage(service, notificationConfig.getOnline()));
                    }
                    break;
                case STOPPING:
                    if (nodePlayer.hasPermission("notification.notify.*") || nodePlayer.hasPermission("notification.notify.stopping")) {
                        player.sendMessage(replaceMessage(service, notificationConfig.getStopping()));
                    }
                    break;
                case STOPPED:
                    if (nodePlayer.hasPermission("notification.notify.*") || nodePlayer.hasPermission("notification.notify.stopped")) {
                        player.sendMessage(replaceMessage(service, notificationConfig.getOffline()));
                    }
                    break;
            }
        });
    }

    public TextComponent replaceMessage(Service service, String message) {
        return TextComponent.of(message
                .replace("%serviceName%", service.getServiceName()).replace("%nodeService%", service.getNodeService())
                .replace("%serviceId%", service.getServiceId()).replace("%serviceGameId%", service.getServiceGameId())
                .replace("%serviceGroup%", service.getServiceGroup()).replace("%servicePort%", String.valueOf(service.getServicePort())).replace("&", "ยง"));
    }

}
