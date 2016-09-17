package com.model;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

public class Segment {

    private boolean horizontal;
    private Point2D from;
    private Point2D to;
    private int backward;
    private int forward;

    public Segment(boolean horizontal, Point2D from, Point2D to, int backward, int forward) {
        this.horizontal = horizontal;
        this.from = from;
        this.to = to;
        this.backward = backward;
        this.forward = forward;
    }

    public Point2D getFrom() {
        return from;
    }

    public Point2D getTo() {
        return to;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public boolean isNorth() {
        return from.getY() > to.getY();
    }

    public boolean isEast() {
        return from.getX() < to.getX();
    }

    public Rectangle getRectangle() {
        int fromWidth = backward * 40;
        int toWidth = forward * 40;
        return horizontal ? new Rectangle(from.getX(), from.getY() - fromWidth,
                to.getX(), to.getY() + toWidth)
                :  new Rectangle(from.getX() - fromWidth, from.getY(),
                to.getX() + toWidth, to.getY());
    }
}
