/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import User.Accounts;
import User.User;
import java.awt.EventQueue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Dragos-Alexandru
 */
public class MainFrame extends javax.swing.JFrame {

    private static User loggedInUser = null;
    
    private final Object loginSignal;
    
    /**
     * Creates new form MainFrame
     * @param loginSignal
     */
    public MainFrame(Object loginSignal) {
        initComponents();
        this.loginSignal = loginSignal;
        setupAccountBox();
    }
    
    public final void setupAccountBox(){
        List<Accounts> accounts = loggedInUser.getAccounts();
        this.accountBox.removeAllItems();
        for(Accounts aux:accounts){
            this.accountBox.addItem(aux.getEmail());
        }
    }
    
    public static void setUser(User newUser){
        loggedInUser = newUser;
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        friendsScroll = new javax.swing.JScrollPane();
        friendsList = new javax.swing.JList<>();
        emailContentScroll = new javax.swing.JScrollPane();
        emailContentArea = new javax.swing.JEditorPane();
        subjectField = new javax.swing.JTextField();
        toField = new javax.swing.JTextField();
        sendButton = new javax.swing.JButton();
        attachFileButton = new javax.swing.JButton();
        detachFileButton = new javax.swing.JButton();
        accountBox = new javax.swing.JComboBox<>();
        currentAccountLabel = new javax.swing.JLabel();
        toLabel = new javax.swing.JLabel();
        subjectLabel = new javax.swing.JLabel();
        addReceiverButton = new javax.swing.JButton();
        deleteFriendButton = new javax.swing.JButton();
        deleteAccountButton = new javax.swing.JButton();
        addAccountButton = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        newMailMenu = new javax.swing.JMenu();
        historyMenu = new javax.swing.JMenu();
        signOutMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EmailCenter - "+loggedInUser.getUsername());
        setBackground(new java.awt.Color(255, 255, 255));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));

        friendsScroll.setViewportView(friendsList);

        emailContentScroll.setViewportView(emailContentArea);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        attachFileButton.setText("Attach File");

        detachFileButton.setText("Dettach File");

        currentAccountLabel.setText("Current account:");

        toLabel.setText("To:");

        subjectLabel.setText("Subject:");

        addReceiverButton.setText("Add Receiver");

        deleteFriendButton.setText("Delete Friend");
        deleteFriendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteFriendButtonActionPerformed(evt);
            }
        });

        deleteAccountButton.setText("Delete Account");
        deleteAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccountButtonActionPerformed(evt);
            }
        });

        addAccountButton.setText("Add Account");
        addAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAccountButtonActionPerformed(evt);
            }
        });

        newMailMenu.setText("New Email");
        menuBar.add(newMailMenu);

        historyMenu.setText("View History");
        menuBar.add(historyMenu);

        signOutMenu.setText("Sign out");
        signOutMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signOutMenuMouseClicked(evt);
            }
        });
        menuBar.add(signOutMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(emailContentScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(149, 149, 149))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currentAccountLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(subjectLabel)
                                    .addComponent(toLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(toField)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(accountBox, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addAccountButton)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(addReceiverButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(deleteAccountButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                                    .addComponent(subjectField)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(attachFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(detachFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                        .addComponent(deleteFriendButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendsScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(accountBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentAccountLabel)
                            .addComponent(deleteAccountButton)
                            .addComponent(addAccountButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel)
                            .addComponent(addReceiverButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subjectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subjectLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailContentScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(attachFileButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteFriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detachFileButton)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(friendsScroll))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteFriendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteFriendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteFriendButtonActionPerformed

    private void signOutMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signOutMenuMouseClicked
        synchronized(loginSignal){
            loggedInUser = null;
            loginSignal.notify();
            this.dispose();
        }
    }//GEN-LAST:event_signOutMenuMouseClicked

    private void addAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addAccountButtonActionPerformed
        AddAccountDialog addDialog = new AddAccountDialog(this, true, loggedInUser);
        addDialog.setVisible(true);
        setupAccountBox();
    }//GEN-LAST:event_addAccountButtonActionPerformed

    private void deleteAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAccountButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteAccountButtonActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        Accounts selectedAccount = null;
        List<Accounts> accounts = loggedInUser.getAccounts();
        for(Accounts aux:accounts){
            if(aux.getEmail().equals((String)accountBox.getSelectedItem())){
                selectedAccount = aux;
            }
        }
        if(selectedAccount == null){
            JOptionPane.showMessageDialog(null, "Please add an Account", "Send Email", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        if(subjectField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please add a Subject", "Send Email", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        if(toField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please add a receiver", "Send Email", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        if(emailContentArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Please add some content", "Send Email", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(selectedAccount.getEmail(), selectedAccount.getDecryptedPassword()));
            email.setSSL(true);
            email.setFrom(selectedAccount.getEmail());
            email.setSubject(subjectField.getText());
            email.setMsg(emailContentArea.getText());
            email.addTo(toField.getText());
            email.send();
        } catch (EmailException ex) {
            System.err.println("Failed "+ex.getMessage());
            ex.printStackTrace();
            return ;
        }
        JOptionPane.showMessageDialog(null, "Email was sent succesfully", "Send Email", JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_sendButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        while(true){
            Object loginSignal = new Object();
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    LoginFrame login = new LoginFrame(loginSignal);
                    login.setLocationRelativeTo(null);
                    login.setVisible(true);
                }
            });

            synchronized(loginSignal){
                try {
                    loginSignal.wait();
                } catch (InterruptedException ex) {
                    System.err.println("Failed: "+ ex.getMessage());
                }
            }

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(() -> {
                MainFrame frame = new MainFrame(loginSignal);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            });
            
            synchronized(loginSignal){
                try {
                    loginSignal.wait();
                } catch (InterruptedException ex) {
                    System.err.println("Failed: "+ex);
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accountBox;
    private javax.swing.JButton addAccountButton;
    private javax.swing.JButton addReceiverButton;
    private javax.swing.JButton attachFileButton;
    private javax.swing.JLabel currentAccountLabel;
    private javax.swing.JButton deleteAccountButton;
    private javax.swing.JButton deleteFriendButton;
    private javax.swing.JButton detachFileButton;
    private javax.swing.JEditorPane emailContentArea;
    private javax.swing.JScrollPane emailContentScroll;
    private javax.swing.JList<String> friendsList;
    private javax.swing.JScrollPane friendsScroll;
    private javax.swing.JMenu historyMenu;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu newMailMenu;
    private javax.swing.JButton sendButton;
    private javax.swing.JMenu signOutMenu;
    private javax.swing.JTextField subjectField;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JTextField toField;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables
}
