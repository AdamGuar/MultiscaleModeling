/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup.simulation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dom
 */
public class SimulationControl {
    public static boolean CLEAR_SCREAN = false;
    public static boolean RESTART_SIM = false;
    public static int GRAINS_NO = 20;
    public static String TYPE = "Moore";
    public static int PROBABILITY = 10;
    public static String SECOND_GROW_TYPE = "Substructure";
    public static boolean DRAW_BORDERS = false;
    public static int BORDER_THICKNESS = 1;
    
    //public static List<SavedCell> SAVED_CELLS = new ArrayList<SavedCell>();
    public static Map<Integer,Color> SAVED_CELLS = new HashMap<Integer,Color>();
    
    
}
