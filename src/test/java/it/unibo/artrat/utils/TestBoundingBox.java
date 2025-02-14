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
                final double size = 3;
                Point center = new Point(size / 2, size / 2);
                assertEquals(size, box.getWidth());
                assertEquals(size, box.getHeight());
                assertEquals(center, box.getCenter());

                // moving the bounding box
                center = new Point(2, 2);
                final Point topLeft = new Point(0.5, 0.5);
                final Point bottomRight = new Point(3.5, 3.5);
                box.setCenter(center);
                assertEquals(size, box.getHeight());
                assertEquals(size, box.getHeight());
                assertEquals(topLeft, box.getTopLeft());
                assertEquals(bottomRight, box.getBottomRight());

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
