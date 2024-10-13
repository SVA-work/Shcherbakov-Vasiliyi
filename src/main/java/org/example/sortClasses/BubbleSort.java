package org.example.sortClasses;

import javax.naming.SizeLimitExceededException;
import java.util.List;

public class BubbleSort implements SortInterface {
  final int MAX_SIZE = 100;
  @Override
  public void sort(List<Integer> listToSort) throws SizeLimitExceededException {
    if (listToSort.size() > MAX_SIZE) {
      throw new SizeLimitExceededException("Для данного алгоритма максимальная длинна списка" + MAX_SIZE);
    }
    for (int i = 0; i < listToSort.size(); i++) {
      for (int j = i + 1; j < listToSort.size(); j++) {
        if (listToSort.get(i) > listToSort.get(j)) {
          int temp = listToSort.get(i);
          listToSort.set(i, listToSort.get(j));
          listToSort.set(j, temp);
        }
      }
    }
  }
}
