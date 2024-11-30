package repositories;

import Exeptions.ArticleIdDuplicatedException;
import Exeptions.ArticleNotFoundException;
import entity.Article;
import entity.ArticleId;
import entity.Comment;
import entity.CommentId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ArticleRepository implements ArticleRepositoryInterface {

  private final AtomicLong nextId = new AtomicLong(0);
  private final Map<Long, Article> articleMap = new ConcurrentHashMap<>();


  @Override
  public ArticleId generateId() {
    return new ArticleId(nextId.incrementAndGet());
  }

  @Override
  public List<Article> findAll() {
    return new ArrayList<>(articleMap.values());
  }

  @Override
  public Article findById(ArticleId articleId) throws ArticleNotFoundException {
    Article book = articleMap.get(articleId.id());
    if (book == null) {
      throw new ArticleNotFoundException("Cannot find article by id=" + articleId.id());
    }
    return book;
  }

  @Override
  public synchronized void create(Article article) throws ArticleIdDuplicatedException {
    if (articleMap.get(article.getId().id()) != null) {
      throw new ArticleIdDuplicatedException("Article with the given id already exists: " + article.getId().id());
    }
    articleMap.put(article.getId().id(), article);
  }

  @Override
  public synchronized void update(Article article) throws ArticleNotFoundException {
    if (articleMap.get(article.getId().id()) == null) {
      throw new ArticleNotFoundException("Cannot find article by id=" + article.getId().id());
    }
    articleMap.put(article.getId().id(), article);
  }

  @Override
  public void delete(ArticleId articleId) throws ArticleNotFoundException {
    if (articleMap.remove(articleId.id()) == null) {
      throw new ArticleNotFoundException("Cannot find article by id=" + articleId.id());
    }
  }

  @Override
  public void createComment(Article article, Comment comment) {
    article.addComment(comment);
  }

  @Override
  public void deleteComment(Article article, CommentId commentId) {
    article.deleteComment(commentId);
  }
}
