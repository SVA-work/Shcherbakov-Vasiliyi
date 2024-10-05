package org.example.CustomArrayList;

/**
 * CustomArrayList is a clone of array list
 * @param <A> - the type of elements in arrayList
 */
public class CustomArrayList<A> implements ArrayMethods<A>{
  /**
   * array - structure where will be all elements
   * count_of_elements - field where will be info about number of elements in array at the moment
   * capacity - field where will be info about max number of elements in array at the moment
   */
  private Object[] array;
  private int count_of_elements;
  private int capacity;

  /**
   * init array, set first capacity, set count_of_elements
   */
  public CustomArrayList() {
    capacity = 16;
    count_of_elements = 0;
    array = new Object[capacity];
  }

  /**
   * increasing a capacity of arrayList
   */
  private void relocate() {
    capacity *= 2;
    Object[] bigger_array = new Object[count_of_elements];
    System.arraycopy(array, 0, bigger_array, 0, count_of_elements);
    array = bigger_array.clone();
  }

  /**
   * adding a new element in arrayList
   *
   * @param element - a new element in array. It can't be null
   */
  @Override
  public void add(A element) {
    if (element == null) {
      throw new NullPointerException("element can't be null");
    }
    if (count_of_elements == capacity) {
      relocate();
    }
    array[count_of_elements] = element;
    count_of_elements++;
  }

  /**
   * removing an element from arrayList
   *
   * @param index - an index of removing element
   */
  @Override
  public void remove(int index) {
    if (index < 0 || index >= count_of_elements)
      throw new IndexOutOfBoundsException("No element with index " + index);
    System.arraycopy(array, index + 1, array, index, count_of_elements - index - 1);
    count_of_elements--;
  }

  /**
   * getting an element from arrayList
   *
   * @param index - an index of getting element
   * @return an element with index "index"
   */
  @Override
  public A get(int index) {
    if (index < 0 || index >= count_of_elements)
      throw new IndexOutOfBoundsException("No element with index " + index);
    return (A) array[index];
  }
}
