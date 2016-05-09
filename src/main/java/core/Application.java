package core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import core.clients.ChatServer;

@SpringBootApplication
@ImportResource("classpath:SpringBeans.xml")
public class Application {

	    public static void main(String[] args) {
	        SpringApplication.run(Application.class, args);
	        (new ChatServer()).start();;
	    }
}

