package agh.ics.oop;

public enum MapDirection {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    @Override
    public String toString() {
        if (this == EAST)
            return "Wschód";
        if (this == WEST)
            return "Zachód";
        if (this == NORTH)
            return "Północ";
        return "Południe";
    }

    public MapDirection next() {
        MapDirection[] mapValues = values();
        return mapValues[(this.ordinal() + 1) % mapValues.length];
    }

    public MapDirection previous(){
        MapDirection[] mapValues = values();
        return mapValues[(4 + this.ordinal() - 1) % mapValues.length];
    }

    public Vector2d toUnitVector(){
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case WEST -> new Vector2d(-1, 0);
            case SOUTH -> new Vector2d(0, -1);
            default -> new Vector2d(1, 0);
        };
    }
}
