package junsulime.cloud.board.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
class PostControllerJsonTest {

    @Autowired
    private JacksonTester<PostController.WritePostRequest> jacksonTester;

    @Test
    void deserializeWritePostRequest() throws IOException {
        String json = "{" +
                "\"title\": \"title\"," +
                "\"content\": \"content\"" +
                "}";

        PostController.WritePostRequest writePostRequest = new PostController.WritePostRequest("title", "content");
        assertEquals(jacksonTester.parse(json).getObject(), writePostRequest);
    }

}