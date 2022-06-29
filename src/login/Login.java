/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.mysql.jdbc.PreparedStatement;
import components.Message;
import components.PanelCover;
import components.PanelLogin;
import dashboard.Main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import database.MysqlConnection;
import java.awt.Frame;
import model.UserModel;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javaswingdev.Notification;
import javax.swing.JOptionPane;

/**
 *
 * @author programmer
 */
public class Login extends javax.swing.JFrame {

//    Declaration des Attributs
    private MigLayout layout;
    private PanelCover cover;
    private PanelLogin log;
    public boolean isLogin;
    private final double coverSize = 40;
    private final double loginSize = 60;
    PreparedStatement pst;
    ResultSet rst;

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        init();
    }

    public void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();
//        Notification Not = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, "akak");
//            Not.show();
        ActionListener eventLogin = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    login();
                } catch (Exception ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
//                    notification("w", "Echec de Connexion");
                    JOptionPane.showMessageDialog(null, "Echec de Connexion");

                }
            }
        };
        log = new PanelLogin(eventLogin);
        bg.setLayout(layout);
        bg.add(cover, "width " + coverSize + "%, pos 0al 0 n 100%");
        bg.add(log, "width " + loginSize + "%, pos 1al 0 n 100%");
    }

    public void login() throws Exception {

        UserModel user = log.getUser();
        System.out.println(user.getPassword()+user.getUsername());
        if (!user.getPassword().isEmpty() && !user.getUsername().isEmpty()) {
            Connection con = MysqlConnection.getConnection();
            pst = (PreparedStatement) con.prepareStatement("select * from admin where username = ? and password=?");
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getPassword());
            rst = pst.executeQuery();
            if (rst.next()) {
                JOptionPane.showMessageDialog(null, "Connection Reussie");
                setVisible(false);
                Main M = new Main();
                M.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Identifiant Incorrect");

            }
        }else{
                JOptionPane.showMessageDialog(null, "Veuillez Remplir les champs");

        }

//        System.out.println("username: " + user.getUsername() + " password: " + user.getPassword());
//        showMessage(Message.MessageType.ERROR, "USERNAME OU PASSWORD INCORRECT");
//        setVisible(false);
//        new Dashboard().setVisible(true);
    }

//    public void notification(String Type, String Msg) {
//        if ("w".equals(Type)) {
//            Notification Not = new Notification(this, Notification.Type.WARNING, Notification.Location.CENTER, Msg);
//            Not.show();
//        } else {
//            Notification Not = new Notification(this, Notification.Type.INFO, Notification.Location.CENTER, Msg);
//            Not.show();
//        }
//    }

    public void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }

        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
