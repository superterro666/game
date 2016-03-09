/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

/**
 *
 * @author terro
 */
public final class Sprite {
    private final int lado;
    private int x;
    private int y;
    public int[] pixeles;
    private final HojaSprites hoja;
    
    //Coleccion de sprites
    public static Sprite asfalto = new Sprite(32,0,0,HojaSprites.desierto);
    
    // fin de la coleccion
    
    public Sprite(final int lado, final int fila, final int columna, final HojaSprites hoja ){
        this.lado=lado;
        this.pixeles = new int[lado*lado];
        this.x = columna*lado;
        this.y = fila*lado;
        this.hoja=hoja;
        
        for (int y = 0; y < lado; y++) {
            for (int x = 0; x < lado; x++) {
                pixeles[ y+x * lado]= hoja.pixeles[(x+this.x) + (y + this.y) * hoja.obtenAncho()];
            }
        }
    }
}
