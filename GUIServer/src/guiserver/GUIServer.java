package guiserver;
import java.net.*;
import java.io.*;
import javax.swing.*;

public class GUIServer {

    static JTextArea ta;
    static BufferedReader nis;
    static PrintWriter nos;
    
    public static void main(String[] args) throws Exception {
       
        System.out.println("Server signed in.");
        
       /**************  GUI  **************/
        JFrame f1 = new JFrame("GUI Server");
        ta = new JTextArea();     
        f1.setSize(400, 400);                    
        f1.add(ta);
        
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
      /**************  SERVER   **************/  
    
       ServerSocket ss = new ServerSocket(9081);
       Socket soc = ss.accept();
       System.out.println("Server says connection established!");
       InputStream in = soc.getInputStream();
       OutputStream out = soc.getOutputStream();
        
       nis = new BufferedReader(new InputStreamReader(in));
       nos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)) , true);
       
       String str = nis.readLine();
        
     
        while(! str.equals("end")){
            System.out.println("server received "+str);
            GUIServer.ta.append(str+"\n");
            
            //to pass it to the client back
            nos.println(str);            
            str = nis.readLine();         
       }
        nos.println("end");
        
        System.out.println("Server signed out.");
        System.exit(0); //line 36 to 46 thread dies, not the application 
          
    }
    
}