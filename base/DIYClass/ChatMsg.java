package base.DIYClass;

import java.io.Serializable;

public class ChatMsg implements Serializable{
    User getter, sender;
    private String sendTime;
    private String context;

    public User getGetter() {
        return getter;
    }

    public void setGetter(User getter) {
        this.getter = getter;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
