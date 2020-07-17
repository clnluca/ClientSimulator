package ro.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class ClientSimulator {
	
  private static final Logger logger = LogManager.getLogger(ClientSimulator.class);

  private Socket clientSocket;

  private DataOutputStream out;

  private DataInputStream in;

  public void startConnection(String ip, int port) throws UnknownHostException, IOException {

    clientSocket = new Socket(ip, port);
    out = new DataOutputStream(clientSocket.getOutputStream());
    in = new DataInputStream(clientSocket.getInputStream());
  }

  public void startListening() throws IOException {

    int code;
    StringBuilder sb = new StringBuilder();

    while ((code = in.read()) != -1) {

      // if received code is not 35 (#)
      if (code != 35) {

        // build incoming chars into a string builder message
        sb.append((char) code);
      } else {
        // get buffer
        System.out.println("RECEIVED FROM SERVER: " + sb.toString());

        // clear buffer
        sb.setLength(0);
      }
    }
  }
  public String messageReceived() throws IOException {

	    int code;
	    StringBuilder sb = new StringBuilder();
	    String SB ="";

	    while ((code = in.read()) != -1) {

	      // if received code is not 35 (#)
	      if (code != 35) {

	        // build incoming chars into a string builder message
	        sb.append((char) code);
	      } else {
	    	 SB += sb.toString();
	        // get buffer
	     // clear buffer
	        sb.setLength(0);
	      }
	    }
	    return SB;
	  }
 
  public void sendMessage(String msg) throws IOException {

    out.write(msg.getBytes());
  }

  public void stopConnection() throws IOException {

    in.close();
    out.close();
    clientSocket.close();
  }
  public String sendLocation (String[] loc){
	  Random rand = new Random();
	  int i = rand.nextInt(6);
	  String s = loc[i] + "#";
	  return s;
  }
  public static void main(String[] args) throws IOException, InterruptedException {

    System.out.println("Start ClientSimulator" ); 
    final ClientSimulator client = new ClientSimulator();
    client.startConnection("20.52.47.168", 9020);
    // client.startConnection("127.0.0.1", 9020);

    new Thread() {
      public void run() {

        try {
          client.startListening();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }.start();
//   String Imeif = "356200909469143";
    long IMEI = 0;
    for (int i = 0; i < 14; i++)
    	IMEI += IMEI * 10 + Math.random()*10;

      
//    client.sendMessage("IWAP00123456789012345#");
//    client.sendMessage(
//        "IWAP01080524A2232.9806N11404.9355E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97&Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97#");
//    client.sendMessage(
//        "IWAP02,zh_cn,0,7,460,0,9520|3671|13,9520|3672|12,9520|3673|11,9520|3674|10,9520|3675|9,9520|3676|8,9520|3677|7,4,1|D8-24-BD-79-FA-1F|59&2|3C-46-D8-6D-CE-01|81&3|0C-4C-39-1A-7C-65|69&4|70-A8-E3-5D-D7-C0|65#");
//    client.sendMessage("IWAP03,06000908000102,5555,30#");
//    client.sendMessage(
//        "IWAP10080524A2232.9806N11404.9355E000.1061830323.8706000908000502,460,0,9520,3671,00,zh-cn,00,HOME|74-DE-2B-44-88-8C|97&HOME1|74-DE-2B-;44-88-8C|97&HOME2|74-DE-2B-44-88-8C|97&HOME3|74-DE-2B-44-88-8C|97#");

    client.sendMessage("**** Connection with server is established ****#");
    client.sendMessage("IWAP00" + IMEI + "#");     //login
    
     
//    new Thread () {
//    	public void run() {
//    		try {
//    			String s;
//    			s = client.sendLocation(locationList);
//    			client.sendMessage("IMEI" + s);
//    			
//    		}
//    		catch (IOException e) {
//    	          e.printStackTrace();
//    	}
//    }}.start();
    
    Timer timer = new Timer(); 
    TimerTask task = new Helper(); 
      
    timer.scheduleAtFixedRate(task, 0, 10000); 
    
    
    Thread.sleep(30000);
    client.stopConnection();
    System.out.println("End ClientSimulator" );
  }
}
