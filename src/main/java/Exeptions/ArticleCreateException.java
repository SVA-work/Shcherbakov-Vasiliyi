package Exeptions;

public class ArticleCreateException extends Exception {
  public ArticleCreateException(String message, ArticleIdDuplicatedException e) {
    super(message, e);
  }
}
