import java.awt.*;

public class Marker extends Rectangle {

    private final Color HIT_COLOUR = new Color(219, 23, 23, 180);

    private final Color MISS_COLOUR = new Color(26, 26, 97, 180);

    private final int PADDING = 3;

    private boolean showMarker;

    private Ship shipAtMarker;

    public Marker(int x, int y, int width, int height) {
        super(x, y, width, height);
        reset();
    }

    public void reset() {
        shipAtMarker = null;
        showMarker = false;
    }

    public boolean isShip() {
        return shipAtMarker != null;
    }

    public void setAsShip(Ship ship) {
        this.shipAtMarker = ship;
    }

    public void paint(Graphics g) {
        if (!showMarker) {
            return;
        }

        g.setColor(isShip() ? HIT_COLOUR : MISS_COLOUR);
        g.fillRect(position.x + PADDING + 1, position.y + PADDING + 1, width - PADDING * 2, height - PADDING * 2);
    }

}
