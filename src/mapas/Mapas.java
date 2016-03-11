/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapas;

import graficos.Pantalla;
import mapas.cuadro.Cuadro;

/**
 *
 * @author terro
 */
public abstract class Mapas {
    protected int alto;
    protected int ancho;
    protected int[] cuadros;
    
    public Mapas(int alto, int ancho){
      this.alto=alto;
      this.ancho=ancho;
      
      cuadros = new int[ancho *alto];
      generarMapa();
      
    }
    
    public Mapas(String ruta){
        cargarMapa(ruta);
    }
    
    
    private void cargarMapa(String ruta){
    
    }
    
    protected void generarMapa(){
    
    }
    
    public void mostrar(int compensacionX, int compensacionY, Pantalla pantalla){
       
        pantalla.estableceDiferencia(compensacionX, compensacionY);
        
        int o = compensacionX >> 5;
        int e = (compensacionX + pantalla.obtenAncho()) >> 5;
        int n = compensacionY >> 5;
        int s =(compensacionY + pantalla.obtenAlto())>> 5;
        
        for (int y = n; y < s; y++) {
            for (int x = o; x < e; x++) {
                obtenCuadro(x,y).mostrar(x, y, pantalla);
            }
        }
    }
    
    public Cuadro obtenCuadro(final int x, final int y){
       switch(cuadros[x + y * ancho]){
           case 0:
               return Cuadro.ASFALTO;
           default:
             return  Cuadro.VACIO;
       
       }
        
      
    }
    
    public void actualizar(){
    }
}

