/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author terro
 */
public class HojaSprites {
    private int ancho;
    private int alto;
    public int[] pixeles;
    
    //colecion hoja de sprites
    public static HojaSprites desierto= new HojaSprites("/resources/texturas/texturas_2.png",512,512);
    // fin de la coleccion
   

    public HojaSprites(String ruta,int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        
        pixeles = new int[ancho*alto];
        BufferedImage imagen;
        try {
            imagen=ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException ex) {
            
            ex.printStackTrace();
        }
    }
    
    public int obtenAncho(){
        
            return ancho;
       
    }
}
