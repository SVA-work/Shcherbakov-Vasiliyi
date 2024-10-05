package org.example.Animals;

public class Dolphin extends Animals {
  public Dolphin(String move, String name) {
    super(move, name);
  }

  public void eat(Fish food) {
    System.out.println(name + " съел " + food.name);
  }
}
