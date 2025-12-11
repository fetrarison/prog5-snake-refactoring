package snake;

public class Screen {

    public static final int HEIGHT = 20;
    public static final int WIDTH  = 40;

    public static final char BORDER_CHAR = 'X';
    public static final char SNAKE_CHAR  = '#';
    public static final char FOOD_CHAR   = '*';
    public static final char EMPTY_CHAR  = ' ';

    public static final String CLEAR_SCREEN = "\033[H\033[2J";

    public void render(Snake snake, Food food, Score score) {
        StringBuilder sb = new StringBuilder();

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                char ch;
                if (isBorder(row, col)) {
                    ch = BORDER_CHAR;
                } else {
                    Position p = new Position(row, col);
                    if (p.equals(food.getPosition())) {
                        ch = FOOD_CHAR;
                    } else if (snake.occupies(p)) {
                        ch = SNAKE_CHAR;
                    } else {
                        ch = EMPTY_CHAR;
                    }
                }
                sb.append(ch);
            }
            sb.append('\n');
        }

        System.out.print(CLEAR_SCREEN);
        System.out.flush();
        System.out.println(sb);
        System.out.println("Score: " + score.getValue());
    }

    private boolean isBorder(int row, int col) {
        return row == 0
                || col == 0
                || row == HEIGHT - 1
                || col == WIDTH - 1;
    }
}
