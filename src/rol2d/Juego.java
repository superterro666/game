/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rol2d;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author terro
 */
public class Juego extends Canvas implements Runnable{
    
   private static JFrame ventana; 
   private static Thread thread;
   private static final int   ANCHO=800;
   private static final int ALTO=600;
   private static int aps = 0;
   private static int fps = 0;
   private static final String NOMBRE="Juego";  
   private static volatile Boolean enFuncionamiento = false;
   
   public Juego()
   {
       setPreferredSize(new Dimension(ANCHO,ALTO));
       ventana = new JFrame();
       ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ventana.setResizable(false);
       ventana.setLayout(new BorderLayout());
       ventana.add(this, BorderLayout.CENTER);
       ventana.pack();
       ventana.setLocationRelativeTo(null);
       ventana.setVisible(true);
       
       
   }
   
   
    public static void main(String[] args) {
        Juego juego = new Juego();
        juego.iniciar();
       
    }
    
    private synchronized void  iniciar()
    {
        enFuncionamiento = true;
        
        thread = new Thread(this, "Graficos");
        thread.start();
    }
    
    private void actualizar(){
        aps++;
    }
    
    private void mostrar(){
        fps++;
    }
    
    private synchronized  void detener(){
      enFuncionamiento = false;
      try{  
          thread.join();
      }
      catch(Exception ex){
      ex.printStackTrace();
      }
    
    }

    @Override
    public void run() {
        
        final int NS_POR_SEGUNDOS = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDOS / APS_OBJETIVO;
        
        long referenciaActualizacion =   System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta =0;
       
      
        while(enFuncionamiento)
        {
            final long inicioBucle = System.nanoTime();
            tiempoTranscurrido= inicioBucle - referenciaActualizacion;
            referenciaActualizacion= inicioBucle;
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            
                while(delta>=1)
                {
                  actualizar();
                  delta--;
                }
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDOS){
                
                ventana.setTitle(NOMBRE+" || APS: "+aps+" || FPS: "+fps);
                aps=0;
                fps=0;
                referenciaContador= System.nanoTime();
            }
            mostrar();
        }
    }
}
