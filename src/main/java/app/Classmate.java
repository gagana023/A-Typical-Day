package app;

/**
 * Represents a classmate NPC in the game.
 *
 * <p>This class currently uses the same behavior as a regular NPC, but it may be expanded in the
 * future to include classmate-specific dialogue, tasks, or interactions.
 */
public class Classmate extends NPC {
  /**
   * Creates a classmate with the given sprite image.
   *
   * @param spritePath the path to the sprite image used for this classmate
   */
  public Classmate(String spritePath) {
    super(spritePath, 40, 100);
    // TODO Auto-generated constructor stub
  }
}
