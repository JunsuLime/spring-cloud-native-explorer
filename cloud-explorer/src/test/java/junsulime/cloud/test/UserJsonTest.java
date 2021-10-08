package junsulime.cloud.test;

import junsulime.cloud.test.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
class UserJsonTest {

    private User user;

    @Autowired
    private JacksonTester<User> jacksonTester;

    // .. 기타 등등의 json tester 를 springboot 에서 제공해준다.

    @BeforeEach
    void setUp() {
        user = new User(1, "user");
    }

    @Test
    void deserializeUser() throws IOException {
        String content = "{" +
                "\"id\": 1," +
                "\"name\": \"user\"" +
                "}";

        assertEquals(jacksonTester.parse(content).getObject(), user);
    }
}