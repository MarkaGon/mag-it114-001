package Project.common;

public class PositionPayload extends Payload {
    private int x, y;
    private String color;

    public PositionPayload(PayloadType pt) {
        setPayloadType(pt);
    }

    public PositionPayload() {
        // default to hide
        this(PayloadType.DRAW);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getColor(){
        return color;
    }


    public void setCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
