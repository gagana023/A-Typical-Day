package app;

/** Stores and updates the player's social battery and social standing. */
public class PlayerStats {

  private double health = 1.0;
  private double social = 1.0;

  /**
   * Updates player stats based on the chosen dialogue option.
   *
   * @param optionType the selected option number
   */
  public void changeStats(int optionType) {
    switch (optionType) {
      case 1:
        health -= 0.1;
        social += 0.1;
        break;

      case 2:
        health += 0.1;
        social -= 0.1;
        break;

      case 3:
        health -= 0.1;
        social -= 0.1;
        break;
    }

    health = clamp(health);
    social = clamp(social);
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

  private double clamp(double value) {
    return Math.max(0, Math.min(1, value));
  }
}
