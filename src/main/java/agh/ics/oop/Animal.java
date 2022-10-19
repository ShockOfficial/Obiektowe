package agh.ics.oop;

public class Animal {
    private  MapDirection orientation;
    private  Vector2d position;

    public Animal() {
        this.orientation = MapDirection.NORTH;
        this.position = new Vector2d(2, 2);
    }

    @Override
    public String toString() {
        return
                "orientation=" + orientation +
                        ", position=" + position;
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

            if (tmpPosition.x >= 0 && tmpPosition.x <= 4 && tmpPosition.y >=0 && tmpPosition.y <= 4){
                this.position = tmpPosition;
            }

        }
    }
}