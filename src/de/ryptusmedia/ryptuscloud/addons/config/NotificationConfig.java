package de.ryptusmedia.ryptuscloud.addons.config;

import java.io.Serializable;

public class NotificationConfig implements Serializable {

    private final String starting, online, stopping, offline;

    public NotificationConfig(String starting, String online, String stopping, String offline) {
        this.starting = starting;
        this.online = online;
        this.stopping = stopping;
        this.offline = offline;
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
