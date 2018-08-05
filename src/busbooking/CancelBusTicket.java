
package busbooking;
import static busbooking.BusSearch.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CancelBusTicket extends JFrame{
    JLabel L0=new JLabel("Cancel Ticket:                              ");
    JLabel L00=new JLabel("Enter Ticket No.:");
    JTextField T00=new JTextField(10);
    JLabel LB=new JLabel("     Bus No.:       ");
    JTextField TB=new JTextField(10);
    JLabel L1=new JLabel("Source:    ");
    JTextField T1=new JTextField(10);
    JLabel L2=new JLabel("     Destination:       ");
    JTextField T2=new JTextField(10);
    JLabel L3=new JLabel("     Seat No.:       ");
    JTextField T3=new JTextField(10);
    JLabel L5=new JLabel("                                                                                                             Departure: ");
    JTextField T5=new JTextField(10);
    JLabel L6=new JLabel("    Arrival:       ");
    JTextField T6=new JTextField(10);
    JLabel L7=new JLabel("    Passenger Name:       ");
    JTextField T7=new JTextField(15);
    JLabel L8=new JLabel("    Date of Journey:       ");
    JTextField T8=new JTextField(15);
    JButton B1=new JButton("Search");
    JButton B2=new JButton("Cancel Ticket");
    JButton B3=new JButton("Back");
    
    public CancelBusTicket(){
        JFrame J = new JFrame();
        setVisible(true);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        T1.setEditable(false);T2.setEditable(false);T3.setEditable(false);TB.setEditable(false);
        T5.setEditable(false);T6.setEditable(false);T7.setEditable(false);T8.setEditable(false);
        setTitle("Search Bus - Smart Bus Booking");        
        
        
        add(L0);add(L00);add(T00);add(LB);add(TB);add(L1);add(T1);add(L2);add(T2);add(L3);add(T3);add(L5);
        add(T5);add(L6);add(T6);add(L7);add(T7);add(L8);add(T8);add(B1);add(B2);add(B3);
        
        if(T1.getText().equals("")){
            B2.setEnabled(false);
        }
        B1.addActionListener(new CBT1());
        B2.addActionListener(new CBT2());
        B3.addActionListener(new CBT3());
    }
    
        class CBT1 implements ActionListener{
            public void actionPerformed(ActionEvent A){
                    try{
                        st=Con.createStatement();
                        P=Con.prepareCall("select * from psn where cid=(?)");
                        P.setInt(1,Integer.parseInt(T00.getText()));
                        Rs=P.executeQuery();
                        if(Rs.next()){
                            TB.setText(Rs.getString(2));
                            T1.setText(Rs.getString(3));T2.setText(Rs.getString(4));
                            T3.setText(Rs.getString(5));T5.setText(Rs.getString(7));
                            T6.setText(Rs.getString(8));T7.setText(Rs.getString(9));
                            T8.setText(Rs.getString(6));
                            B2.setEnabled(true);
                            T00.setEditable(false);
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "No such records exists in the database");
                        }
                    }catch(Exception E){
                            JOptionPane.showMessageDialog(null, "Exception "+E);
                    }
                }}
        
     class CBT2 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            try{
                                       
                    Rs=null;
                    int r=Integer.parseInt(T3.getText());
                    //r--;
                    BusSearch.seats=Integer.toString(r);
                    
                    st=Con.createStatement();
                    P=Con.prepareCall("update bus set seats=(?) where bid='"+TB.getText()+"'");
                    P.setInt(1,r);
                    P.executeUpdate();
                    
                    
                    st=Con.createStatement();
                    P=Con.prepareCall("delete from psn where cid='"+T00.getText()+"'");
                    P.executeUpdate();
                    
                       JOptionPane.showMessageDialog(null, "Ticket Canceled Successfully ");
                    dispose();
                    new BusSearch();
                    }
                    catch(Exception E){
                        JOptionPane.showMessageDialog(null, "Exception "+E);
                    }
        }
    }
    
     class CBT3 implements ActionListener{
        public void actionPerformed(ActionEvent A){
            new BusSearch();
        }}
     
     
    public static void main(String[] args) {
        new CancelBusTicket();
    }
}
