package Server.UserAction;

/* 发送消息时，先判断接收方是否在线（即是否存在于onlineuser表中）
若在线，即时对话：

若不在线，离线对话：
    讲消息储存到表中，对方登录时获取到信息，并将其从表中删除
 */
public class ChatManager {
}