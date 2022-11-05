package agh.ics.oop;

public class Animal {
    private  MapDirection orientation;
    private  Vector2d position;
    private final IWorldMap map;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH; // Because without it we will have this unspecified (null)
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return switch (this.orientation){
            case NORTH -> "N";
            case SOUTH -> "S";
            case EAST -> "E";
            case WEST -> "W";
        };
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    void move(MoveDirection direction){
        if (direction == MoveDirection.LEFT  ){
            this.orientation = this.orientation.previous();
        } else if (direction == MoveDirection.RIGHT) {
            this.orientation = this.orientation.next();
        } else  {
            Vector2d unitVector = direction == MoveDirection.BACKWARD ? this.orientation.toUnitVector().opposite() :  this.orientation.toUnitVector();
            Vector2d tmpPosition = this.position.add(unitVector);
                if (this.map.canMoveTo(tmpPosition)){
                if (this.map instanceof GrassField && this.map.objectAt(tmpPosition) instanceof Grass){
                    ((GrassField) this.map).eatGrass(tmpPosition);

                }
                    this.position = tmpPosition;

                }

        }
    }
}