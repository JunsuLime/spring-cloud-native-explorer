package junsulime.cloud.board.adapter;

import junsulime.cloud.board.application.PostManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class PostController {

    private final PostManager postManager;


    public PostController(PostManager postManager) {
        this.postManager = postManager;
    }

    public void createPost(@RequestBody WritePostRequest request) {
        postManager.writePost(new PostManager.WritePostSpec(
                request.getTitle(),
                request.getContent()
        ));
    }

    public static class WritePostRequest {

        private String title;

        private String content;

        protected WritePostRequest() {
        }

        public WritePostRequest(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WritePostRequest that = (WritePostRequest) o;
            return Objects.equals(title, that.title) && Objects.equals(content, that.content);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, content);
        }
    }
}
