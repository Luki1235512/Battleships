import java.awt.*;

public class Ship {

    public enum ShipPlacementColour {
        Valid,
        Invalid,
        Placed
    }

    private Position gridPosition;

    private Position drawPosition;

    private int segments;

    private boolean isSideways;

    private int destroyedSections;

    private ShipPlacementColour shipPlacementColour;

    public Ship(Position gridPosition, Position drawPosition, int segments, boolean isSideways) {
        this.gridPosition = gridPosition;
        this.drawPosition = drawPosition;
        this.segments = segments;
        this.isSideways = isSideways;
        destroyedSections = 0;
        shipPlacementColour = ShipPlacementColour.Placed;
    }

    public void paint(Graphics g) {
        if (shipPlacementColour == ShipPlacementColour.Placed) {
            g.setColor(destroyedSections >= segments ? Color.RED : Color.DARK_GRAY);
        } else {
            g.setColor(shipPlacementColour == ShipPlacementColour.Valid ? Color.GREEN : Color.RED);
        }

        if (isSideways) {
            paintHorizontal(g);
        } else {
            paintVertical(g);
        }
    }

    public void paintVertical(Graphics g) {
        int boatWidth = (int) (SelectionGrid.CELL_SIZE * 0.8);
        int boatLeftX = drawPosition.x + SelectionGrid.CELL_SIZE / 2 - boatWidth / 2;
        g.fillPolygon(new int[] {drawPosition.x + SelectionGrid.CELL_SIZE / 2, boatLeftX, boatLeftX + boatWidth},
                new int[] {drawPosition.y + SelectionGrid.CELL_SIZE / 4, drawPosition.y + SelectionGrid.CELL_SIZE, drawPosition.y + SelectionGrid.CELL_SIZE}, 3);
        g.fillRect(boatLeftX, drawPosition.y + SelectionGrid.CELL_SIZE, boatWidth, (int) (SelectionGrid.CELL_SIZE * (segments - 1.2)));
    }

    public void paintHorizontal(Graphics g) {
        int boatWidth = (int)(SelectionGrid.CELL_SIZE * 0.8);
        int boatTopY = drawPosition.y + SelectionGrid.CELL_SIZE / 2 - boatWidth / 2;
        g.fillPolygon(new int[] {drawPosition.x + SelectionGrid.CELL_SIZE / 4,drawPosition.x + SelectionGrid.CELL_SIZE,drawPosition.x + SelectionGrid.CELL_SIZE},
                new int[]{drawPosition.y + SelectionGrid.CELL_SIZE / 2, boatTopY, boatTopY + boatWidth},3);
        g.fillRect(drawPosition.x + SelectionGrid.CELL_SIZE, boatTopY,
                (int)(SelectionGrid.CELL_SIZE * (segments-1.2)), boatWidth);
    }

    public boolean isSideways() {
        return isSideways;
    }

    public int getSegments() {
        return segments;
    }


}
