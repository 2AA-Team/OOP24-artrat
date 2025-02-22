package it.unibo.artrat.model.impl.characters;

import java.util.HashSet;
import java.util.Set;

import it.unibo.artrat.model.api.characters.AbstractEnemy;
import it.unibo.artrat.model.api.characters.Player;
import it.unibo.artrat.utils.api.Directions;
import it.unibo.artrat.utils.impl.Point;
import it.unibo.artrat.utils.impl.Vector2d;

/**
 * New hypothetical enemies.
 * 
 * @author Samuele Trapani
 */
public final class AdvancedEnemy extends AbstractEnemy {

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
    public void follow(Player p) {
        final Vector2d dir = Set.of(Directions.values()).stream()
                .map(x -> x.vector())
                .min((a, b) -> {
                    final Point playerPos = p.getPosition();
                    return Double.compare(
                            playerPos.getEuclideanDistance(this.getPosition().sum(a)),
                            playerPos.getEuclideanDistance(this.getPosition().sum(b)));
                })
                .orElse(new Vector2d());

        this.setSpeed(dir);
    }

    @Override
    public void move() {
        this.setSpeed(new Vector2d());
    }

}
