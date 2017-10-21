/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import growup.utils.BitMapUtils;
import growup.utils.TextFileUtils;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Adam
 */
public class Frame extends JFrame {

    public int no;
    private Screen s;
    private Simulation sim;
    private List<Inclusion> incl;

    private int witdh;
    private int height;

    // Creates a menubar for a JFrame
    public int getWitdh() {
        return witdh;
    }

    public void setWitdh(int witdh) {
        this.witdh = witdh;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSimType() {
        return simType;
    }

    public void setSimType(String simType) {
        this.simType = simType;
    }
    private String simType = "Moore";

    private float timeSinceLastUpdate;
    public float iterationTime = 1f;

    //:TODO fix this constructors
    private void setUpMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu fileMenuImport = new JMenu("Import");
        JMenu fileMenuExport = new JMenu("Export");
        fileMenu.add(fileMenuImport);
        fileMenu.add(fileMenuExport);
        JMenuItem importActionBMP = new JMenuItem("BitMap");
        JMenuItem exportActionBMP = new JMenuItem("BitMap");
        JMenuItem importActionTXT = new JMenuItem("Text");
        JMenuItem exportActionTXT = new JMenuItem("Text");
        fileMenuImport.add(importActionBMP);
        fileMenuExport.add(exportActionBMP);
        fileMenuImport.add(importActionTXT);
        fileMenuExport.add(exportActionTXT);
        menuBar.add(fileMenu);
        super.setJMenuBar(menuBar);

        exportActionBMP.addActionListener((ActionEvent event) -> {
            JFileChooser saveFile = new JFileChooser();
            saveFile.setFileFilter(new FileNameExtensionFilter("bmp file", "bmp"));
            
            saveFile.addActionListener((ActionEvent e) -> {
                String path = saveFile.getSelectedFile().toString();
                path = path.endsWith(".bmp") ? path : path + ".bmp";
                BitMapUtils.writeImage(sim.getCells(), path);
            });
            
            saveFile.showSaveDialog(this);
            

        });
        
        importActionBMP.addActionListener((ActionEvent event)-> {
            JFileChooser openFile = new JFileChooser();
            openFile.addActionListener((ActionEvent e)->{
                try {
                    this.sim.setPause(true);
                    this.sim.setCellsByColors(BitMapUtils.readImage(openFile.getSelectedFile())); 
                    JOptionPane.showMessageDialog(this,"File opened, wait for your simulation to resume");
                    this.sim.checkAliveByColor();
                    this.sim.update();
                    
                    this.sim.setPause(false);
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            openFile.showOpenDialog(this);        
        });
        
        exportActionTXT.addActionListener((ActionEvent event) -> {
            JFileChooser saveFile = new JFileChooser();
            saveFile.setFileFilter(new FileNameExtensionFilter("text file", "txt"));
            
            saveFile.addActionListener((ActionEvent e) -> {
                String path = saveFile.getSelectedFile().toString();
                path = path.endsWith(".txt") ? path : path + ".txt";
                try {
                    TextFileUtils.createTXTFile(this.sim.getCells(), path);
                    JOptionPane.showMessageDialog(this,"File saved");
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            saveFile.showSaveDialog(this);
            

        });
        
        importActionTXT.addActionListener((ActionEvent event)-> {
            JFileChooser openFile = new JFileChooser();
            openFile.addActionListener((ActionEvent e)->{
                try {
                    this.sim.setPause(true);
                    this.sim.setCellsByColors(TextFileUtils.loadColorsFromTXTFile2(openFile.getSelectedFile(),this.witdh,this.height));
                    JOptionPane.showMessageDialog(this,"File opened, wait for your simulation to resume");
                    this.sim.checkAliveByColor();
                    this.sim.update();
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
            openFile.showOpenDialog(this);        
        });
        

    }

    public Frame() {
        this.witdh = 800;
        this.height = 600;
        setSize(this.witdh, this.height);
        setTitle("Simulation :" + simType);
        setUpMenuBar();
    }

    public Frame(int width, int height, List<Inclusion> incl) {
        this.witdh = width;
        this.height = height;
        setSize(this.witdh, this.height);
        setTitle("Simulation :" + simType);
        this.incl = incl;
        setUpMenuBar();
    }

    public void createScreen(String location, int radius) {
        sim = new Simulation(no, simType, location, radius, this.witdh, this.height, this.incl);
        s = new Screen();
        s.setBounds(0, 0, this.witdh, this.height);
        add(s);
        this.addKeyListener(sim);
        this.addMouseListener(sim);

    }

    public void repaint() {

        s.repaint();

    }

    /*public void update(float timeSinceLastFrame){
     timeSinceLastUpdate+=timeSinceLastFrame;
     if(timeSinceLastUpdate>iterationTime){
     sim.update();
     timeSinceLastUpdate=0;
        
     }
        
        
     }*/
    public void update() {

        sim.update();

    }

    private class Screen extends JLabel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponents(g);
            sim.drawCells(g);
        }

    }

}
