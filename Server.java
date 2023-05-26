import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static String[] names={"wily","felix","carlsbad","hobob"};
    private static String[] adjs={"the gentle","the urbane","the powerful","the ungentle"};
    private static final int PORT=9090;
//    private static List<ClientHandler> clients;
    public static HashMap<String,HashMap<String,Integer>> mp;

    private static ArrayList<ClientHandler> clients=new ArrayList<>();
    private static ExecutorService pool= Executors.newFixedThreadPool(4);
    public static void f(){
        mp=new HashMap<>();
        HashMap<String,Integer> h1=new HashMap<>();
        h1.put("One",1);
        h1.put("Two",2);
        HashMap<String,Integer> h2=new HashMap<>();
        h2.put("Three",3);
        h2.put("Four",4);
        HashMap<String,Integer> h3=new HashMap<>();
        h3.put("Five",5);
        h3.put("Six",6);
        HashMap<String,Integer> h4=new HashMap<>();
        h4.put("Seven",7);
        h4.put("Eight",8);
        HashMap<String,Integer> h5=new HashMap<>();
        h5.put("Nine",9);
        h5.put("Ten",10);
        mp.put("SetA",h1);
        mp.put("SetB",h2);
        mp.put("SetC",h3);
        mp.put("SetD",h4);
        mp.put("SetE",h5);
    }
    public static void main(String[] args) throws IOException{
        f();
        ServerSocket listener= new ServerSocket(PORT);
        while (true) {
            System.out.println("[SERVER] Waiting for client Connection");
            Socket client = listener.accept();
            System.out.println("[SERVER] Connected to client");
            ClientHandler clientThread=new ClientHandler(client);
            clients.add(clientThread);
            pool.execute(clientThread);
        }
    }
//    public static String getRandomName(){
//        String name=names[(int)(Math.random()*names.length)];
//        String adj=adjs[(int)(Math.random()* adjs.length)];
//        return name+" "+adj;
//    }
}
