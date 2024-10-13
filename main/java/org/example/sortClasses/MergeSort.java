package org.example.sortClasses;

import javax.naming.SizeLimitExceededException;
import java.util.Collections;
import java.util.List;

public class MergeSort implements SortInterface {
  private int MAX_SIZE = 100;

  @Override
  public void sort(List<Integer> listToSort) throws SizeLimitExceededException {
    if (listToSort.size() > MAX_SIZE) {
      throw new SizeLimitExceededException("Для данного алгоритма максимальная длинна списка" + MAX_SIZE);
    }
    Collections.sort(listToSort);
  }
}
