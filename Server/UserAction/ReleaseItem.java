package Server.UserAction;

import base.DIYClass.Item;
import base.DIYClass.Message;
import base.DataBase;
import base.FunctionThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

//接受一个item对象，储存到数据库中
//发送一个message对象，商品发布成功时其context为success
public class ReleaseItem extends FunctionThread {//应添加图片传输

    public ReleaseItem(Socket socket) {
        this.socket = socket;

        start();
    }

    public void run() {
//        System.out.println("发布商品功能启动");

        try {
            getStream();
            Item item = (Item) readObj();
            System.out.println(item.getImagePos());
            String pos = item.getImagePos();
            String str = pos.replace('\\', '/');
            System.out.println(str);
            String sql = "INSERT INTO 商品(商品名,商品价格,商品描述,卖家,图片路径,是否已售,买家,评论数,售卖方式) VALUES('" + item.getName() + "','"
                    + item.getPrice() + "','" + item.getDescription() + "','"
                    + item.getSeller() + "','" + str + "',0,'null',0,"+ item.getAction()+")";

            dataBase.update(sql);
            sql = "UPDATE 商品数 SET count=count+1";
            dataBase.update(sql);

            System.out.println("商品发布成功");
            message = new Message();
            message.setContext("success");
            writeObj(message);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }

    }
}
