package org.example.Animals;

public class Camel extends Animals {
  public Camel(String move, String name) {
    super(move, name);
  }

  public void eat(Grass food) {
    System.out.println(name + " ест " + food.name);
  }
}
