package de.tomwey2.syslog.kafka.data;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

class SuccessLoginEventFactoryTest {

    private final Map<String, String[]> testLogs = Map.of(
            "Jun 20 03:40:59 combo ftpd[8833]: connection from 222.33.90.199 () at Mon Jun 20 03:40:59 2005",
            new String[]{"Jun 20 03:40:59", "connection", "222.33.90.199", "", "Mon Jun 20 03:40:59 2005"},
            "Jun 18 02:08:10 combo ftpd[31275]: connection from 82.252.162.81 (lns-vlq-45-tou-82-252-162-81.adsl.proxad.net) at Sat Jun 18 02:08:10 2005",
            new String[]{"Jun 18 02:08:10", "connection", "82.252.162.81", "lns-vlq-45-tou-82-252-162-81.adsl.proxad.net", "Sat Jun 18 02:08:10 2005"}
    );

    @Test
    public void transformToStringTest() {
        for (Map.Entry<String, String[]> entry : testLogs.entrySet()) {
            SuccessLoginEvent successLoginEvent = SuccessLoginEventFactory.toSuccessLoginEvent(entry.getKey());
            assertEquals(entry.getValue()[1], successLoginEvent.message());
            assertEquals(entry.getValue()[2], successLoginEvent.url());
            assertEquals(entry.getValue()[3], successLoginEvent.host());
            assertEquals(entry.getValue()[4], successLoginEvent.loginTime());
            String json = SuccessLoginEventFactory.toJsonString(successLoginEvent);
            System.out.println(json);
        }
    }

    @Test
    public void regexTest() {
        for (Map.Entry<String, String[]> entry : testLogs.entrySet()) {
            Matcher matcher = SuccessLoginEventFactory.pattern.matcher(entry.getKey());
            assertTrue(matcher.find());
            assertEquals(entry.getValue()[0], matcher.group("timestamp"));
            assertEquals(entry.getValue()[1], matcher.group("message"));
            assertEquals(entry.getValue()[2], matcher.group("url"));
            assertEquals(entry.getValue()[3], matcher.group("host"));
            assertEquals(entry.getValue()[4], matcher.group("loginTime"));
        }
    }

}