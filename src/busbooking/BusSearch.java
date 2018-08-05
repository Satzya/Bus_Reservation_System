package busbooking;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import java.util.Date;

public class BusSearch extends JFrame{
    static String date,source,destination,fare,departure,arrival,seats,psngr;
    static int bid=0,temp=1301;
    static Connection Con=null;
    static Statement st=null;
    static PreparedStatement P=null; 
    static ResultSet Rs=null;
    JCalendar Cal=new JCalendar();
    JLabel L0=new JLabel("Please select your source and destination:                              ");
    JLabel L1=new JLabel("Source:    ");
    JComboBox C1;
    JLabel L2=new JLabel("     Destination:       ");
    JComboBox C2;
    JButton B1=new JButton("Search Buses");
    JButton B2=new JButton("Cancel");
    JButton B3=new JButton("Search Ticket");
    Vector<String> V=new Vector<>();
    Vector<String> W=new Vector<>();
    public BusSearch(){
//        Database Connection
    try{
    Class.forName("com.mysql.jdbc.Driver");
    Con=DriverManager.getConnection("jdbc:mysql://localhost/busbook","root","root");
    }
    catch(Exception E){
    JOptionPane.showMessageDialog(null, "Database Connectivity Unsucccessful");
    dispose();
    System.exit(0);
    }     
//    End DAtabase Connection
        
        L0.setForeground(Color.RED);
        V.add("Delhi    ");V.add("Indore   ");V.add("Mumbai   ");
        C1=new JComboBox(V);
        W.add("Agra     ");W.add("Ahmedabad");W.add("Goa      ");W.add("Mumbai   ");
        C2=new JComboBox(W);
        JFrame J = new JFrame();
        setTitle("Bus Search - Smart Bus Booking");
        setVisible(true);
        setLayout(new FlowLayout());
        
        add(L0);add(L1);add(C1);add(L2);add(C2);add(Cal);add(B1);add(B3);add(B2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH); 
        B1.addActionListener(new BS1());
        B2.addActionListener(new BS2());
        B3.addActionListener(new BS3());
        Date d=new Date();
        Cal.setMinSelectableDate(d);
    }
    
    class BS1 implements ActionListener{
        
        public void actionPerformed(ActionEvent A){
            dateMethod();
            String c1=C1.getSelectedItem().toString();String c2=C2.getSelectedItem().toString();
            
            try{
            
                st=Con.createStatement();
                P=Con.prepareCall("select * from bus where src='"+c1+"' and dstn='"+c2+"'");
                Rs=P.executeQuery();
                if(Rs.next()){
                    bid=Rs.getInt(1);
                    source=c1;
                    destination=c2;
                    seats=Rs.getString(4);
                    fare=Rs.getString(5);
                    departure=Rs.getString(6);
                    arrival=Rs.getString(7);
//                    System.out.println(departure+""+arrival);
                        if(seats.equals("0")){
                            JOptionPane.showMessageDialog(null, "No seats available");
                            return;
                        }
                    new busbooking.BusSearchResult();
                    dispose();
                }
                
                else{
                    JOptionPane.showMessageDialog(null, "Buses not available on the specified cities");
                    C1.requestFocus();
                }
            }
                catch(Exception E){
                    JOptionPane.showMessageDialog(null, "Invalid ID");
                }
            
            
            
        }
    }
    
    class BS2 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            System.exit(0);
        }
    }
    
    class BS3 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            new CancelBusTicket();
            dispose();
        }
    }
    
    void dateMethod(){
            String a="",b="";
            date=Cal.getDate().toString();
            System.out.println(Cal.getDate());
            for(int i=0;i<=10;i++){
                a=a+date.charAt(i);
            }
            for(int i=24;i<date.length();i++){
                b=b+date.charAt(i);
            }
            date="";
            date=date+a+b;
            System.out.println(date);
    }
    
    
    
    public static void main(String[] args) {
        new BusSearch();
    }
}
