/*
  A GUI consumer loan assistant. You input a loan
balance and yearly interest rate. You then have two options: (1) enter the desired
number of payments and the loan assistant computes the monthly payment, or
(2) enter the desired monthly payment and the loan assistant determines the
number of payments you will make. An analysis of your loan, including total of
payments and interest paid is also provided.
 */
package loanasistant;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.*;
/*
 * @author Ak
 */
public class LoanAsistant extends JFrame{
    
    JLabel LBalanceLabel=new JLabel();
    JLabel IRateLabel=new JLabel();
    JLabel NPaymentsLabel=new JLabel();
    JLabel MPaymentsLabel=new JLabel();
    JLabel analysisLabel=new JLabel();
    JTextField LBalanceField=new JTextField();
    JTextField IRateField=new JTextField();
    JTextField NPaymentsField=new JTextField();
    JTextField MPaymentsField=new JTextField();
    JTextArea analysisField=new JTextArea();
    JButton computeButton=new JButton();
    JButton nAnalysisButton=new JButton();
    JButton NPayButton=new JButton();
    JButton MPayButton=new JButton();
    JButton exitButton=new JButton();
    
   
    double Lbalance; double interest; double NPays; double MPays;
    double mi, balance, finalPayment;
    public static void main(String[] args) {
        new LoanAsistant().show();
    }
    
    public LoanAsistant(){
        //Frame constructor
        setTitle("Loan Assistant demo");
        //setSize(500,300);
        addWindowListener(new WindowAdapter(){
        public void windowClosing(WindowEvent e){
            exitForm(e);
        }});
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints=new GridBagConstraints();
        
        LBalanceLabel.setText("Loan Balance");
        LBalanceLabel.setFont(new Font("Calibiri", Font.PLAIN, 14));
        //gridConstraints.anchor=GridBagConstraints.WEST;
        gridConstraints.gridx=0; gridConstraints.gridy=0;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(LBalanceLabel, gridConstraints);
        
        IRateLabel.setText("Interest Rate");
        IRateLabel.setFont(new Font("Calibiri", Font.PLAIN, 14));
        IRateLabel.setHorizontalAlignment(JLabel.LEFT);
        gridConstraints.gridx=0; gridConstraints.gridy=1;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(IRateLabel, gridConstraints);
        
        NPaymentsLabel.setText("Number of Payments");
        NPaymentsLabel.setFont(new Font("Calibiri", Font.PLAIN, 14));
        gridConstraints.gridx=0; gridConstraints.gridy=2;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(NPaymentsLabel, gridConstraints);
        
        MPaymentsLabel.setText("Monthly Payments");
        MPaymentsLabel.setFont(new Font("Calibiri", Font.PLAIN, 14));
        gridConstraints.gridx=0; gridConstraints.gridy=3;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(MPaymentsLabel, gridConstraints);
        
        analysisLabel.setText("Loan Analysis:");
        analysisLabel.setFont(new Font("Calibiri", Font.PLAIN, 14));
        gridConstraints.gridx=4; gridConstraints.gridy=0;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(analysisLabel, gridConstraints);
        
        LBalanceField.setText(" ");
        LBalanceField.setColumns(8);
        LBalanceField.setPreferredSize(new Dimension(80,30));
        gridConstraints.gridx=1; gridConstraints.gridy=0;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(LBalanceField, gridConstraints);
        
        IRateField.setText(" ");
        IRateField.setColumns(8);
        IRateField.setPreferredSize(new Dimension(80,30));
        gridConstraints.gridx=1; gridConstraints.gridy=1;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(IRateField, gridConstraints);
        
        NPaymentsField.setText(" ");
        NPaymentsField.setColumns(8);
        NPaymentsField.setPreferredSize(new Dimension(80,30));
        gridConstraints.gridx=1; gridConstraints.gridy=2;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(NPaymentsField, gridConstraints);
        
        MPaymentsField.setText(" ");
        MPaymentsField.setColumns(8);
        MPaymentsField.setPreferredSize(new Dimension(80,30));
        MPaymentsField.setBackground(Color.yellow);
        gridConstraints.gridx=1; gridConstraints.gridy=3;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(MPaymentsField, gridConstraints);
        
        analysisField.setText(" ");
        analysisField.setPreferredSize(new Dimension(300,200));
        analysisField.setBackground(Color.white);
        analysisField.setAutoscrolls(true);
        gridConstraints.gridx=4; gridConstraints.gridy=1;
        gridConstraints.gridwidth=3;
        gridConstraints.gridheight=4;
        gridConstraints.insets=new Insets(0,5,10,10);
        getContentPane().add(analysisField, gridConstraints);
        
        computeButton.setText("Compute Monthly Payment");
        computeButton.setFont(new Font("Arial", Font.BOLD, 11));
        gridConstraints.gridx=0; gridConstraints.gridy=4;
        gridConstraints.gridwidth=2;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(computeButton, gridConstraints);
        
        nAnalysisButton.setText("New Loan Analysis");
        nAnalysisButton.setEnabled(false);
        nAnalysisButton.setFont(new Font("Arial", Font.BOLD, 11));
        gridConstraints.gridx=0; gridConstraints.gridy=5;
        gridConstraints.gridwidth=2;
        gridConstraints.insets=new Insets(10,5,10,10);
        getContentPane().add(nAnalysisButton, gridConstraints);
        
        NPayButton.setText("Number of Payments");
        NPayButton.setFocusable(false);
        NPayButton.setEnabled(true);
        NPayButton.setFont(new Font("Arial", Font.BOLD, 11));
        gridConstraints.gridx=2; gridConstraints.gridy=1;
        gridConstraints.insets=new Insets(0,0,0,0);
        getContentPane().add(NPayButton, gridConstraints);
        
        MPayButton.setText("Amount per Month");
        MPayButton.setFocusable(false);
        MPayButton.setEnabled(true);
        MPayButton.setFont(new Font("Arial", Font.BOLD, 11));
        gridConstraints.gridx=2; gridConstraints.gridy=3;
        gridConstraints.insets=new Insets(0,0,0,0);
        getContentPane().add(MPayButton, gridConstraints);
        
        exitButton.setText("Exit");
        exitButton.setFocusable(false);
        exitButton.setEnabled(true);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 11));
        gridConstraints.gridx=5; gridConstraints.gridy=5;
        gridConstraints.gridwidth=2;
        getContentPane().add(exitButton, gridConstraints);
        pack();
        
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                exitAction(e);
            }
        });
        LBalanceField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                LBalanceAction(e);
            }
        });
        IRateField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                IRateAction(e);
            }
        });
        NPaymentsField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                NPaymentsAction(e);
            }
        });
        MPaymentsField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MPaymentsAction(e);
            }
        });
        NPayButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                NPayAction(e);
            }
        });
        MPayButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                MPayAction(e);
            }
        });
        computeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                computeButtonAction(e);
            }
        });
        nAnalysisButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nAnalysisAction();
            }
        });
    }
    
    private void exitForm(WindowEvent e){
        System.exit(0);
    }
    private void exitAction(ActionEvent e){
        System.exit(0);
    }
    private void LBalanceAction(ActionEvent e){
        LBalanceField.transferFocus();
    }
    private void IRateAction(ActionEvent e){
        IRateField.transferFocus();
    }
    private void NPaymentsAction(ActionEvent e){
        NPaymentsField.transferFocus();
    }
    private void MPaymentsAction(ActionEvent e){
        MPaymentsField.transferFocus();
    }
    private void computeButtonAction(ActionEvent e){
        //double Lbalance=0, interest, mi=0, MPays;
       // double NPays=0;
        try{
            Lbalance=Double.parseDouble(LBalanceField.getText());
            interest=Double.parseDouble(IRateField.getText());
            mi=interest/1200;
            
        }catch(NumberFormatException ex){
               errorDisplay();
           }
        if(computeButton.getText().equals("Compute Monthly Payment")){
           try{
            String npays=NPaymentsField.getText();
            NPays=Double.parseDouble(npays);
            double sum=0;
           for(int k=0;k<(NPays-1);k++){
               sum+=Math.pow(1+mi, k);
           }
           if((int)(mi)==0){
               MPays=Lbalance/NPays;
           }else{
            MPays= (Lbalance*Math.pow(1+mi, NPays))/sum;
           }
            MPaymentsField.setText(new DecimalFormat("0.00").format(MPays));
            System.out.println(Lbalance+" "+MPays);
            AnalysisAction();
            
           }catch(NumberFormatException ex){
               errorDisplay();
           }
           
       }else if(computeButton.getText().equals("Compute Number of Payments")){
           try{ 
            String mpays=MPaymentsField.getText();
            MPays=Double.parseDouble(mpays);
            if ((int)(mi)==0){
                NPays=(int)(Lbalance/MPays);
            }else{
            NPays=(Math.log(MPays)-Math.log(MPays-Lbalance*mi))/Math.log(1+mi);
            }
            NPaymentsField.setText(new DecimalFormat("0.00").format(NPays));
            System.out.println(Lbalance+" "+NPays);
            
            AnalysisAction();
           }catch(NumberFormatException ex){
               errorDisplay();
           }
        }
    }
    private void NPayAction(ActionEvent e){
        NPayButton.setVisible(false);
        MPayButton.setVisible(true);
        MPaymentsField.setEditable(false);
        NPaymentsField.setBackground(Color.white);
        MPaymentsField.setFocusable(false);
        NPaymentsField.setText(" ");
        MPaymentsField.setText(" ");
        MPaymentsField.setBackground(Color.yellow);
        NPaymentsField.setEditable(true);
        NPaymentsField.setFocusable(true);
        analysisField.setFocusable(false);
        computeButton.setText("Compute Monthly Payment");
        LBalanceField.requestFocus();
    }
    private void MPayAction(ActionEvent e){
        NPayButton.setVisible(true);
        NPaymentsField.setEditable(true);
        MPaymentsField.setBackground(Color.white);
        NPaymentsField.setFocusable(false);
        MPayButton.setVisible(false);
        MPaymentsField.setText(" ");
        NPaymentsField.setText(" ");
        NPaymentsField.setBackground(Color.yellow);
        MPaymentsField.setEditable(true);
        MPaymentsField.setFocusable(true);
        analysisField.setFocusable(false);
        computeButton.setText("Compute Number of Payments");
    }
    private void errorDisplay(){
        analysisField.setText("Input Must be Integers!!");
        analysisField.setFont(new Font("Arial", Font.BOLD, 15));
        analysisField.setForeground(Color.black);
        analysisField.setBackground(Color.LIGHT_GRAY);
        NPaymentsField.setText(" ");
        MPaymentsField.setText(" ");
        //computeButton.setEnabled
    }
    private void AnalysisAction(){
        analysisField.setForeground(Color.black);
        analysisField.setBackground(Color.white);
        analysisField.setFont(new Font("Arial", Font.PLAIN, 12));
        computeButton.setEnabled(false);
        nAnalysisButton.setEnabled(true);
        nAnalysisButton.requestFocus();
        analysisField.setText("Loan Balance:  $"+ new DecimalFormat("0.00").format(Lbalance));
        analysisField.append("\n"+"Interest Rate:  "+ new DecimalFormat("0.00").format(interest)+"%");
        
        balance = Lbalance;
        for (int paymentNumber = 1; paymentNumber <= NPays - 1;paymentNumber++) {
         balance += balance * mi - MPays; 
        }
        finalPayment = balance;
        if (finalPayment > MPays){
            balance += balance * mi - MPays;
            finalPayment = balance;
            NPays++;
            NPaymentsField.setText((new DecimalFormat("00").format(NPays))); }
        
        analysisField.append("\n\n" + new DecimalFormat("00").format(NPays-1) + " Payments of  $" + new DecimalFormat("0.00").format(MPays));
        analysisField.append("\n" + "Final Payment of:  $" + new DecimalFormat("0.00").format(finalPayment));
        
        analysisField.append("\n" + "Total Payments:  $" + new DecimalFormat("0.00").format((NPays - 1) * MPays + finalPayment)); 
        analysisField.append("\n" + "Interest Paid  $" + new DecimalFormat("0.00").format((NPays - 1) * MPays +finalPayment - Lbalance));
        
        }
    private void nAnalysisAction(){
        computeButton.setEnabled(true);
        nAnalysisButton.setEnabled(false);
        MPayButton.setVisible(true);
        NPayButton.setVisible(true);
        analysisField.setText(" ");
        LBalanceField.setText(" ");
        IRateField.setText(" ");
        NPaymentsField.setText(" ");
        MPaymentsField.setText(" ");
    }
    
}


