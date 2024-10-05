package org.example.CustomArrayList;

/**
 * ArrayMethods is an interface with based methods of ArrayLists
 * @param <A> - the type of elements in arrayList
 */
public interface ArrayMethods<A> {

  /**
   *  adding an element to arrayList
   * @param element - element to add
   */
  void add(A element);

  /**
   * removing an element from arrayList
   * @param index - index of removing element
   */
  void remove(int index);

  /**
   *  getting an element from arrayList
   * @param index - index of getting element
   * @return an element with index "index"
   */
  A get(int index);
}
