package de.ryptusmedia.ryptuscloud.addons.event;

import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.api.CloudAPI;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.event.LibraryEvent;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.player.NodePlayer;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.utilities.enums.ServiceConnectionType;
import de.ryptusmedia.minecraft.cloudsystem.ryptuscloud.utilities.network.service.Service;
import de.ryptusmedia.ryptuscloud.addons.NotificationAddon;
import de.ryptusmedia.ryptuscloud.addons.config.NotificationConfig;
import net.md_5.bungee.api.ProxyServer;

public class CloudBungeeEvent extends LibraryEvent {

    @Override
    public void onServiceResponseEvent(Service service, ServiceConnectionType statusType) {
        this.notifyPlayers(service, statusType);
    }

    public void notifyPlayers(Service service, ServiceConnectionType serviceConnectionType) {
        NotificationConfig notificationConfig = NotificationAddon.getAddon().getConfig(new NotificationConfig());
        if (notificationConfig == null) return;
        ProxyServer.getInstance().getPlayers().forEach(proxiedPlayer -> {
            NodePlayer nodePlayer = CloudAPI.getAPI().getNodePlayer(proxiedPlayer.getUniqueId());
            if (nodePlayer == null) return;
            switch (serviceConnectionType) {
                case STARTING:
                    if (nodePlayer.hasPermission("ryptuscloud.notification.notify.*") || nodePlayer.hasPermission("ryptuscloud.notification.notify.starting")) {
                        proxiedPlayer.sendMessage(replaceMessage(service, notificationConfig.getStarting()));
                    }
                    break;
                case ONLINE:
                    if (nodePlayer.hasPermission("ryptuscloud.notification.notify.*") || nodePlayer.hasPermission("ryptuscloud.notification.notify.online")) {
                        proxiedPlayer.sendMessage(replaceMessage(service, notificationConfig.getOnline()));
                    }
                    break;
                case STOPPING:
                    if (nodePlayer.hasPermission("ryptuscloud.notification.notify.*") || nodePlayer.hasPermission("ryptuscloud.notification.notify.stopping")) {
                        proxiedPlayer.sendMessage(replaceMessage(service, notificationConfig.getStopping()));
                    }
                    break;
                case STOPPED:
                    if (nodePlayer.hasPermission("ryptuscloud.notification.notify.*") || nodePlayer.hasPermission("ryptuscloud.notification.notify.stopped")) {
                        proxiedPlayer.sendMessage(replaceMessage(service, notificationConfig.getOffline()));
                    }
                    break;
            }
        });
    }

    public String replaceMessage(Service service, String message) {
        return message
                .replace("%serviceName%", service.getServiceName()).replace("%nodeService%", service.getNodeService())
                .replace("%serviceId%", service.getServiceId()).replace("%serviceGameId%", service.getServiceGameId())
                .replace("%serviceGroup%", service.getServiceGroup()).replace("%servicePort%", String.valueOf(service.getServicePort())).replace("&", "ยง");
    }

}
