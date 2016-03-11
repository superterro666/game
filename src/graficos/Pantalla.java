/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import mapas.cuadro.Cuadro;

/**
 *
 * @author terro
 */
public final class Pantalla {
   private final int ancho;
   private final int alto;
   public final int[] pixeles;
   
   private int diferenciaX;
   private int diferenciaY;
   //temporales
 //  private final int LADO_SPRITE=32;
 //  private final int MASCARA_SPRITE= LADO_SPRITE -1;
   // fin temporales
   
   public Pantalla(final int ancho, final int alto){
       this.ancho=ancho;
       this.alto=alto;
       pixeles = new int[ancho*alto];
   
   }
   
   public void limpiar()
   {
       for (int i = 0; i < pixeles.length; i++) {
           pixeles[i]=0;
       }
   }
    // temporal 
 
   
   // fin temporal
   
   
   public void mostrarCuadro(int compensacionX, int compensacionY, Cuadro cuadro){
       
       compensacionX -= diferenciaX;
       compensacionY -= diferenciaY;
       
       for (int y = 0; y < cuadro.sprite.obtenLado(); y++) {
           
           int posicionY = y + compensacionY;
           
           for (int x = 0; x < cuadro.sprite.obtenLado(); x++) {
               
               int posicionX = x + compensacionX;
               
               if(posicionX < 0 || posicionX > ancho || posicionY <0 || posicionY > alto){
                   break;
               }
               
               pixeles[posicionX + posicionY * ancho]=cuadro.sprite.pixeles[x + y * cuadro.sprite.obtenLado()];
           }
       }
       
       
   }
   
   public void estableceDiferencia(final int diferenciaX, final int diferenciaY){
       this.diferenciaX=diferenciaX;
       this.diferenciaY=diferenciaY;
       
   }
   
   public int obtenAncho(){
       return ancho;
   }
   
   public int obtenAlto(){
       return alto;
   }
}