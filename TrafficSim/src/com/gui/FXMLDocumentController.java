/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import com.model.Route;
import com.model.Segment;
import com.model.Vehicle;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author 1
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Label label;

    @FXML
    private void handle(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final Segment up = new Segment(true, new Point2D(0, 100), new Point2D(700, 100), 2, 2);
        pane.getChildren().add(up.getRectangle());
        LinkedHashMap<Segment, Boolean> routeMap = new LinkedHashMap<>();
        routeMap.put(up, Boolean.TRUE);
        final Route route = new Route(routeMap);
        final Set<Vehicle> vehicles = new TreeSet<>();
        Vehicle v = new Vehicle(1000, route);
        pane.getChildren().add(v.getRepresentation());
        AnimationTimer timer = new AnimationTimer() {
            private int counter;
            @Override
            public void handle(long now) {
                label.setText(Integer.toString(counter++));
                v.moveForward();
                if (counter == 100) {
                    v.shouldStop();
                }/* else if (counter == 300) {
                    v.increaseSpeed(1);
                }*/
                
            }
        };
        timer.start();
    }
}
