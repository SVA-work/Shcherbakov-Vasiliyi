package Exeptions;

public class ArticleUpdateException extends Exception {
  public ArticleUpdateException(String message, ArticleNotFoundException e) {
    super(message, e);
  }
}
