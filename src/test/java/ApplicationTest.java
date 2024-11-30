import Exeptions.ArticleFindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ArticleController;
import entity.Article;
import entity.ArticleId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.ArticleRepository;
import repositories.CommentRepository;
import service.ArticleService;
import spark.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

  private Service service;

  @BeforeEach
  void beforeEach() {
    service = Service.ignite();
  }

  @AfterEach
  void afterEach() {
    service.stop();
    service.awaitStop();
  }

  @Test
  void findAll() throws IOException, InterruptedException, ArticleFindException {
    ObjectMapper objectMapper = new ObjectMapper();
    ArticleService articleService = new ArticleService(new ArticleRepository(), new CommentRepository());
    Application application = new Application(List.of(new ArticleController(service, articleService, objectMapper)));
    application.start();
    service.awaitInitialization();
    HttpResponse<String> createArticleResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                        {
                                                          "name": "qwerty"
                                                        }
                                                    """
                                    )
                            )
                            .uri(URI.create("http://localhost:4567/api/articles"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, createArticleResponse.statusCode());

    HttpResponse<String> createCommentResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .POST(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    {
                                                      "articleId": "1",
                                                      "text": "123"
                                                    }
                                                    """
                                    )
                            )
                            .uri(URI.create("http://localhost:4567/api/comments"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, createCommentResponse.statusCode());

    HttpResponse<String> articleUpdateRequest = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .PUT(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    {
                                                      "articleId": "1",
                                                      "name": "qwerty updated"
                                                    }
                                                    """
                                    )
                            )
                            .uri(URI.create("http://localhost:4567/api/articles/update"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, articleUpdateRequest.statusCode());

    HttpResponse<String> deleteCommentResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .DELETE()
                            .uri(URI.create("http://localhost:4567/api/comments/delete/1/0"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, deleteCommentResponse.statusCode());

    HttpResponse<String> getArticleResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:4567/api/articles/get/1"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, getArticleResponse.statusCode());

    Article article = articleService.findById(new ArticleId(1));
    assertEquals(article.getName(), "qwerty updated");
    assertEquals(article.getCountOfComments(), 0);

    HttpResponse<String> deleteArticleRequest = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .DELETE()
                            .uri(URI.create("http://localhost:4567/api/articles/delete/1"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(201, deleteArticleRequest.statusCode());

    HttpResponse<String> badDeleteArticleRequest = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .DELETE()
                            .uri(URI.create("http://localhost:4567/api/articles/delete/1"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(400, badDeleteArticleRequest.statusCode());

    HttpResponse<String> badGetArticleResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .GET()
                            .uri(URI.create("http://localhost:4567/api/articles/get/1"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(400, badGetArticleResponse.statusCode());

    HttpResponse<String> badDeleteCommentResponse = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .DELETE()
                            .uri(URI.create("http://localhost:4567/api/comments/delete/1/0"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(400, badDeleteCommentResponse.statusCode());

    HttpResponse<String> badArticleUpdateRequest = HttpClient.newHttpClient()
            .send(
                    HttpRequest.newBuilder()
                            .PUT(
                                    HttpRequest.BodyPublishers.ofString(
                                            """
                                                    {
                                                      "articleId": "1",
                                                      "name": "qwerty updated"
                                                    }
                                                    """
                                    )
                            )
                            .uri(URI.create("http://localhost:4567/api/articles/update"))
                            .build(),
                    HttpResponse.BodyHandlers.ofString(UTF_8)
            );
    assertEquals(400, badArticleUpdateRequest.statusCode());
  }
}