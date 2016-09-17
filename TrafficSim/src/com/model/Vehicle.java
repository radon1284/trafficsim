package com.model;

import java.util.Map;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class Vehicle implements Comparable<Vehicle> {

    private double currentSpeed;
    private double idealSpeed;
    private int startTime;
    private int turn;
    private Rectangle representation;
    private Route route;
    private Status status;

    public Vehicle(int startTime, Route route) {
        currentSpeed = 2;
        this.startTime = startTime;
        this.route = route;
        status = Status.NONE;
        Map.Entry<Segment, Boolean> item = route.getSegments().entrySet().stream().findFirst().get();
        Segment startSegment = item.getKey();
        boolean forward = item.getValue();
        Point2D from = startSegment.getFrom();
        
        representation = new Rectangle(from.getX(), from.getY(), 40, 60);
        representation.setFill(new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, new Stop[] { new Stop(0, Color.ORANGE), new Stop(1, Color.RED)}));
        representation.setRotate(startSegment.isHorizontal()
                ? (forward
                       ? (startSegment.isEast() ? 90 : 270)
                       : (startSegment.isEast() ? 270 : 90)
                ) : (forward
                       ? (startSegment.isNorth() ? 0 : 180)
                       : (startSegment.isNorth() ? 180 : 0)
        ));
    }

    public void increaseSpeed(double speed) {
        idealSpeed = speed;
        status = Status.INCREASE_SPEED;
    }

    public Rectangle getRepresentation() {
        return representation;
    }

    public double getRotate() {
        return representation.getRotate();
    }

    public void moveForward() {
        switch (status) {
            case WILL_STOP:
                currentSpeed -= 0.1;
                if (currentSpeed <= 0) {
                    status = Status.NONE;
                    currentSpeed = 0;
                }
                break;
            case INCREASE_SPEED:
                if (idealSpeed >= currentSpeed) {
                    status = Status.NONE;
                } else {
                    currentSpeed += 0.1;
                }
        }
        double radian = Math.toRadians(representation.getRotate() - 90);
        representation.setX(representation.getX() + Math.cos(radian) * currentSpeed);
        representation.setY(representation.getY() + Math.sin(radian) * currentSpeed);
    }

    public void setRotate(double rotate) {
        representation.setRotate(rotate);
    }

    @Override
    public int compareTo(Vehicle o) {
        return startTime - o.startTime;
    }

    public void shouldStop() {
        status = Status.WILL_STOP;
    }

    public void turnLeft() {
        turn--;
    }

    public void turnRight() {
        if (turn < 90) {
            turn++;
            setRotate(getRotate() + 1);
        } else {
            turn = 0;
        }
    }

    private enum Status {
        NONE, WILL_STOP, INCREASE_SPEED;
    }
}
