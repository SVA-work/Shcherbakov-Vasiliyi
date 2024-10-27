package org.example.sortClasses;

import org.junit.jupiter.api.Test;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {
  @Test
  public void testMergeSortForSort() throws SizeLimitExceededException {
    MergeSort mergeSort = new MergeSort();
    List<Integer> testList = new ArrayList<>();
    testList.add(1);
    testList.add(7);
    testList.add(2);
    testList.add(3);
    testList.add(1);


    List<Integer> sortedList = new ArrayList<>(List.copyOf(testList));
    mergeSort.sort(sortedList);

    Collections.sort(testList);

    assertTrue(isListsEquals(testList, sortedList));
  }

  boolean isListsEquals(List<Integer> firstList, List<Integer> secondList) {
    if (firstList.size() != secondList.size()) {
      return false;
    } else {
      for (int i = 0; i < firstList.size(); ++i) {
        if (!(firstList.get(i)).equals(secondList.get(i))) {
          return false;
        }
      }
      return true;
    }
  }
}