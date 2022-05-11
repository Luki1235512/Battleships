import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GamePanel extends JPanel implements MouseListener, MouseMotionListener {

    public enum GameState {
        PlacingShips,
        FiringShots,
        GameOver
    }

    private SelectionGrid computer;

    private StatusPanel statusPanel;

    private GameState gameState;


    public GamePanel() {
        computer = new SelectionGrid(0, 0);
        setBackground(new Color(42, 136, 163));
        setPreferredSize(new Dimension(computer.getWidth(), computer.getHeight() + 50));
        addMouseListener(this);
        addMouseMotionListener(this);
        statusPanel = new StatusPanel(new Position(0, computer.getHeight() + 1), computer.getWidth(), 49);
        restart();
    }

    public void restart() {
        computer.reset();
        computer.setShowShips(false);
        computer.populateShips();
        statusPanel.reset();
        gameState = GameState.FiringShots;

    }

    public void paint(Graphics g) {
        super.paint(g);
        computer.paint(g);
//
//        if (gameState == GameState.PlacingShips) {
//
//        }

        statusPanel.paint(g);
    }

    public void handleInput(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(1);
        } else if (keyCode == KeyEvent.VK_R) {
            restart();
        }
        repaint();
    }

    private void tryFireAtComputer(Position mousePosition) {
        Position targetPosition = computer.getPositionInGrid(mousePosition.x, mousePosition.y);
        if (!computer.isPositionMarked(targetPosition)) {
            doPlayerTurn(targetPosition);
        }
    }

    private void doPlayerTurn(Position targetPosition) {
        boolean hit = computer.markPosition(targetPosition);
        String hitMiss = hit ? "Hit" : "Missed";
        String destroyed = "";
        if (hit && computer.getMarkerAtPosition(targetPosition).getAssociatedShip().isDestroyed()) {
            destroyed = "(Destroyed)";
        }

        statusPanel.setTopLine("Player " + hitMiss + " " + targetPosition + destroyed);
        if (computer.areAllShipsDestroyed()) {
            gameState = GameState.GameOver;
            statusPanel.showGameOver(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Position mousePosition = new Position(e.getX(), e.getY());
        if (gameState == GameState.FiringShots && computer.isPositionInside(mousePosition)) {
            tryFireAtComputer(mousePosition);
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
