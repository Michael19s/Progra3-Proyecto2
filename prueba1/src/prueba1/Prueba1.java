/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba1;

/**
 *
 * @author Maykol Burgos
 */
public class Prueba1 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        System.out.println(getNumberFromPosition(1356, 2));
    }
    
    public static int getNumberFromPosition(int pNumber, int pPosition){
        if(pPosition>0)
            return Prueba1.getNumberFromPosition((int)(pNumber/10), --pPosition);
        else 
            return (pNumber-(((int)pNumber/10)*10));
    }
    
}
