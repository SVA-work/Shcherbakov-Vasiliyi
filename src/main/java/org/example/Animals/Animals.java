package org.example.Animals;

public abstract class Animals {
  protected String name;
  protected String move;

  public Animals(String move, String name) {
    this.move = move;
    this.name = name;
  }

  public void eat() {
  }

  public void move() {
    switch (this.move) {
      case "land":
        System.out.println(this.name + " идет");
        break;
      case "flying":
        System.out.println(this.name + " летит");
        break;
      case "waterfowl":
        System.out.println(this.name + " плывет");
    }

  }
}
