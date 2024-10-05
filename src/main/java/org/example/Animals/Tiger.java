package org.example.Animals;

public class Tiger extends Animals {
  public Tiger(Move move, String name) {
    super(move, name);
  }

  public void eat(Beef food) {
    System.out.println(name + " ест " + food.name);
  }
}
