package org.example.sortClasses;

public enum TypeOfSort {
  MERGESORT("mergeSort"), BUBBLESORT("bubbleSort");

  private final String typeOfSort;
  TypeOfSort(String typeOfSort) {
    this.typeOfSort = typeOfSort;
  }

  public String getTypeOfSort() {
    return typeOfSort;
  }
}
