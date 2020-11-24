package Server;

import Server.Chat.AuctionManager;
import Server.Chat.ChatManager;
import Server.server.*;

import java.io.IOException;

public class ServerMain {
    public static void main(String[] args){
        try {
            new Login_Register_Server();
            new Get_List_Server();
            new Release_Item_Server();
            new Buy_Server();
            new Release_Comment_Server();
            new Get_Item_Comment_Server();
            new FavoriteServer();
            new ChatManager();
            new AuctionManager();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
