package ca.ulaval.lab.katas.BowlingGame;

public class Roll {

  private int score;

  public Roll() {
    super();
    score = 0;
  }

  public int score() {
    return score;
  }

  public void roll(int numberOfPins) {
    score += numberOfPins;
  }

}
