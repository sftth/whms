package com.summit.whms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class SessionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);

    @GetMapping("/hi")
    public Map<String, String> getSession(HttpSession session) {
        UUID uid = Optional.ofNullable((UUID) session.getAttribute("uid")).orElse(UUID.randomUUID());
        session.setAttribute("uid", uid);
        LOGGER.info("&&&&&&&&&***** TET");
        Map<String, String> m = new HashMap<>();
        m.put("instance_ip", "13.125.178.249");
        m.put("uuid", uid.toString());

        return m;
    }
}
