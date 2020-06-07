package client;

public class Client {
    public static void main(String[] args) {
        new Thread(new ClientThread("127.0.0.1",6066,"卧室")).start();
        new Thread(new ClientThread("127.0.0.1",6066,"卫生间")).start();
        new Thread(new ClientThread("127.0.0.1",6066,"客厅")).start();
        new Thread(new ClientThread("127.0.0.1",6066,"厨房")).start();
        new Thread(new ClientThread("127.0.0.1",6066,"撤硕")).start();
    }
}
