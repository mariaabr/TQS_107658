package ua.tqs.homework.models;

import java.util.*;

import ua.tqs.homework.models.Fare;

public class BusTrip {
    private String depOffset;
    private String arrOffset;
    private String depName;
    private String arrName;
    private String duration;
    // private int changeovers;
    // private List<Segment> segments;
    // private String deeplink;
    private List<Fare> fares;

    public BusTrip(String depOffset, String arrOffset, String depName, String arrName, String duration, List<Fare> fares) {
        this.depOffset = depOffset;
        this.arrOffset = arrOffset;
        this.depName = depName;
        this.arrName = arrName;
        this.duration = duration;
        // this.changeovers = changeovers;
        // this.deeplink = deeplink;
        this.fares = fares;
    }

    public String getDepOffset() {
        return this.depOffset;
    }

    public void setDepOffset(String depOffset) {
        this.depOffset = depOffset;
    }

    public String getArrOffset() {
        return this.arrOffset;
    }

    public void setArrOffset(String arrOffset) {
        this.arrOffset = arrOffset;
    }

    public String getDepName() {
        return this.depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    public String getArrName() {
        return this.arrName;
    }

    public void setArrName(String arrName) {
        this.arrName = arrName;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    // public int getChangeovers() {
    //     return this.changeovers;
    // }

    // public void setChangeovers(int changeovers) {
    //     this.changeovers = changeovers;
    // }

    // public List<Segment> getSegments() {
    //     return this.segments;
    // }

    // public void setSegments(List<Segment> segments) {
    //     this.segments = segments;
    // }

    // public String getDeeplink() {
    //     return this.deeplink;
    // }

    // public void setDeeplink(String deeplink) {
    //     this.deeplink = deeplink;
    // }

    public List<Fare> getFares() {
        return this.fares;
    }

    public void setFares(List<Fare> fares) {
        this.fares = fares;
    }

    @Override
    public String toString() {
        return "{" +
            " depOffset='" + getDepOffset() + "'" +
            ", arrOffset='" + getArrOffset() + "'" +
            ", depName='" + getDepName() + "'" +
            ", arrName='" + getArrName() + "'" +
            ", duration='" + getDuration() + "'" +
            ", fares='" + getFares() + "'" +
            " }";
    }

}
