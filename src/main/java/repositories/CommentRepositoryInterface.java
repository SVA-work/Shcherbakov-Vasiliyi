package repositories;

import Exeptions.CommentIdDuplicatedException;
import Exeptions.CommentNotFoundException;
import entity.Comment;
import entity.CommentId;

import java.util.List;

public interface CommentRepositoryInterface {

  CommentId generateId();

  List<Comment> findAll();

  Comment findById(CommentId commentId) throws CommentNotFoundException;

  void create(Comment comment) throws CommentIdDuplicatedException;

  void update(Comment comment) throws CommentNotFoundException;

  void delete(CommentId commentId) throws CommentNotFoundException;
}
