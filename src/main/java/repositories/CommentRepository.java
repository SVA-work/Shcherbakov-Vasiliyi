package repositories;

import Exeptions.CommentIdDuplicatedException;
import Exeptions.CommentNotFoundException;
import entity.Comment;
import entity.CommentId;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class CommentRepository implements CommentRepositoryInterface {

  private final AtomicLong nextId = new AtomicLong(0);
  private final Map<Long, Comment> booksMap = new ConcurrentHashMap<>();

  @Override
  public CommentId generateId() {
    return new CommentId(nextId.incrementAndGet());
  }

  @Override
  public List<Comment> findAll() {
    return new ArrayList<>(booksMap.values());
  }

  @Override
  public Comment findById(CommentId commentId) throws CommentNotFoundException {
    Comment book = booksMap.get(commentId.id());
    if (book == null) {
      throw new CommentNotFoundException("Cannot find book by id=" + commentId.id());
    }
    return book;
  }

  @Override
  public synchronized void create(Comment comment) throws CommentIdDuplicatedException {
    if (booksMap.get(comment.getId().id()) != null) {
      throw new CommentIdDuplicatedException("Comment with the given id already exists: " + comment.getId().id());
    }
    booksMap.put(comment.getId().id(), comment);
  }

  @Override
  public synchronized void update(Comment comment) throws CommentNotFoundException {
    if (booksMap.get(comment.getId().id()) == null) {
      throw new CommentNotFoundException("Cannot find comment by id=" + comment.getId().id());
    }
    booksMap.put(comment.getId().id(), comment);
  }

  @Override
  public void delete(CommentId commentId) throws CommentNotFoundException {
    if (booksMap.remove(commentId.id()) == null) {
      throw new CommentNotFoundException("Cannot find comment by id=" + commentId.id());
    }
  }
}
