package repositories;

import Exeptions.ArticleIdDuplicatedException;
import Exeptions.ArticleNotFoundException;
import entity.Article;
import entity.ArticleId;
import entity.Comment;
import entity.CommentId;

import java.util.List;

public interface ArticleRepositoryInterface {

  ArticleId generateId();

  List<Article> findAll();

  Article findById(ArticleId articleId) throws ArticleNotFoundException;

  void create(Article article) throws ArticleIdDuplicatedException;

  void update(Article article) throws ArticleNotFoundException;

  void delete(ArticleId articleId) throws ArticleNotFoundException;

  void createComment(Article article, Comment comment);

  void deleteComment(Article article, CommentId commentId);
}
