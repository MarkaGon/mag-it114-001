package Project.common;

import java.io.Serializable;

public class CoordinatePayload extends Payload implements Serializable {
    private int x;
    private int y;
    private String color;

    public CoordinatePayload(int x, int y, String color) {
        this.x = x;
        this.y = y;
        this.color = color;
        setPayloadType(PayloadType.COORDINATES);
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
