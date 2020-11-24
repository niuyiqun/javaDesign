package base.DIYClass;

import java.io.Serializable;



public class Comment implements Serializable {
    private String comment, sender, item,timeDetail;
    Time time;

    public String getTimeDetail() {
        return timeDetail;
    }

    public void setTimeDetail(String timeDetail) {
        this.timeDetail = timeDetail;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
