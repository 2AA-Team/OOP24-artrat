package it.unibo.artrat.model.impl.world;

public enum RoomSymbols {
    EMPTY_SPACE(' '),
    WALL('#'),
    ENEMY('E'),
    VALUE('V');

    private final char symbol;

    RoomSymbols(char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static RoomSymbols fromChar(char c) {
        for (RoomSymbols rs : values()) {
            if (rs.symbol == c) {
                return rs;
            }
        }
        throw new IllegalArgumentException("Char does not exist: " + c);
    }
}
