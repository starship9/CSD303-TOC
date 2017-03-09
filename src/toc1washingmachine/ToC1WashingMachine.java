/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toc1washingmachine;

/**
 *
 * @author starship9
 */

public class ToC1WashingMachine {

    /**
     * @param args the command line arguments
     */
    public class WashingMachine{
        
        int fabricType;
        int waterTemp;
        int machineRPM;
        int stateTime [] = new int[4];
        String currState [] = {"Pre-wash","Wash","Rinse","Spin"};
        boolean startStatus;
        public WashingMachine(){
            fabricType = 1;
            waterTemp = 20;
            machineRPM = 400;
            stateTime[0] = 10;
            stateTime[1] = 18;
            stateTime[2] = 14;
            stateTime[3] = 4;
        }

        
    }
    
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    
}

