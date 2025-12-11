package snake;


public class Game {

    private static final int INITIAL_SNAKE_LENGTH = 3;
    private static final int TICK_DELAY_MS = 120;

    private Snake snake;
    private Food food;
    private Score score;
    private Screen screen;
    private boolean gameOver = false;

    public Game() {
        reset();
    }

    private void reset() {
        Position start = new Position(Screen.HEIGHT / 2, Screen.WIDTH / 2);
        snake  = new Snake(start, INITIAL_SNAKE_LENGTH);
        food   = new Food(Screen.HEIGHT, Screen.WIDTH, snake);
        score  = new Score();
        screen = new Screen();
        gameOver = false;
    }

    public void start() throws Exception {
        while (!gameOver) {
            handleInput();
            update();
            screen.render(snake, food, score);
            Thread.sleep(TICK_DELAY_MS);
        }

        System.out.println("GAME OVER - SCORE = " + score.getValue());
    }

    private void handleInput() throws java.io.IOException {
        if (System.in.available() > 0) {
            char c = (char) System.in.read();
            switch (c) {
                case 'w' -> snake.changeDirection(Direction.UP);
                case 's' -> snake.changeDirection(Direction.DOWN);
                case 'a' -> snake.changeDirection(Direction.LEFT);
                case 'd' -> snake.changeDirection(Direction.RIGHT);
                default -> {
                }
            }
        }
    }

    private void update() {
        Position nextHead = snake.nextHeadPosition();

        if (isOutsideBounds(nextHead)) {
            snake.die();
            gameOver = true;
            return;
        }
        if (snake.occupies(nextHead)) {
            snake.die();
            gameOver = true;
            return;
        }

        boolean eatFood = nextHead.equals(food.getPosition());

        if (eatFood) {
            score.increment();
            snake.move(true);
            food.respawn(Screen.HEIGHT, Screen.WIDTH, snake);
        } else {
            snake.move(false);
        }
    }

    private boolean isOutsideBounds(Position position) {
        return position.row() <= 0
                || position.row() >= Screen.HEIGHT - 1
                || position.col() <= 0
                || position.col() >= Screen.WIDTH - 1;
    }

    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.start();
    }
}
