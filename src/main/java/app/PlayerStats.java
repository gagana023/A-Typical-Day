package app;

/**
 * Stores and updates the player's social battery and social standing.
 *
 * <p>Both stats are stored as values from 0.0 to 1.0. Dialogue choices can increase or decrease
 * these values, and the values are clamped so they stay within the valid range.
 */
public class PlayerStats {
  /** The player's social battery value. */
  private double health = 1.0;

  /** The player's social standing value. */
  private double social = 1.0;

  /**
   * Updates player stats based on the chosen dialogue option.
   *
   * <p>Option 1 decreases social battery and increases social standing. Option 2 increases social
   * battery and decreases social standing. Option 3 decreases both values.
   *
   * @param optionType the selected dialogue option number
   * @return true if the updated stats cause a game-over condition, false otherwise
   */
  public boolean changeStats(int optionType) {
    double amount = 0.15;

    switch (optionType) {
      case 1:
        // Most socially acceptable: battery down, standing up
        health -= amount;
        social += amount;
        break;

      case 2:
        // Second option: battery up, standing down
        health += amount;
        social -= amount;
        break;

      case 3:
        // Worst option: both down
        health -= amount;
        social -= amount;
        break;

      default:
        break;
    }

    health = clamp(health);
    social = clamp(social);

    if ((health < 0.5 && health > 0) || (social < 0.5 && social > 0)) {
      // panic class
      // method that makes visuals dark and shaky
      if ((health < 0.5 && health > 0) || (social < 0.5 && social > 0)) {
        if (HelloWorld.currentPanic == null) {
          HelloWorld.currentPanic = new Panic();
          HelloWorld.currentPanic.setPrimaryStage(HelloWorld.getStage());
          HelloWorld.currentPanic.shakeStage();
          HelloWorld.currentPanic.darkenStage();
        }
      }
    }

    return isGameOver();
  }

  public boolean bullyHit() {
    health -= 0.15;
    social -= 0.15;

    health = clamp(health);
    social = clamp(social);

    HelloWorld.updateStatsBars();

    if ((health < 0.5 && health > 0) || (social < 0.5 && social > 0)) {
      if (HelloWorld.currentPanic == null) {
        HelloWorld.currentPanic = new Panic();
        HelloWorld.currentPanic.setPrimaryStage(HelloWorld.getStage());
        HelloWorld.currentPanic.shakeStage();
        HelloWorld.currentPanic.darkenStage();
      }
    }

    return isGameOver();
  }

  /**
   * Returns the player's social battery.
   *
   * @return the social battery value from 0.0 to 1.0
   */
  public double getHealth() {
    return health;
  }

  /**
   * Returns the player's social standing.
   *
   * @return the social standing value from 0.0 to 1.0
   */
  public double getSocial() {
    return social;
  }

  /**
   * Keeps a stat value between 0.0 and 1.0.
   *
   * @param value the stat value to limit
   * @return the value limited to the range from 0.0 to 1.0
   */
  private double clamp(double value) {
    return Math.max(0, Math.min(1, value));
  }

  /**
   * Checks whether the player has reached a game-over condition.
   *
   * @return true if social battery or social standing is 0.0 or lower, false otherwise
   */
  public boolean isGameOver() {
    return health <= 0 || social <= 0;
  }
}
