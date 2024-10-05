package org.example;

import org.example.CustomArrayList.CustomArrayList;

public class Main {
  public static void main(String[] args) {
    CustomArrayList<Integer> array = new CustomArrayList<>();
    array.add(1);
    array.add(100);
    System.out.println(array.get(1));
    array.remove(1);
    array.get(10);

  }
}
