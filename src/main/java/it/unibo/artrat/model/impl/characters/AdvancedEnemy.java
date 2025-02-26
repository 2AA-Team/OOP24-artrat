package it.unibo.artrat.model.impl.characters;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.model.impl.characters.characters.AbstractEnemy;
import it.unibo.artrat.model.impl.characters.characters.Player;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * An enemy that chases the player when too close and dosen't move when
 * the player is not in its field of view .
 * 
 * @author Samuele Trapani
 */
public final class AdvancedEnemy extends AbstractEnemy {

    /**
     * constructor for advanced enemy.
     * 
     * @param center center of the hitbox
     * @param width  width of the hitbox
     * @param height height of the hitbox
     */
    public AdvancedEnemy(final Point center, final double width, final double height) {
        super(center, width, height, new HashSet<>());
    }

    /**
     * Advanced enemy constructor.
     * 
     * @param topLeft     top left corner
     * @param bottomRight bottom right corner
     * @param v           vector
     */
    public AdvancedEnemy(final Point topLeft, final Point bottomRight, final Set<Vector2d> v) {
        super(topLeft, bottomRight, v);
    }

    /**
     * Advanced enemy constructor.
     * 
     * @param topLeft     top left corner
     * @param bottomRight bottom right corner
     */
    public AdvancedEnemy(final Point topLeft, final Point bottomRight) {
        super(topLeft, bottomRight);
    }

    @Override
    public void follow(final Player p) {
        final Vector2d dir = Set.of(Directions.values()).stream()
                .map(Directions::vector)
                .min((a, b) -> {
                    final Point playerPos = p.getPosition();
                    return Double.compare(
                            playerPos.getManhattanDistance(this.getPosition().sum(a)),
                            playerPos.getManhattanDistance(this.getPosition().sum(b)));
                })
                .orElse(new Vector2d());

        this.setSpeed(dir);
    }

    @Override
    public void move() {
        this.setSpeed(new Vector2d());
    }

}
