package Exeptions;

public class ArticleDeleteException extends  Exception {
  public ArticleDeleteException(String message, ArticleNotFoundException e) {
    super(message, e);
  }
}
