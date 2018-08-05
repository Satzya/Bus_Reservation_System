
package busbooking;

import static busbooking.BusSearch.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BusSearchResult extends JFrame {
    JLabel L0=new JLabel("Search Results:                              ");
    JLabel L1=new JLabel("Source:    ");
    JTextField T1=new JTextField(10);
    JLabel L2=new JLabel("     Destination:       ");
    JTextField T2=new JTextField(10);
    JLabel L3=new JLabel("     Seats Left:       ");
    JTextField T3=new JTextField(10);
    JLabel L4=new JLabel("    Fare:     ");
    JTextField T4=new JTextField(10);
    JLabel L5=new JLabel("                                                                                                             Departure: ");
    JTextField T5=new JTextField(10);
    JLabel L6=new JLabel("    Arrival:       ");
    JTextField T6=new JTextField(10);
    JLabel L7=new JLabel("    Passenger Name:       ");
    JTextField T7=new JTextField(15);
    JButton B1=new JButton("Book");
    JButton B2=new JButton("Replan");
    
    public BusSearchResult(){
        JFrame J = new JFrame();
        setVisible(true);
        setLayout(new FlowLayout());
        setTitle("Available Bus - Smart Bus Booking");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        T1.setEditable(false);T2.setEditable(false);T3.setEditable(false);T4.setEditable(false);
        T5.setEditable(false);T6.setEditable(false);
        T1.setText(BusSearch.source);T2.setText(BusSearch.destination);T3.setText(BusSearch.seats);
        T4.setText(BusSearch.fare);T5.setText(BusSearch.departure);T6.setText(BusSearch.arrival);
        add(L0);add(L1);add(T1);add(L2);add(T2);add(L3);add(T3);add(L4);add(T4);add(L5);add(T5);
        add(L6);add(T6);add(L7);add(T7);add(B1);add(B2);
        L0.setForeground(Color.red);
        B1.addActionListener(new Bsr1());
            }
    
    
    
            class Bsr1 implements ActionListener{
            public void actionPerformed(ActionEvent A){
                
                if(T7.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please provide a valid passenger name");
                    T7.requestFocus();
                    return;
                }
                else if(T7.getText()!=""){
                    try{
                        psngr=T7.getText();
                        st=Con.createStatement();
                P=Con.prepareCall("select * from psn");
                Rs=P.executeQuery();
                if(Rs.next()){
                    while(Rs.next()){
                        temp=Rs.getInt(1);
                    }temp++;
                    
                    Rs=null;
                    st=Con.createStatement();
                    P=Con.prepareCall("insert into psn values (?,?,?,?,?,?,?,?,?)");
                    P.setInt(1,temp);P.setInt(2,BusSearch.bid);P.setString(3,BusSearch.source);
                    P.setString(4,BusSearch.destination);P.setString(5,BusSearch.seats);
                    P.setString(6,BusSearch.date);P.setString(7,BusSearch.departure);
                    P.setString(8,BusSearch.arrival);P.setString(9,T7.getText());
                    P.executeUpdate();
                    
                    
                    Rs=null;
                  
                    int r=Integer.parseInt(BusSearch.seats);
                    r--;
                    BusSearch.seats=Integer.toString(r);
                    st=Con.createStatement();
                    P=Con.prepareCall("update bus set seats=(?) where bid='"+BusSearch.bid+"'");
                    P.setInt(1,r);
                    P.executeUpdate();
                    
                    
                    new BookingConfirmation();
                    dispose();
                    
                }
                        
                        
                        
                else{     
                
                    Rs=null;
                    st=Con.createStatement();
                    P=Con.prepareCall("insert into psn values (?,?,?,?,?,?,?,?,?)");
                    P.setInt(1,temp);P.setInt(2,BusSearch.bid);P.setString(3,BusSearch.source);
                    P.setString(4,BusSearch.destination);P.setString(5,BusSearch.seats);
                    P.setString(6,BusSearch.date);P.setString(7,BusSearch.departure);
                    P.setString(8,BusSearch.arrival);P.setString(9,T7.getText());
                    P.executeUpdate();
                    
                    Rs=null;
                    int r=Integer.parseInt(BusSearch.seats);
                    r--;
                    BusSearch.seats=Integer.toString(r);
                    st=Con.createStatement();
                    P=Con.prepareCall("update bus set seats=(?) where bid='"+BusSearch.bid+"'");
                    P.setInt(1,r);
                    P.executeUpdate();

                    
                    
                    
                    new BookingConfirmation();
                    dispose();
                }
                    }
                    catch(Exception E){
                    JOptionPane.showMessageDialog(null, "UnSuccessful"+E);
                        System.out.println(T7.getText());
                    }
                }} }
    
    
    
    
    public static void main(String[] args) {
        new BusSearchResult();
    }
}
