package org.example.Animals;

public class Owl extends Animals {
  public Owl(String move, String name) {
    super(move, name);
  }

  public void eat(Meet food) {
    System.out.println(name + " ест " + food.name);
  }
}
