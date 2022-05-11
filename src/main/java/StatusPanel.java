import java.awt.*;

public class StatusPanel extends Rectangle {

    private String topLine;

    private String bottomLine;

    private final Font font = new Font("Arial", Font.BOLD, 20);

    private final String gameOverWinLine = "You won! Well done!";

    private final String gameOverBottomLine = "Press R to restart.";

    public StatusPanel(Position position, int width, int height) {
        super(position, width, height);
    }

    public StatusPanel(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void setTopLine(String message) {
        topLine = message;
    }

    public void showGameOver(boolean playerWon) {
        topLine = gameOverWinLine;
        bottomLine = gameOverBottomLine;
    }

    public void reset() {
        topLine = "";
        bottomLine = "";
    }

    public void paint (Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(position.x, position.y, width, height);
        g.setColor(Color.BLACK);
        g.setFont(font);
        int strWidth = g.getFontMetrics().stringWidth(topLine);
        g.drawString(topLine, position.x + width / 2 - strWidth / 2, position.y + 20);
        strWidth = g.getFontMetrics().stringWidth(bottomLine);
        g.drawString(bottomLine, position.x + width / 2 - strWidth / 2, position.y + 40);
    }
}
