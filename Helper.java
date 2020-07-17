package ro.client;
import java.io.IOException;
import java.util.Timer; 
import java.util.TimerTask; 

class Helper extends TimerTask {

	
	final String[] locationList = {"4442.9016N22614.3464E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97&Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97",
	   		 "4322.9012N21613.3454E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97&Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97",
	   		 "4455.9043N20034.3234E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97&Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97",
	   		 "4221.9021N22335.3125E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97&Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97",
	   		 "4115.9032N25242.3422E000.1061830323.8706000908000102,460,0,9520,3671,Home|74-DE-2B-44-88-8C|97& Home1|74-DE-2B-44-88-8C|97&Home2|74-DE-2B-44-88-8C|97& Home3|74-DE-2B-44-88-8C|97"
	   		 };
	  
	public void run() {
		String s;
		ClientSimulator clientSim = new ClientSimulator();
		s = clientSim.sendLocation(locationList);
		try {
			clientSim.sendMessage(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) 
//    { 
//          
//        Timer timer = new Timer(); 
//        TimerTask task = new Helper(); 
//          
//        timer.schedule(task, 2000, 5000); 
//          
//    } 

}

  
 
