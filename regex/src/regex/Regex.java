/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;


/**
 *
 * @author manjul singh sachan
 */
public class Regex {
    private final int NO_OF_CHARS = 256;
    private int flag = 0;
    
    public void computeTransFun(String pat, int M, int TF[][]){
        int i,lps = 0, x;
        //int j,k;
 
       // Fill entries in first row
         for (x =0; x < NO_OF_CHARS; x++)
            TF[0][x] = 0;
         
         TF[0][(int)(pat.charAt(0))] = 1;
 
       // Fill entries in other rows
    for (i = 1; i< M; i++)
    {
        // Copy values from row at index lps
        for (x = 0; x < NO_OF_CHARS; x++)
            TF[i][x] = TF[lps][x];
 
        // Update the entry corresponding to this character
             //System.out.println((int)pat.charAt(i));
        TF[i][(int)(pat.charAt(i))] = i + 1;
        
//        for(j=0;j<M;j++){
//            for(k=0;k<256;k++){
//                System.out.println(k+"-->"+TF[j][k]);
//            }
//            //System.out.println("\n");
//        }
 
        // Update lps for next row to be filled
        if (i < M)
          lps = TF[lps][(int)(pat.charAt(i))];
    }
}
 
/* Prints all occurrences of pat in txt */
public void Search(String pat, String txt)
{
    int M = pat.length();
    int N = txt.length();
 
    int[][] TF = new int[M+1][NO_OF_CHARS];
   
    computeTransFun(pat, M, TF);
 
    // process text over FA.
    int i, j=0;
    for (i = 0; i < N; i++)
    {
       j = TF[j][(int)(txt.charAt(i))];
       if (j == M)
       {
           flag = 1;
       }
    }
    if(flag == 1)
        System.out.println("Accepted!");
    else
        System.out.println("Not Accepted!");
}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Regex rex = new Regex();
        String txt = "HELLO WORLD";
        String pat = "HELL";
        rex.Search(pat, txt);
    }
    
}
