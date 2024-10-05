package org.example;

import org.example.Animals.Move;
import org.example.Animals.Tiger;

public class Main {
  public static void main(String[] args) {
    Tiger tiger = new Tiger(Move.LAND, "tig");
    tiger.move();
  }
}
