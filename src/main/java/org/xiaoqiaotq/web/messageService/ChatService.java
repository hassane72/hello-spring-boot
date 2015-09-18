package org.xiaoqiaotq.web.messageService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.atmosphere.config.managed.Decoder;
import org.atmosphere.config.managed.Encoder;
import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xiaoqiaotq.service.UserService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;

@ManagedService(path = "/message/chat")
public class ChatService {

	private final Logger logger = LoggerFactory.getLogger(ChatService.class);

	@Inject
			@Singleton
	BroadcasterFactory broadcasterFactory;

	@Autowired
	private UserService userService;

	@Ready
	public void onReady(final AtmosphereResource resource) {
		System.err.println(this);
		System.err.println(broadcasterFactory);
		DefaultBroadcaster user = broadcasterFactory.lookup(DefaultBroadcaster.class, "zhsan", true);
//		resource.addBroadcaster(user);
		user.addAtmosphereResource(resource);
		this.logger.info("Connected {}", resource.uuid());
	}

	@Disconnect
	public void onDisconnect(AtmosphereResourceEvent event) {
		this.logger.info("Client {} disconnected [{}]", event.getResource().uuid(),
				(event.isCancelled() ? "cancelled" : "closed"));
	}

	@org.atmosphere.config.service.Message(encoders = JacksonEncoderDecoder.class, decoders = JacksonEncoderDecoder.class)
	public Message onMessage(Message message) throws IOException {
		this.logger.info("Author {} sent message {}", message.getAuthor(),
				message.getMessage());
		return message;
	}

	public static class JacksonEncoderDecoder implements Encoder<Message, String>,
			Decoder<String, Message> {

		private final ObjectMapper mapper = new ObjectMapper();

		@Override
		public String encode(Message m) {
			try {
				return this.mapper.writeValueAsString(m);
			}
			catch (IOException ex) {
				throw new IllegalStateException(ex);
			}
		}

		@Override
		public Message decode(String s) {
			try {
				return this.mapper.readValue(s, Message.class);
			}
			catch (IOException ex) {
				throw new IllegalStateException(ex);
			}
		}

	}

}