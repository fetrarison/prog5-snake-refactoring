
package snake;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;

public class Snake {

    private final Deque<Position> body = new ArrayDeque<>();
    private Direction direction = Direction.RIGHT;
    private boolean alive = true;

    public Snake(Position start, int initialLength) {
        for (int i = 0; i < initialLength; i++) {
            body.addLast(new Position(start.row(), start.col() - i));
        }
    }

    public Position getHead() {
        return body.peekFirst();
    }

    public Direction getDirection() {
        return direction;
    }

    public void changeDirection(Direction newDirection) {
        if (!direction.isOpposite(newDirection)) {
            this.direction = newDirection;
        }
    }

    public Position nextHeadPosition() {
        return getHead().move(direction);
    }

    public void move(boolean grow) {
        Position newHead = nextHeadPosition();
        body.addFirst(newHead);
        if (!grow) {
            body.removeLast();
        }
    }

    public boolean occupies(Position position) {
        return body.contains(position);
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public Collection<Position> getBody() {
        return Collections.unmodifiableCollection(body);
    }
}
