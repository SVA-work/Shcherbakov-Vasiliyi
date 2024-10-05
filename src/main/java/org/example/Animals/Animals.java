package org.example.Animals;

import org.example.Main;

public abstract class Animals {
  protected String name;
  protected Move move;

  public Animals(Move move, String name) {
    this.move = move;
    this.name = name;
  }

  public void eat() {
  }

  public void move() {
    switch (this.move) {
      case LAND -> System.out.println(this.name + " идет");
      case FLYING -> System.out.println(this.name + " летит");
      case SWIMMING -> System.out.println(this.name + " плывет");
    }

  }
}
