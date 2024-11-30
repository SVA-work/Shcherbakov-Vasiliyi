import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ArticleController;
import org.slf4j.LoggerFactory;
import repositories.ArticleRepository;
import repositories.CommentRepository;
import service.ArticleService;
import spark.Service;

import java.util.List;
import org.slf4j.Logger;

public class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    LOG.info("App started");
    Service service = Service.ignite();
    ObjectMapper objectMapper = new ObjectMapper();
    Application application = new Application(
      List.of(
        new ArticleController(
          service,
            new ArticleService(
              new ArticleRepository(),
                    new CommentRepository()),
            objectMapper
          )
        )
    );
    application.start();
  }
}