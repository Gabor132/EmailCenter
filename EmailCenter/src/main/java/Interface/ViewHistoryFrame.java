/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import User.Accounts;
import User.Friends;
import User.Messages;
import User.Users;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;

/**
 *
 * @author Dragos-Alexandru
 */
public class ViewHistoryFrame extends javax.swing.JFrame {
    
    private final MainFrame rootFrame;
    
    private final Users loggedInUser;
    private List<Accounts> accounts = null;
    private List<Messages> messages = null;
    
    private final String NO_ATTACHMENT_LABEL = "NaN";
    
    /**
     * Creates new form ViewHistoryFrame
     * @param rootFrame
     * @param loggedInUser
     */
    public ViewHistoryFrame(MainFrame rootFrame, Users loggedInUser) {
        initComponents();
        this.rootFrame = rootFrame;
        this.loggedInUser = loggedInUser;
        setup();
    }
    
    public final void setup(){
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
            java.util.logging.Logger.getLogger(ViewHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                rootFrame.setVisible(true);
            }
            
        });
        
        setupAccountBox();
        
        this.setVisible(true);
    }
    
    public final void setupAccountBox(){
        accounts = loggedInUser.getAccounts();
        for(Accounts aux:accounts){
            this.accountBox.addItem(aux.getEmailAddress());
        }
        setupMessagesList();
    }
    
    public final void setupMessagesList(){
        Accounts account = getSelectedAccount();
        if(account == null){
            return;
        }
        List<Friends> friends = account.getFriends();
        DefaultListModel<String> messagesModel = new DefaultListModel<>();
        List<Messages> listMessages;
        messages = new ArrayList<>();
        for(Friends auxFriend:friends){
            listMessages = auxFriend.getMessages();
            for(Messages auxMessages:listMessages){
                messagesModel.addElement(auxMessages.getSubject()+"/"+auxFriend.getEmailAddress());
                messages.add(auxMessages);
            }
        }
        this.messagesList.setModel(messagesModel);
    }
    
    public Accounts getSelectedAccount(){
        return accounts.get(accountBox.getSelectedIndex());
    }
    
    public Messages getSelectedMessage(){
        if(messagesList.getSelectedIndex() >= 0){
            return messages.get(messagesList.getSelectedIndex());
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        accountBox = new javax.swing.JComboBox<>();
        currentAccountLabel = new javax.swing.JLabel();
        messagesScrollPane = new javax.swing.JScrollPane();
        messagesList = new javax.swing.JList<>();
        messagesContentScrollPane = new javax.swing.JScrollPane();
        messagesContentArea = new javax.swing.JTextArea();
        toField = new javax.swing.JTextField();
        toLabel = new javax.swing.JLabel();
        subjectLabel = new javax.swing.JLabel();
        subjectField = new javax.swing.JTextField();
        attachedFileDecorationLabel = new javax.swing.JLabel();
        attachedFileLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EmailCenter - View History");

        accountBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountBoxActionPerformed(evt);
            }
        });

        currentAccountLabel.setText("Current account:");

        messagesList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                messagesListValueChanged(evt);
            }
        });
        messagesScrollPane.setViewportView(messagesList);

        messagesContentArea.setColumns(20);
        messagesContentArea.setRows(5);
        messagesContentArea.setEnabled(false);
        messagesContentScrollPane.setViewportView(messagesContentArea);

        toField.setEnabled(false);

        toLabel.setText("To:");

        subjectLabel.setText("Subject:");

        subjectField.setEnabled(false);

        attachedFileDecorationLabel.setText("Attached file:");

        attachedFileLabel.setText(NO_ATTACHMENT_LABEL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(currentAccountLabel)
                            .addComponent(subjectLabel)
                            .addComponent(toLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(subjectField)
                            .addComponent(toField)
                            .addComponent(accountBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(messagesContentScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(attachedFileDecorationLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(attachedFileLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messagesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(accountBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currentAccountLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(toField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(toLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subjectField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subjectLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messagesContentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(attachedFileDecorationLabel)
                            .addComponent(attachedFileLabel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(messagesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void messagesListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_messagesListValueChanged
        Messages selectedMessage = getSelectedMessage();
        if(selectedMessage != null){
            subjectField.setText(selectedMessage.getSubject());
            toField.setText(selectedMessage.getReceiver().getEmailAddress());
            messagesContentArea.setText(selectedMessage.getTextContent());
            if(selectedMessage.getAttachmentName() != null){
                attachedFileLabel.setText(selectedMessage.getAttachmentName()+" ("
                        +selectedMessage.getAttachmentSize()+")");
            }else{
                attachedFileLabel.setText(NO_ATTACHMENT_LABEL);
            }
        }else{
            subjectField.setText("");
            toField.setText("");
            messagesContentArea.setText("");
            attachedFileLabel.setText(NO_ATTACHMENT_LABEL);
        }
    }//GEN-LAST:event_messagesListValueChanged

    private void accountBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountBoxActionPerformed
        setupMessagesList();
    }//GEN-LAST:event_accountBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> accountBox;
    private javax.swing.JLabel attachedFileDecorationLabel;
    private javax.swing.JLabel attachedFileLabel;
    private javax.swing.JLabel currentAccountLabel;
    private javax.swing.JTextArea messagesContentArea;
    private javax.swing.JScrollPane messagesContentScrollPane;
    private javax.swing.JList<String> messagesList;
    private javax.swing.JScrollPane messagesScrollPane;
    private javax.swing.JTextField subjectField;
    private javax.swing.JLabel subjectLabel;
    private javax.swing.JTextField toField;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables
}
