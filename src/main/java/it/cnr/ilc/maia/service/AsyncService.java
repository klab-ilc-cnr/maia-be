package it.cnr.ilc.maia.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author oakgen
 */
public class AsyncService {

    private static final Map<String, AsyncInfo> INFO = new HashMap<>();

    public enum AsyncStatus {
        PROGRESS,
        DONE,
        ERROR
    }

    @Getter
    @Setter
    public static class AsyncInfo {

        private String uuid;
        private Date created;
        private Date updated;
        private AsyncStatus status;
        private Double percentage;
        private Object data;
    }

    public static synchronized AsyncInfo createAsync() {
        AsyncInfo info = new AsyncInfo();
        info.setUuid(UUID.randomUUID().toString());
        info.setCreated(new Date());
        info.setUpdated(new Date());
        info.setStatus(AsyncStatus.PROGRESS);
        info.setPercentage(0.0);
        INFO.put(info.getUuid(), info);
        return info;
    }

    public static synchronized Collection<AsyncInfo> getInfos() {
        return INFO.values();
    }

    public static synchronized AsyncInfo getInfo(String uuid) {
        return INFO.get(uuid);
    }

    public static synchronized void setPercentage(String uuid, double percentage) {
        AsyncInfo info = INFO.get(uuid);
        info.setUpdated(new Date());
        info.setPercentage(percentage);
    }

    public static synchronized void setDone(String uuid, Object data) {
        AsyncInfo info = INFO.get(uuid);
        info.setUpdated(new Date());
        info.setStatus(AsyncStatus.DONE);
        info.setPercentage(1.0);
        info.setData(data);
    }

    public static synchronized void setError(String uuid, Exception exception) {
        AsyncInfo info = INFO.get(uuid);
        info.setUpdated(new Date());
        info.setStatus(AsyncStatus.ERROR);
        info.setData(exception.getMessage());
    }

    public static synchronized void purgeInfos() {
        INFO.entrySet().removeIf(e -> !e.getValue().getStatus().equals(AsyncStatus.PROGRESS));
    }

}
