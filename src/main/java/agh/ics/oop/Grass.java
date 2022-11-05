package agh.ics.oop;

import java.util.Objects;

public class Grass {
    private final Vector2d position;
    public Grass(Vector2d vector) {
        this.position = vector;
    }

    public Vector2d getPosition() {
        return new Vector2d(this.position.x, this.position.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return position.equals(grass.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString(){
        return "*";
    }
}
