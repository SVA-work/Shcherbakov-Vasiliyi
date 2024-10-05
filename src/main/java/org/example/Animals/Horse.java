package org.example.Animals;

public class Horse extends Animals {

  public Horse(Move move, String name) {
    super(move, name);
  }

  public void eat(Grass food) {
    System.out.println(name + " ест " + food.name);
  }
}
