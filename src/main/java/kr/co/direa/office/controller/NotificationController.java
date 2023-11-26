package kr.co.direa.office.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
    private final SimpMessagingTemplate messagingTemplate;

    public NotificationController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage message) throws Exception {
////        Thread.sleep(1000); // simulated delay
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
//    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public void greeting() throws Exception {
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        messagingTemplate.convertAndSend("/topic/dev", "test api called!");

//        messagingTemplate.convertAndSend (
//                "/topic/admin/notification",
//                new Notification("")
//        );
    }


}
