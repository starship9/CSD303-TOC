/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toc1washingmachine;

import java.util.Scanner;

/**
 *
 * @author starship9
 */
public class ToC1WashingMachine {

    /**
     * @param args the command line arguments
     */
    public static class WashingMachine {

        int fabricType;
        int waterTemp;
        int machineRPM;
        int stateTime[] = new int[4];
        String currState[] = {"Pre-wash", "Wash", "Rinse", "Spin"};
        boolean startStatus;
//        public WashingMachine(){
//            fabricType = 1;
//            waterTemp = 20;
//            machineRPM = 400;
//            stateTime[0] = 10;
//            stateTime[1] = 18;
//            stateTime[2] = 14;
//            stateTime[3] = 4;
//        }
        public void printParams(){
            System.out.println("Fabric type: "+this.fabricType);
            System.out.println("Water temperature: "+this.waterTemp);
            System.out.println("Washing machine RPM: "+this.machineRPM);
            for (int i = 0; i < 4; i++) {
                System.out.println("Time for "+currState[i]+" is "+stateTime[i]);
            }
        }
    }

    public static void setParams(WashingMachine wm) {
        switch (wm.fabricType) {
            case 1: {
                wm.waterTemp = 20;
                wm.machineRPM = 800;
                wm.stateTime[0] = 10;
                wm.stateTime[1] = 18;
                wm.stateTime[2] = 14;
                wm.stateTime[3] = 4;
            }

            case 2: {
                wm.waterTemp = 20;
                wm.machineRPM = 800;
                wm.stateTime[0] = 10;
                wm.stateTime[1] = 16;
                wm.stateTime[2] = 18;
                wm.stateTime[3] = 4;
            }

            case 3: {
                wm.waterTemp = 20;
                wm.machineRPM = 400;
                wm.stateTime[0] = 10;
                wm.stateTime[1] = 14;
                wm.stateTime[2] = 18;
                wm.stateTime[3] = 4;
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        WashingMachine wm = new WashingMachine();
        System.out.println("Enter the type of fabric being washed");
        int clothType;
        Scanner sc = new Scanner(System.in);
        clothType = sc.nextInt();
        wm.fabricType = clothType;

        setParams(wm);
        wm.printParams();

    }

}
