package concurrentserver;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class ConcurrentServer {
   
    public static void main(String[] args) throws Exception {
        
         
        System.out.println("Server Signinig in");
        
        ServerSocket ss = new ServerSocket(9081);
        for (int i = 0; i < 10; i++) {
            Socket soc = ss.accept();
            Conversion c = new Conversion(soc);
            c.start();
        }
        
    }
    
}

class Conversion extends Thread{
    
    Socket soc;  
    //bcz to create nis nos u need socket thus defining soc again through constructor
    Conversion(Socket s){
        soc = s;
    }
    
    public void run() {        
        try {            
           /**************  GUI  **************/
            JFrame f1 = new JFrame("GUI Concurrent Server");
            JTextArea ta = new JTextArea();
            f1.setSize(400, 400);
            f1.add(ta);
            
            f1.setVisible(true);
            f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            /**************  SERVER   **********/
            
            System.out.println("Server says connection established");
         
            BufferedReader nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            PrintWriter nos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(soc.getOutputStream())), true);
            
            String s = nis.readLine();
            while(!s.equals("end")){
                System.out.println("server received "+s);
                ta.append(s+"\n");
                nos.println(s);
                s = nis.readLine();
            }            
            nos.println("end");            
            System.out.println("Server signed out.");
            System.exit(0);
            
        } catch (Exception e) {System.out.println("IO Exception caught"+e);}
        
    }
}
