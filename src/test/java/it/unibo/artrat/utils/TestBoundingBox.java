package it.unibo.artrat.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.artrat.utils.impl.BoundingBoxImpl;
import it.unibo.artrat.utils.impl.Point;

/**
 * BoundingBox class testing.
 */
class TestBoundingBox {
        private BoundingBoxImpl box;

        /**
         * BoundingBox initialization.
         */
        @BeforeEach
        void init() {
                box = new BoundingBoxImpl(new Point(0, 0), new Point(3, 3));
        }

        /**
         * Test all contructors and accuracy of the calculations.
         */
        @Test
        void checkCreation() {
                assertEquals(box.getBottomRight().getX() - box.getTopLeft().getX(), box.getWidth());
                assertEquals(box.getBottomRight().getY() - box.getTopLeft().getY(), box.getHeight());
                assertEquals(new Point(box.getTopLeft().getX() + box.getWidth() / 2,
                                box.getTopLeft().getY() + box.getHeight() / 2), box.getCenter());

                final double width = 19;
                final double height = 31;
                box = new BoundingBoxImpl(new Point(Math.random() * 10, Math.random() * 10), width,
                                height);
                // Corners check
                assertEquals(
                                new Point(box.getCenter().getX() - box.getWidth() / 2,
                                                box.getCenter().getY() - box.getHeight() / 2),
                                box.getTopLeft());
                assertEquals(new Point(box.getCenter().getX() + width / 2,
                                box.getCenter().getY() + height / 2), box.getBottomRight());

                box.setCenter(new Point(Math.random() * 10, Math.random() * 10));
                assertEquals(width, box.getWidth());
                assertEquals(height, box.getHeight());
                // Corners check
                assertEquals(new Point(box.getCenter().getX() - width / 2,
                                box.getCenter().getY() - height / 2), box.getTopLeft());
                assertEquals(new Point(box.getCenter().getX() + width / 2,
                                box.getCenter().getY() + height / 2), box.getBottomRight());

        }

        /**
         * Testing all type of collisions.
         */
        @Test
        void checkCollision() {
                BoundingBoxImpl rect = new BoundingBoxImpl(new Point(1, 1), new Point(3, 3)); // Nested rectangle
                assertTrue(box.isColliding(rect));
                rect = new BoundingBoxImpl(new Point(2, 2), new Point(4, 4)); // Colliding rectangle
                assertTrue(box.isColliding(rect));
                rect = new BoundingBoxImpl(new Point(3, 0), new Point(4, 4)); // Colliding with right border
                assertTrue(box.isColliding(rect));
                rect = new BoundingBoxImpl(new Point(2, 3), new Point(4, 4)); // Colliding with bottom border
                assertTrue(box.isColliding(rect));
                rect = new BoundingBoxImpl(new Point(3, 3), new Point(4, 4)); // Colliding on corner
                assertTrue(box.isColliding(rect));
                rect = new BoundingBoxImpl(new Point(4, 0), new Point(4, 4)); // Non-Colliding rectangle
                assertFalse(box.isColliding(rect));
                rect = new BoundingBoxImpl(new Point(0, 4), new Point(4, 4)); // Non-Colliding rectangle
                assertFalse(box.isColliding(rect));
        }
}
