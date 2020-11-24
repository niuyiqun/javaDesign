package base.DIYClass;

import java.io.Serializable;

public class Message implements Serializable {
    private String sender;
    private String getter;
    private String context;
    private String sendTime;
    private String type;
    private String fileName;
    private int fileLen, fileNameLen, fileCon;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getSendTime(String detail) {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public int getFileNameLen() {
        return fileNameLen;
    }

    public void setFileNameLen(int fileNameLen) {
        this.fileNameLen = fileNameLen;
    }

    public int getFileCon() {
        return fileCon;
    }

    public void setFileCon(int fileCon) {
        this.fileCon = fileCon;
    }

    public String getSendTime() {
        return sendTime;
    }
}
