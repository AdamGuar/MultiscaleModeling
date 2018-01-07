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
    public static boolean IS_MONTE_CARLO = false;
    public static int MONTE_CARLO_NO = 20;
    
    //public static List<SavedCell> SAVED_CELLS = new ArrayList<SavedCell>();
    public static Map<Integer,Color> SAVED_CELLS = new HashMap<Integer,Color>();
    
    public static boolean ENERGY_HOMOGENOUS = true;
    public static int REC_ENERGY_LOWER_VALUE = 5;
    public static int REC_ENERGY_HIGHER_VALUE = 8;
    
    public static boolean DISTRIBUTE_ENERGY = false;
    public static Cell[][] PUBLIC_CELLS = null;
    public static boolean RECRYSTALIZE = false;
    public static List<Color> REC_NUCLEONS_COLORS;
    public static List<Nucleon> REC_NUCLEONS;
    public static boolean INITIALIZE_NUCLEONS = false;
    
    
}
