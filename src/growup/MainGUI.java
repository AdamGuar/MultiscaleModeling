/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import growup.inclusion.Inclusion;
import growup.inclusion.InclusionSet;
import growup.inclusion.InclusionShape;
import growup.inclusion.InclusionTimeType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author student
 */
public class MainGUI extends javax.swing.JFrame {

    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        additionalComponentsInitalization();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        startButton = new javax.swing.JButton();
        nbhoodCheck = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        locationBox = new javax.swing.JComboBox();
        radLabel = new javax.swing.JLabel();
        radSpinner = new javax.swing.JSpinner();
        mouseSeedsLabel = new javax.swing.JLabel();
        mouseSeedsCounter = new javax.swing.JLabel();
        widthSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        heightSpinner = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        inclusionNumberSpinner = new javax.swing.JSpinner();
        inclusionSizeSpinner = new javax.swing.JSpinner();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        inclusionComboBox = new javax.swing.JComboBox<>();
        inclusionShapeComboBox = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Naive grain growth");
        setResizable(false);

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        nbhoodCheck.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Moore", "VonNeuman", "HexLewy", "HexPrawy", "HexRandom", "Penta" }));
        nbhoodCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nbhoodCheckActionPerformed(evt);
            }
        });

        jLabel1.setText("Neighborhood");

        jLabel2.setText("No. grains");

        jLabel3.setText("Starting grain location");

        locationBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Random", "Równomiernie", "Promień", "Myszka" }));
        locationBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locationBoxActionPerformed(evt);
            }
        });

        radLabel.setText("Radius:");
        radLabel.setEnabled(false);

        radSpinner.setEnabled(false);

        mouseSeedsLabel.setText("No. clicked grains");
        mouseSeedsLabel.setEnabled(false);

        mouseSeedsCounter.setText("0");
        mouseSeedsCounter.setEnabled(false);

        jLabel4.setText("Screen width");

        jLabel5.setText("Screen height");

        jLabel6.setText("Inclusions");

        jLabel7.setText("No.");

        jLabel8.setText("Size");

        jLabel9.setText("Including time");

        inclusionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PRE", "POST" }));
        inclusionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionComboBoxActionPerformed(evt);
            }
        });

        inclusionShapeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SQUARE", "CIRCLE" }));
        inclusionShapeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inclusionShapeComboBoxActionPerformed(evt);
            }
        });

        jLabel10.setText("Inclusion shape");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(startButton)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(radLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(widthSpinner, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(radSpinner)
                            .addComponent(nbhoodCheck, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSpinner1)
                            .addComponent(locationBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(heightSpinner)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mouseSeedsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mouseSeedsCounter))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inclusionShapeComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inclusionNumberSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addComponent(inclusionSizeSpinner)
                            .addComponent(inclusionComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nbhoodCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(locationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radLabel)
                    .addComponent(radSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(widthSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(heightSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(inclusionNumberSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inclusionSizeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inclusionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inclusionShapeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mouseSeedsLabel)
                    .addComponent(mouseSeedsCounter))
                .addGap(18, 18, 18)
                .addComponent(startButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void additionalComponentsInitalization() {
        nbhoodCheck.setEnabled(false);
        widthSpinner.setValue(800);
        heightSpinner.setValue(600);
        locationBox.setEnabled(false);
        
    }
    

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        // TODO add your handling code here:
        
        int width = (Integer) widthSpinner.getValue();
        int height = (Integer) heightSpinner.getValue();
        
        simThread runner = new simThread();
        runner.setWidth(width);
        runner.setHeight(height);
        //  System.out.print(nbhoodCheck.getSelectedItem().toString());
        
        List<Inclusion> inclusions = new ArrayList<Inclusion>();
        
        int number = Integer.parseInt(inclusionNumberSpinner.getValue().toString());
        int size = Integer.parseInt(inclusionSizeSpinner.getValue().toString());
        
        for(int i = 0; i < number ;i++){
            inclusions.add(new Inclusion(size, width, height));
        }
        
        InclusionTimeType timeType = InclusionTimeType.valueOf(inclusionComboBox.getSelectedItem().toString());
        InclusionShape shape = InclusionShape.valueOf(inclusionShapeComboBox.getSelectedItem().toString());
        
        
        InclusionSet inclusionsSet = new InclusionSet(inclusions, timeType,shape);
        
        runner.setIncl(inclusionsSet);
        
        runner.setType(nbhoodCheck.getSelectedItem().toString());
        runner.setLocation(locationBox.getSelectedItem().toString());
        runner.setRadius((int) radSpinner.getValue());
        runner.simSpeed = (float) 10000;
        runner.no = (int) jSpinner1.getValue();
        Thread thread = new Thread(runner);
        thread.start();
        

    }//GEN-LAST:event_startButtonActionPerformed

    private void locationBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locationBoxActionPerformed
        // TODO add your handling code here:
        if (locationBox.getSelectedItem().toString().equals("Promień")) {
            radLabel.setEnabled(true);
            radSpinner.setEnabled(true);
        } else {
            radLabel.setEnabled(false);
            radSpinner.setEnabled(false);
        }
    }//GEN-LAST:event_locationBoxActionPerformed

    private void nbhoodCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nbhoodCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nbhoodCheckActionPerformed

    private void inclusionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inclusionComboBoxActionPerformed

    private void inclusionShapeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inclusionShapeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inclusionShapeComboBoxActionPerformed

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
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner heightSpinner;
    private javax.swing.JComboBox<String> inclusionComboBox;
    private javax.swing.JSpinner inclusionNumberSpinner;
    private javax.swing.JComboBox<String> inclusionShapeComboBox;
    private javax.swing.JSpinner inclusionSizeSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JComboBox locationBox;
    private javax.swing.JLabel mouseSeedsCounter;
    public static javax.swing.JLabel mouseSeedsLabel;
    private javax.swing.JComboBox nbhoodCheck;
    private javax.swing.JLabel radLabel;
    private javax.swing.JSpinner radSpinner;
    private javax.swing.JButton startButton;
    private javax.swing.JSpinner widthSpinner;
    // End of variables declaration//GEN-END:variables
}
