package com.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Route {

    private LinkedHashMap<Segment, Boolean> segments;

    public Route(LinkedHashMap<Segment, Boolean> segments) {
        this.segments = segments;
    }

    public LinkedHashMap<Segment, Boolean> getSegments() {
        return segments;
    }

}
