/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import java.util.Random;

/**
 *
 * @author terro
 */
public class MapaGenerado extends Mapas{
    
    private final static Random aleatorio = new Random();
    
    public MapaGenerado(int alto, int ancho) {
        super(alto, ancho);
    }
    
  protected void generarMapa(){
      for (int y = 0; y < alto; y++) {
          for (int x = 0; x < ancho; x++) {
              cuadros[x + y * ancho]=aleatorio.nextInt(3);
          }
      }
    }
    
}
