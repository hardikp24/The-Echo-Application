
package guiclient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class GUIClient {

    static JTextArea ta ;
    static JTextField tf;
    static PrintWriter nos;
    static BufferedReader nis;
    
    public static void main(String[] args) throws Exception{
        
        System.out.println("Client logged in");
        
        Socket soc = new Socket("127.0.0.1", 9081);
        
        System.out.println("Client says connection established!");
        
        InputStream in = soc.getInputStream();
        OutputStream out = soc.getOutputStream(); 
        nos = new PrintWriter(new BufferedWriter(new OutputStreamWriter(out)) , true);
        nis = new BufferedReader(new InputStreamReader(in));
        
        /* *******************    GUI        ************************ */
        JFrame f1 = new JFrame("GUI Client");
        ta = new JTextArea();
        tf = new JTextField(20); 
        JPanel p1 = new JPanel();
        JButton b1 = new JButton("SEND");
        
        f1.setSize(400, 400);        
        p1.add(tf);
        p1.add(b1);        
        f1.add(BorderLayout.SOUTH, p1);        
        f1.add(ta);
        
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        L1 l1 = new L1();
        b1.addActionListener(l1);
        
        String s1 = nis.readLine();
        while(!s1.equals("end")){
            ta.append(s1+"\n");
            s1 = nis.readLine();
        }
    }
    
}

class L1 implements ActionListener{

    public void actionPerformed(ActionEvent e){
        String str = GUIClient.tf.getText();
        //GUIClient.ta.append(str+"\n");        
        GUIClient.tf.setText("");
    
        GUIClient.nos.println(str);
    
        if(str.equals("end")){System.exit(0);}
    }
    
    
}
