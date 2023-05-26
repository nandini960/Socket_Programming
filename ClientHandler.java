import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class ClientHandler implements Runnable{
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    public ClientHandler(Socket clientSocket) throws IOException {
        this.client=clientSocket;
        in=new BufferedReader(new InputStreamReader(client.getInputStream()));
        out=new PrintWriter(client.getOutputStream(),true);


    }
    @Override
    public void run() {
        try {
            while (true) {
                String request = in.readLine();// SetA-One
                String[] arr=request.split("-");
                if(Server.mp.containsKey(arr[0])){
                    if(Server.mp.get(arr[0]).containsKey(arr[1])){
                        int tmp=Server.mp.get(arr[0]).get(arr[1]);
                        out.println(tmp);
                        for(int i=0;i<tmp;i++){
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            out.println(dtf.format(now));
                            try {
                                Thread.sleep(1000);
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
                else{
                    out.println("EMPTY");
                }
                if(request.equals("quit")) break;
//                if (request.contains("name")) {
//                    out.println(Server.getRandomName());
//                } else {
//                    out.println("Tell me a name");
//                }
            }
        }catch(IOException e) {
            System.err.println("IO Exception");
            System.err.println(e.getStackTrace());
        }finally
         {
            System.out.println("[SERVER] Closing");
            out.close();
            try{
                in.close();
            }
            catch (IOException e){
                System.err.println(e.getStackTrace());
            }
        }
    }
}
