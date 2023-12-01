
package Videojuego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.JPanel;

public class Juego extends JPanel{
    
    Dino triceraptops = new Dino(this); // Objeto de la clase Dino
    Depredador trex = new Depredador(this); // Objeto de la clase Depredador
    Fondo paisaje = new Fondo(this); // Objeto de la clase Fondo
    DinoVolador volador = new DinoVolador(this);
    
    /*Variables del juego*/
    public static boolean juegoFinalizado = false;
    public static boolean pierdeVidas = false;
    public static int vidas = 3;
    public static int contadorPuntos = 0;
    public static int numeroNivel = 1;
    
    /* Sonidos de juego (salto, trex, triceratop) */
    URL salto;
    URL fondo;
    
    AudioClip saltoTricera;
    AudioClip musicaDeFondo;
    
    
    public Juego() {
        
        salto = getClass().getResource("/multimedia/cartoon-jump-6462.wav");
        saltoTricera = Applet.newAudioClip(salto);
        
        fondo = getClass().getResource("/multimedia/musicaFondo2.wav");
        musicaDeFondo = Applet.newAudioClip(fondo);
        
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
                    saltoTricera.play();
                    triceraptops.activarSalto(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
            
        });
        
        setFocusable(true);
        reproducirMusica();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        dibujar(g2d);
        dibujarPuntaje(g2d);
    }
    
    public void dibujar(Graphics2D g2d) {
        paisaje.paint(g2d);
        triceraptops.paint(g2d);
        trex.paint(g2d);
        volador.paint(g2d);
        movimiento();
    }
    
    public void dibujarPuntaje(Graphics2D g2d) {
        Graphics2D g1 = g2d;
        Graphics g2 = g2d;
        
        Font puntaje = new Font("Arial", Font.BOLD, 30);
        g2d.setFont(puntaje);
        g2d.setColor(Color.blue);
        
        g1.drawString("Puntaje: " + contadorPuntos, 50, 30);
        g1.drawString("Vidas: " + vidas, 50, 60);
        g1.drawString("Nivel: " + numeroNivel, 50, 90);
    }
    
    public void movimiento() {
        trex.moverDepredador();
        triceraptops.saltandoDino();
        paisaje.moverPaisaje();
        volador.moverVolador();
    }
    
    public void finDelJuego() {
        juegoFinalizado = true;
    }
    
    public void perderVidas(){
        pierdeVidas = true;
    }
    
    public void reproducirMusica() {
        musicaDeFondo.loop();
    }
    
}
