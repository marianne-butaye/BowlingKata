package ca.ulaval.lab.katas.BowlingGame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameTest {

  private static final int ZERO_PINS = 0;
  private static final int THREE_PINS = 3;
  private static final int FOUR_PINS = 4;
  private static final int SEVEN_PINS = 7;
  private static final int TEN_PINS = 10;
  private Game game;

  @Before
  public void setupGame() {
    game = new Game();
  }

  @Test
  public void gameShouldHave10Frames() {
    assertEquals(10, game.getNumberOfFrames());
  }

  @Test
  public void scoreShouldBeZeroAtGameStart() {
    assertEquals(0, game.score());
  }

  @Test
  public void whenRoll_scoreShouldBeCorrect() {
    game.roll(THREE_PINS);
    game.roll(FOUR_PINS);
    assertEquals(THREE_PINS + FOUR_PINS, game.score());
  }

  @Test
  public void whenSpare_scoreShouldBeCorrect() {
    game.roll(THREE_PINS);
    game.roll(SEVEN_PINS);
    game.roll(FOUR_PINS);
    assertEquals(THREE_PINS + SEVEN_PINS + FOUR_PINS * 2, game.score());
  }

  @Test
  public void whenStrike_scoreShouldBeCorrect() {
    game.roll(TEN_PINS);
    game.roll(THREE_PINS);
    game.roll(FOUR_PINS);
    assertEquals(TEN_PINS + THREE_PINS * 2 + FOUR_PINS * 2, game.score());
  }

  @Test
  public void when2Strikes_scoreShouldBeCorrect() {
    game.roll(TEN_PINS);
    game.roll(TEN_PINS);
    game.roll(THREE_PINS);
    game.roll(ZERO_PINS);
    assertEquals(TEN_PINS + TEN_PINS * 2 + THREE_PINS * 2, game.score());
  }

  @Test
  public void whenSpareInLastFrame_extraRollShouldBeAllowed() {
    game.roll(TEN_PINS); // 1STFRAME
    game.roll(TEN_PINS); // 2ND FRAME
    game.roll(THREE_PINS); // 3RD FRAME
    game.roll(ZERO_PINS);
    game.roll(FOUR_PINS);// 4TH FRAME
    game.roll(FOUR_PINS);
    game.roll(ZERO_PINS);// 5TH FRAME
    game.roll(FOUR_PINS);
    game.roll(SEVEN_PINS);// 6TH FRAME
    game.roll(THREE_PINS);
    game.roll(THREE_PINS);// 7TH FRAME
    game.roll(FOUR_PINS);
    game.roll(FOUR_PINS);// 8TH FRAME
    game.roll(FOUR_PINS);
    game.roll(TEN_PINS); // 9TH FRAME
    game.roll(SEVEN_PINS);// 10TH FRAME
    game.roll(THREE_PINS);
    assertFalse(game.isFinished());
  }

  @Test
  public void whenStrikeInLastFrame_noMoreThan3RollsAllowed() {
    game.roll(TEN_PINS); // 1ST FRAME
    game.roll(TEN_PINS); // 2ND FRAME
    game.roll(THREE_PINS); // 3RD FRAME
    game.roll(ZERO_PINS);
    game.roll(FOUR_PINS);// 4TH FRAME
    game.roll(FOUR_PINS);
    game.roll(ZERO_PINS);// 5TH FRAME
    game.roll(FOUR_PINS);
    game.roll(SEVEN_PINS);// 6TH FRAME
    game.roll(THREE_PINS);
    game.roll(THREE_PINS);// 7TH FRAME
    game.roll(FOUR_PINS);
    game.roll(FOUR_PINS);// 8TH FRAME
    game.roll(FOUR_PINS);
    game.roll(TEN_PINS); // 9TH FRAME
    game.roll(TEN_PINS);// 10TH FRAME
    game.roll(SEVEN_PINS);// EXTRA ROLL
    game.roll(THREE_PINS);// EXTRA ROLL
    assertTrue(game.isFinished());
  }

  @Test
  public void whenFinishedLastFrame_gameShouldEnd() {
    game.roll(TEN_PINS); // 1ST FRAME
    game.roll(TEN_PINS); // 2ND FRAME
    game.roll(THREE_PINS); // 3RD FRAME
    game.roll(ZERO_PINS);
    game.roll(FOUR_PINS);// 4TH FRAME
    game.roll(FOUR_PINS);
    game.roll(ZERO_PINS);// 5TH FRAME
    game.roll(FOUR_PINS);
    game.roll(SEVEN_PINS);// 6TH FRAME
    game.roll(THREE_PINS);
    game.roll(THREE_PINS);// 7TH FRAME
    game.roll(FOUR_PINS);
    game.roll(FOUR_PINS);// 8TH FRAME
    game.roll(FOUR_PINS);
    game.roll(TEN_PINS); // 9TH FRAME
    game.roll(ZERO_PINS);// 10TH FRAME
    game.roll(THREE_PINS);
    assertTrue(game.isFinished());
  }

}
