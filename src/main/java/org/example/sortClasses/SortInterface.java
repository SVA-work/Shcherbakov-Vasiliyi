package org.example.sortClasses;

import javax.naming.SizeLimitExceededException;
import java.util.List;

public interface SortInterface {
  final int MAX_SIZE = 0;

  void sort(List<Integer> listToSort) throws SizeLimitExceededException;
}
