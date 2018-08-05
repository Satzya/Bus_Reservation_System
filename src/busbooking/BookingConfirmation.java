package busbooking;

import static busbooking.BusSearch.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookingConfirmation extends JFrame {
    JLabel L0=new JLabel("Confirm Ticket:                              ");
    JLabel L00=new JLabel("Customer ID:");
    JTextField T00=new JTextField(10);
    JLabel L1=new JLabel("Source:    ");
    JTextField T1=new JTextField(10);
    JLabel L2=new JLabel("     Destination:       ");
    JTextField T2=new JTextField(10);
    JLabel L3=new JLabel("     Seat No.:       ");
    JTextField T3=new JTextField(10);
    JLabel L4=new JLabel("    Fare:     ");
    JTextField T4=new JTextField(10);
    JLabel L5=new JLabel("                                                                                                             Departure: ");
    JTextField T5=new JTextField(10);
    JLabel L6=new JLabel("    Arrival:       ");
    JTextField T6=new JTextField(10);
    JLabel L7=new JLabel("    Passenger Name:       ");
    JTextField T7=new JTextField(15);
    JLabel L8=new JLabel("    Date of Journey:       ");
    JTextField T8=new JTextField(15);
    JButton B1=new JButton("Confirm & Pay");
    JButton B2=new JButton("Cancel Booking");
    
    
    public BookingConfirmation(){
                JFrame J = new JFrame();
        setVisible(true);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        T00.setEditable(false);T1.setEditable(false);T2.setEditable(false);T3.setEditable(false);T4.setEditable(false);
        T5.setEditable(false);T6.setEditable(false);T7.setEditable(false);T8.setEditable(false);
        T00.setText(Integer.toString(BusSearch.temp));
        T1.setText(BusSearch.source);T2.setText(BusSearch.destination);T3.setText(BusSearch.seats);
        T4.setText(BusSearch.fare);T5.setText(BusSearch.departure);T6.setText(BusSearch.arrival);
        add(L0);add(L00);add(T00);add(L1);add(T1);add(L2);add(T2);add(L3);add(T3);add(L4);add(T4);add(L5);add(T5);
        add(L6);add(T6);add(L7);add(T7);add(L8);add(T8);add(B1);add(B2);
        T1.setText(BusSearch.source);T2.setText(BusSearch.destination);T3.setText(BusSearch.seats);
        T4.setText(BusSearch.fare);T5.setText(BusSearch.departure);T6.setText(BusSearch.arrival);
        T7.setText(BusSearch.psngr);T8.setText(BusSearch.date);
        L0.setForeground(Color.red);
        setTitle("Booking Confirmation - Smart Bus Booking");
        B1.addActionListener(new Bkc1());
        B2.addActionListener(new Bkc2());
        
    }
    
    class Bkc1 implements ActionListener{
            public void actionPerformed(ActionEvent A){
                    JOptionPane.showMessageDialog(null, "Booked Succesfully. Please note your ID as "+BusSearch.temp+" for further enquiry");
                    new BusSearch();
                    dispose();
                }}
                
    
            class Bkc2 implements ActionListener{
            public void actionPerformed(ActionEvent A){
                    try{
                    st=Con.createStatement();
                    P=Con.prepareCall("delete from psn where cid='"+BusSearch.temp+"'");
                    P.executeUpdate();
                    
                    Rs=null;
                    int r=Integer.parseInt(BusSearch.seats);
                    r++;
                    BusSearch.seats=Integer.toString(r);
                    st=Con.createStatement();
                    P=Con.prepareCall("update bus set seats=(?) where bid='"+BusSearch.bid+"'");
                    P.setInt(1,r);
                    P.executeUpdate();
                       JOptionPane.showMessageDialog(null, "Booking terminated");
                    dispose();
                    new BusSearch();
                    }
                    catch(Exception E){
                        JOptionPane.showMessageDialog(null, "Exception "+E);
                    }
                    
                    
                }
            
    }
    
    
    public static void main(String[] args) {
        new BookingConfirmation();
    }
}

