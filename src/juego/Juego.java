/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego;

import control.Teclado;
import graficos.Pantalla;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
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
   private static Teclado teclado;
   private static Pantalla pantalla;
   private static BufferedImage imagen = new BufferedImage(ANCHO,ALTO, BufferedImage.TYPE_INT_RGB);
   private static int[] pixeles =((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
   private static int x=0;
   private static int y=0;
   
   public Juego()
   {
       setPreferredSize(new Dimension(ANCHO,ALTO));
       
       pantalla = new Pantalla(ANCHO,ALTO);
       
       ventana = new JFrame();
       ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       ventana.setResizable(false);
       ventana.setLayout(new BorderLayout());
       ventana.add(this, BorderLayout.CENTER);
       ventana.pack();
       ventana.setLocationRelativeTo(null);
       ventana.setVisible(true);
       
       teclado= new Teclado();
       addKeyListener(teclado);
       
       
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
        teclado.actualizar();
        if(teclado.arriba)
        {
           y++;
        }
        
         if(teclado.abajo)
        {
           y--;
        }
          
        if(teclado.izquierda)
        {
            x++;
        }
        
        if(teclado.derecha)
        {
            x--;
        }
        aps++;
    }
    
    private void mostrar(){
        BufferStrategy estrategia = getBufferStrategy();
        if(estrategia==null){
        createBufferStrategy(3);
        return;
        }
        pantalla.limpiar();
        pantalla.mostrar(x,y);
        
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
        
        Graphics g = estrategia.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        estrategia.show();
        
      //   for (int i = 0; i < pixeles.length; i++) {
      //      pixeles[i]=pantalla.pixeles[i];
      //  }
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
       
        requestFocus();
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
