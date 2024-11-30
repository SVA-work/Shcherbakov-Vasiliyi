package service;

import Exeptions.*;
import entity.Article;
import entity.ArticleId;
import entity.Comment;
import entity.CommentId;
import repositories.ArticleRepository;
import repositories.CommentRepository;

import java.util.List;

public class ArticleService {

  private final ArticleRepository articleRepository;
  private final CommentRepository commentRepository;

  public ArticleService(ArticleRepository articleRepository, CommentRepository commentRepository) {
    this.articleRepository = articleRepository;
    this.commentRepository = commentRepository;
  }

  public List<Article> findAll() {
    return articleRepository.findAll();
  }

  public Article findById(ArticleId articleId) throws ArticleFindException {
    try {
      return articleRepository.findById(articleId);
    } catch (ArticleNotFoundException e) {
      throw new ArticleFindException("Cannot find article by id=" + articleId.id());
    }
  }

  public long create(String name) throws ArticleCreateException {
    ArticleId articleId = articleRepository.generateId();
    Article article = new Article(articleId, name);
    try {
      articleRepository.create(article);
    } catch (ArticleIdDuplicatedException e) {
      throw new ArticleCreateException("Cannot create article", e);
    }
    return articleId.id();
  }

  public void update(ArticleId articleId, String name) throws ArticleUpdateException {
    Article article;
    try {
      article = articleRepository.findById(articleId);
    } catch (ArticleNotFoundException e) {
      throw new ArticleUpdateException("Cannot find article with id=" + articleId.id(), e);
    }

    try {
      articleRepository.update(
              article.withName(name)
      );
    } catch (ArticleNotFoundException e) {
      throw new ArticleUpdateException("Cannot update article with id=" + articleId.id(), e);
    }
  }

  public void delete(ArticleId articleId) throws ArticleDeleteException {
    try {
      articleRepository.delete(articleId);
    } catch (ArticleNotFoundException e) {
      throw new ArticleDeleteException("Cannot delete article with id=" + articleId, e);
    }
  }

  public void createComment(ArticleId articleId, String text) throws ArticleFindException {
    CommentId newCommentId = commentRepository.generateId();
    Comment comment = new Comment(newCommentId, articleId, text);
    Article article;
    try {
      article = findById(articleId);
    } catch (ArticleFindException e) {
      throw new ArticleFindException("Cannot find article with id " + articleId.id());
    }

    articleRepository.createComment(article, comment);
  }

  public void deleteComment(ArticleId articleId, CommentId commentId) throws ArticleFindException {
    Article article;
    try {
      article = findById(articleId);
    } catch (ArticleFindException e) {
      throw new ArticleFindException("Cannot find article with id " + articleId.id());
    }

    articleRepository.deleteComment(article, commentId);
  }
}
