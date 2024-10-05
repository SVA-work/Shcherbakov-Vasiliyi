package org.example.Animals;

public enum Move {
  LAND("land"), SWIMMING("swimming"), FLYING("flying");

  private final String move;

  Move(String move) {
    this.move = move;
  }

  public String getMove() {
    return move;
  }
}
