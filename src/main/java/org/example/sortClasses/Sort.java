package org.example.sortClasses;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.List;

public class Sort {

  private final BubbleSort bubbleSort;
  private final MergeSort mergeSort;

  public Sort() {
    bubbleSort = new BubbleSort();
    mergeSort = new MergeSort();
  }

  public List<Integer> sort(TypeOfSort typeOfSort, List<Integer> listToSort) throws SizeLimitExceededException {
    List<Integer> copyOfListToSort = doCopyList(listToSort);
    switch (typeOfSort) {
      case BUBBLESORT -> bubbleSort.sort(copyOfListToSort);
      case MERGESORT -> mergeSort.sort(copyOfListToSort);
    }
    return copyOfListToSort;
  }

  private List<Integer> doCopyList(List<Integer> listToSort) {
    return new ArrayList<>(List.copyOf(listToSort));
  }
}
