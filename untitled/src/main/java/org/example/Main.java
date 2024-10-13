package org.example;

import org.example.sortClasses.Sort;
import org.example.sortClasses.TypeOfSort;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
  public static void main(String[] args) throws SizeLimitExceededException {
    List<Integer> anyList = new ArrayList<>();
    Random rd = new Random();
    for (int i = 0; i < 10; i++) {
      anyList.add(rd.nextInt() % 100);
    }
    Sort sort = new Sort();
    anyList = sort.sort(TypeOfSort.BUBBLESORT, anyList);
    System.out.println(anyList);
    for (int i = 0; i < 10; i++) {
      anyList.add(rd.nextInt() % 100);
    }
    anyList = sort.sort(TypeOfSort.MERGESORT, anyList);
    System.out.println(anyList);
  }
}