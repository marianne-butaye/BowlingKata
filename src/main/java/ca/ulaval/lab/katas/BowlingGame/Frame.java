package ca.ulaval.lab.katas.BowlingGame;

import java.util.ArrayList;
import java.util.List;

public class Frame {

  private int numberOfRolls = 2;
  private static final int NUMBER_OF_PINS = 10;
  private static final int FIRST_ROLL = 0;
  private static final int THREE_ROLLS = 3;
  private static final int SECOND_ROLL = 1;
  private static final int THIRD_ROLL = 2;
  private int rollNumber = 0;
  private List<Roll> rolls;
  private int bonusPoints = 0;
  private boolean isSpare = false;
  private boolean isStrike = false;
  private boolean isFrameFinished = false;

  public Frame() {
    super();
    rolls = new ArrayList<Roll>();
    for (int i = 0; i < numberOfRolls; i++) {
      rolls.add(new Roll());
    }
  }

  public List<Roll> getRolls() {
    return rolls;
  }

  public boolean isSpare() {
    return isSpare;
  }

  public void setSpare(boolean isSpare) {
    this.isSpare = isSpare;
  }

  public boolean isStrike() {
    return isStrike;
  }

  public void setStrike(boolean isStrike) {
    this.isStrike = isStrike;
  }

  public boolean isFrameFinished() {
    return isFrameFinished;
  }

  public void setFrameFinished(boolean isFrameFinished) {
    this.isFrameFinished = isFrameFinished;
  }

  public int scoreWithBonusPoints() {
    int sum = 0;
    for (Roll roll : rolls) {
      sum += roll.score();
    }
    sum += bonusPoints;
    return sum;
  }

  private int scoreWithoutBonusPoints() {
    int sum = 0;
    for (Roll roll : rolls) {
      sum += roll.score();
    }
    return sum;
  }

  public int getRollNumber() {
    return rollNumber;
  }

  public void roll(int numberOfPins) {
    Roll currentRoll = rolls.get(rollNumber);
    currentRoll.roll(numberOfPins);

    if (numberOfRolls == THREE_ROLLS) {
      if (currentRoll.score() == NUMBER_OF_PINS) {
        setStrike(true);
      }

      Roll previousRoll = rolls.get(rollNumber - 1);
      if (currentRoll.score() + previousRoll.score() == NUMBER_OF_PINS) {
        setSpare(true);
      }

      if (rollNumber == THIRD_ROLL) {
        setFrameFinished(true);
      }
    } else if (rollNumber == FIRST_ROLL) {
      if (currentRoll.score() == NUMBER_OF_PINS) {
        setStrike(true);
        setFrameFinished(true);
      }
    } else if (rollNumber != FIRST_ROLL) {
      Roll previousRoll = rolls.get(rollNumber - 1);
      if (currentRoll.score() + previousRoll.score() == NUMBER_OF_PINS) {
        setSpare(true);
      }
      if (rollNumber == SECOND_ROLL) {
        setFrameFinished(true);
      }
    }

    rollNumber++;
  }

  public void addSpareBonusPoints(Frame nextFrame) {
    bonusPoints += nextFrame.getRolls().get(FIRST_ROLL).score();
  }

  public void addStrikeBonusPoints(Frame nextFrame) {
    bonusPoints += nextFrame.scoreWithoutBonusPoints();
  }

  public void setNumberOfRollsTo3() {
    if (numberOfRolls != THREE_ROLLS) {
      setFrameFinished(false);
      numberOfRolls = THREE_ROLLS;
      rolls.add(new Roll());
    }
  }

}
