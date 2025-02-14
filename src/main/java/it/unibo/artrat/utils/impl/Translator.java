package it.unibo.artrat.utils.impl;

import java.util.Set;

import it.unibo.artrat.model.api.GameObject;

/**
 * interface to translate some object into others.
 */
public interface Translator {

    /**
     * translate a matrix of char into a set of game object.
     * 
     * @param matrix a matrix of char (room rapresentation)
     * @return set of gameobject equivalent of the matrix of char
     */
    Set<GameObject> translate(char[][] matrix);
}
