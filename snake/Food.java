package snake;

import java.util.Random;

public class Food {

    private final Random random = new Random();
    private Position position;

    public Food(int screenHeight, int screenWidth, Snake snake) {
        respawn(screenHeight, screenWidth, snake);
    }

    public Position getPosition() {
        return position;
    }

    public void respawn(int screenHeight, int screenWidth, Snake snake) {
        while (true) {
            int row = random.nextInt(screenHeight - 2) + 1;
            int col = random.nextInt(screenWidth - 2) + 1;
            Position candidate = new Position(row, col);
            if (!snake.occupies(candidate)) {
                this.position = candidate;
                return;
            }
        }
    }
}
