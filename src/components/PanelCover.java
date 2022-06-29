/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package components;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author programmer
 */
public class PanelCover extends javax.swing.JPanel {

    /**
     * Creates new form PanelCover
     */
    private ActionListener event;
    private MigLayout layout;
    private JLabel text;
    private JLabel logo;

    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout();
        layout = new MigLayout("wrap, fill", "[center]", "push[]10[]10[]push");
        setLayout(layout);
        init();
    }

    public void init() {
//        logo = new JLabel();
//        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/login/icon/iconen.png")));
//        add(logo);
        text = new JLabel("WELCOME");
        text.setFont(new java.awt.Font("Fira Code", 1, 35));
        text.setForeground(new Color(250,250,250));
        add(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    @Override
    protected void paintComponent(Graphics graphs) {
        Graphics2D g2 = (Graphics2D) graphs;
        GradientPaint pa = new GradientPaint(0, 0, new Color(0, 139, 240), 0, getHeight(), new Color(0, 46, 245));
        g2.setPaint(pa);
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(graphs);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splash/assets/hopital.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addEvent(ActionListener event) {
        this.event = event;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}