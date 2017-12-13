package mike.magic.com.brofinder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Christoffer on 02-12-2017.
 */

public class Event implements Serializable {

    private int distance;
    private String id;
    private String title;
    private String desc;
    private String creator;
    //date, because everything else is hard
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;
    //location
    private double location_Lat;
    private double location_Lng;

    private List<Comment> commentList;

    public Event() {}

    public Event(String id, String title, String desc, String creator, int day, int month, int year, int hour, int minute, double location_Lat, double location_Lng) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.creator = creator;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.location_Lat = location_Lat;
        this.location_Lng = location_Lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public double getLocation_Lat() {
        return location_Lat;
    }

    public void setLocation_Lat(double location_Lat) {
        this.location_Lat = location_Lat;
    }

    public double getLocation_Lng() {
        return location_Lng;
    }

    public void setLocation_Lng(double location_Lng) {
        this.location_Lng = location_Lng;
    }

    public void setDistance(int distance){this.distance = distance;}

    public int getDistance(){return distance;}

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", creator='" + creator + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                ", minute=" + minute +
                ", location_Lat=" + location_Lat +
                ", location_Lng=" + location_Lng +
                ", commentList=" + commentList +
                ", distance=" + distance +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("desc", desc);
        result.put("title", title);
        result.put("creator", creator);
        result.put("day", day);
        result.put("month", month);
        result.put("year", year);
        result.put("hour", hour);
        result.put("minute", minute);
        result.put("location_Lat", location_Lat);
        result.put("location_Lng", location_Lng);
        result.put("Distance", distance);
        return result;
    }
}