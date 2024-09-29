package org.example;

import org.example.Animals.*;

public class Main {
  public Main() {
  }

  public static void main(String[] args) {
    Camel camel = new Camel("land", "Верблюжатина");
    Horse horse = new Horse("land", "Коник");
    Owl owl = new Owl("flying", "Орленок");
    Tiger tiger = new Tiger("land", "Тигренок");
    Dolphin dolphin = new Dolphin("waterfowl", "Дельфинчик");
    camel.move();
    horse.move();
    owl.move();
    dolphin.move();
    tiger.move();
    System.out.println();
    Grass grass = new Grass("укроп");
    Fish fish = new Fish("карась");
    Beef beef = new Beef("стейк");
    Meet meet = new Meet("еж");
    camel.eat(grass);
    horse.eat(grass);
    owl.eat(meet);
    dolphin.eat(fish);
    tiger.eat(beef);
  }
}
