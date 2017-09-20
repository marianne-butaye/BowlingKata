package ca.ulaval.lab.katas.BowlingGame;

import java.util.ArrayList;
import java.util.List;

public class Game {

  private List<Frame> frames;
  private int frameNumber = 0;
  private static final int NUMBER_OF_FRAMES = 10;
  private static final int LAST_FRAME = NUMBER_OF_FRAMES - 1;

  public Game() {
    super();
    frames = new ArrayList<Frame>();
    for (int i = 0; i < NUMBER_OF_FRAMES; i++) {
      frames.add(new Frame());
    }
  }

  public boolean isFinished() {
    return frames.get(LAST_FRAME).isFrameFinished();
  }

  public int score() {
    int sum = 0;
    for (Frame frame : frames) {
      sum += frame.scoreWithBonusPoints();
    }
    return sum;
  }

  public void roll(int numberOfPins) {
    Frame currentFrame = frames.get(frameNumber);
    currentFrame.roll(numberOfPins);

    if (frameNumber > 0) {
      Frame previousFrame = frames.get(frameNumber - 1);
      if (!currentFrame.isFrameFinished() && previousFrame.isSpare()) {
        previousFrame.addSpareBonusPoints(currentFrame);
      } else if (currentFrame.isFrameFinished() && previousFrame.isStrike()) {
        previousFrame.addStrikeBonusPoints(currentFrame);
      }
    }

    if (frameNumber == LAST_FRAME && currentFrame.isSpare()) {
      currentFrame.setNumberOfRollsTo3();
    } else if (frameNumber == LAST_FRAME && currentFrame.isStrike()) {
      currentFrame.setNumberOfRollsTo3();
    }

    if (currentFrame.isFrameFinished()) {
      frameNumber++;
    }
  }

}
