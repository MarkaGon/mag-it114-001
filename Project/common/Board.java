package Project.common;

public class Board {
    private String[][] pixels;

    public Board(int width, int height) {
        pixels = new String[width][height];
        initializeBoard();
    }

    private void initializeBoard() {
        // Initialize the board with default color (e.g., "white")
        for (int i = 0; i < pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = "white";
            }
        }
    }

    public String getColor(int x, int y) {
        return pixels[x][y];
    }

    public void setColor(int x, int y, String color) {
        pixels[x][y] = color;
    }
}
