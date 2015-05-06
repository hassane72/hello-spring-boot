package org.xiaoqiaotq.web.messageService;

import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/4.
 */
@ManagedService(path = "/message/myMessage")
public class MessageService {
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Ready
    public String onReady(final AtmosphereResource resource) {
        System.err.println(this);
        this.logger.info("Connected", resource.uuid());
        return "hello";
    }

    @Disconnect
    public void onDisconnect(AtmosphereResourceEvent event) {
        this.logger.info("Client {} disconnected [{}]", event.getResource().uuid(),
                (event.isCancelled() ? "cancelled" : "closed"));
    }

    @org.atmosphere.config.service.Message
    public String onMessage() throws IOException {
        return "hello myMessage";
    }
}
