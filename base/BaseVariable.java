package base;



//程序中用到的基本常量
public class BaseVariable {
    public static final String HOST = "127.0.0.1"; //主机地址
    public static final int LOGIN_REGISTER_PORT = 8787; // 登录or注册
    public static final int GET_LIST_PORT = 8788; //
    public static final int BUY_ITEM_PORT = 8789;
    public static final int RELEASE_ITEM_PORT = 8790;
    public static final int RELEASE_COMMENT_PORT = 8791;
    public static final int GET_COMMENT_PORT = 8792;
    public static final int FAVORITE_PORT = 8793;
    public static final int CHAT_PORT = 7777;
    public static final int AUCTION_PORT = 7778;

    //Message消息类型
    public static final int LOGIN = 1; //登录
    public static final int REGISTER = 0; //注册
    public static final int LOGOUT = 2; //登出
}
