package mike.magic.com.brofinder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Christoffer on 02-12-2017.
 */

public class Event implements Serializable {

    private String id;
    private String title;
    private String desc;
    private String commentsID;
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

    public Event() {}

    public Event(String id, String title, String desc, String commentsID, String creator, int day, int month, int year, int hour, int minute, double location_Lat, double location_Lng) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.commentsID = commentsID;
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

    public String getCommentsID() {
        return commentsID;
    }

    public void setCommentsID(String commentsID) {
        this.commentsID = commentsID;
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

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", commentsID='" + commentsID + '\'' +
                ", creator='" + creator + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hour=" + hour +
                ", minute=" + minute +
                ", location_Lat=" + location_Lat +
                ", location_Lng=" + location_Lng +
                '}';
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("id", id);
        result.put("desc", desc);
        result.put("title", title);
        result.put("commentsID", commentsID);
        result.put("creator", creator);
        result.put("day", day);
        result.put("month", month);
        result.put("year", year);
        result.put("hour", hour);
        result.put("minute", minute);
        result.put("location_Lat", location_Lat);
        result.put("location_Lng", location_Lng);
        return result;
    }
}