package base.DIYClass;

import java.io.Serializable;
/*
���� sendTime
 */
public class Item implements Serializable {
    String name; //��Ʒ��
    double price; //�۸�
    String description; //��Ʒ����
    String seller, buyer; // ���������
    int id, action,comment = 0;// action��ʾ����or��������comment������
    String imagePos; // ͼƬ·��
    int ifSold = 0;//���ݿ������۶�Ӧ1��δ�۶�Ӧ0
    String soldTime;//������ʱ��

    public String getSoldTime() {
        return soldTime;
    }

    public void setSoldTime(String soldTime) {
        this.soldTime = soldTime;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public int getIfSold() {
        return ifSold;
    }

    public void setIfSold(int ifSold) {
        this.ifSold = ifSold;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getImagePos() {
        return imagePos;
    }

    public void setImagePos(String imagePos) {
        this.imagePos = imagePos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
