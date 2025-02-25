package it.unibo.artrat.model.impl.world;

/**
 * enum that describe room objects.
 * 
 * @author Matteo Tonelli
 */
public enum RoomSymbols {
    /**
     * the room wall.
     */
    WALL('#'),
    /**
     * base enemy.
     */
    ENEMY('E'),
    /**
     * base valuable object.
     */
    VALUE('V'),
    /**
     * exit.
     */
    EXIT('X'),
    /**
     * player.
     */
    PLAYER('P');

    private final char symbol;

    /**
     * constructor for set the symbol of the object.
     * 
     * @param symbol symbol
     */
    RoomSymbols(final char symbol) {
        this.symbol = symbol;
    }

    /**
     * get the symbol of a object.
     * 
     * @return the symbol
     */
    public char getSymbol() {
        return symbol;
    }
}
