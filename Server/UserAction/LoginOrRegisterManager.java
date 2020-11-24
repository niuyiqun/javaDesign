package Server.UserAction;

import base.BaseVariable;
import base.DIYClass.Message;
import base.DIYClass.User;
import base.DataBase;
import base.FunctionThread;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

// 用户登录注册和登出
public class LoginOrRegisterManager extends FunctionThread {

    public LoginOrRegisterManager(Socket socket) throws IOException {
        this.socket = socket;

        getStream();
        start();
    }

    public void run() {
        try {
            User user = (User) readObj();

            if (user.getAction() == BaseVariable.LOGIN) {
                message = login(user);
            } else if (user.getAction() == BaseVariable.REGISTER) {
                message = register(user);
            } else if (user.getAction() == BaseVariable.LOGOUT) {
                message = logout(user);
            } else if (user.getAction() == 5) { // 设置用户信息，包括姓名、性别、专业、年纪
                String name, sex, major, grade;
                name = user.getName();
                sex = user.getSex();
                grade = user.getGrade();
                major = user.getMajor();
                String userID = user.getUserId();

                String sql = "UPDATE 用户 SET 真实姓名='" + name + "', 性别='" + sex + "', 专业='" + major +
                        "', 年级='" + grade + "' WHERE 用户名='" + userID + "'";
                dataBase.update(sql);
                message = new Message();
                message.setContext("s");
                // 获取用户详细信息
            } else if(user.getAction() == 9){
                String userID = user.getUserId();

                String sql = "SELECT * FROM 用户 WHERE 用户名='" + userID + "'";
                ResultSet resultSet = dataBase.query(sql);
                message = new Message();
                while (resultSet.next()) {
                    message.setGetter(resultSet.getString("真实姓名"));
                    message.setContext(resultSet.getString("性别"));
                    message.setFileName(resultSet.getString("年级"));
                    message.setSender(resultSet.getString("专业"));
                }
            }else {
                System.out.println("用户action错误");
            }
            writeObj(message);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
    }


    public Message login(User user) {
        Message message = new Message();

        System.out.println("用户登录");
        String id = user.getUserId();
        String password = user.getPassword();
        System.out.println("UserId:" + id + ";password:" + password);

        String sql = "SELECT * FROM 用户";

        try {
            ResultSet resultSet = dataBase.query(sql);
            while (resultSet.next()) {
                if (id.equals(resultSet.getString("用户名")) && password.equals(resultSet.getString("密码"))) {
                    System.out.println("用户名密码正确");
                    message.setContext("success");

                    int num = 0;
                    sql = "SELECT * FROM 离线消息";//将离线期间获得消息数量设置为message的fileLen
                    ResultSet resultSet1 = dataBase.query(sql);
                    while (resultSet1.next()) {
                        if (resultSet1.getString("getter").equals(id)) {
                            num++;
                        }
                    }
                    message.setFileLen(num);
                    return message;
                }
            }
            System.out.println("用户名密码错误");
            message.setContext("fail");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return message;
        }
    }

    public Message register(User user) throws SQLException {
        Message message = new Message();
        String sql = "SELECT * FROM 用户";
        ResultSet resultSet = dataBase.query(sql);

        String userId = user.getUserId();
        String pw = user.getPassword();
        int status = user.getStatus();

        while (resultSet.next()) {
            if (user.getUserId().equals(resultSet.getString("用户名"))) {
                System.out.println("用户名已存在，注册失败");
                message.setContext("RFail");
                return message;
            }
        }
        System.out.println("无重复用户名");
        sql = "INSERT INTO 用户(用户名,密码,属性) VALUES('" + userId + "','" + pw + "'," + status + ")";

        dataBase.update(sql);

        sql = "UPDATE 用户数 SET count=count+1";

        dataBase.update(sql);
        System.out.println("注册成功");
        message.setContext("RSuccess");
        return message;

    }

    public Message logout(User user) throws SQLException {
        Message message = new Message();
        String sql = "DELETE FROM onlineuser WHERE userId='" + user.getUserId() + "'";
        dataBase.update(sql);
        message.setContext("Log out successfully");
        return message;
    }
}
