
package Videojuego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal {
    
    public static int reiniciarJuego = -1;
    
    public static void main(String[] args) {
        
        boolean bandera = true;
        
        JOptionPane.showMessageDialog(null, "¿Estas Listo?");
        
        JFrame ventana = new JFrame("Juego del Dinosaurio");
        Juego dino = new Juego();
        ventana.add(dino);
        ventana.setResizable(false);
        ventana.setSize(1000, 400);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(bandera);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        while(bandera) {
            if(Juego.juegoFinalizado) {
                reiniciarJuego = JOptionPane.showConfirmDialog(null, "Haz perdido, ¿Quieres jugar de nuevo?", "Haz perdido",JOptionPane.YES_NO_OPTION);
                if(reiniciarJuego == 0){
                    reiniciarValores();
                } else if(reiniciarJuego == 1) {
                    System.exit(0);
                }
            } else {
                dino.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(Juego.pierdeVidas == true) {
                    JOptionPane.showMessageDialog(null, "¡¡¡ CUIDADO !!!");
                    Juego.pierdeVidas = false;
                    Juego.vidas--;
                    Dino.yInicial = 270;
                    Dino.estaSaltando = false;
                    Depredador.xInicial = 1300;
                }
            }
        }
    }
    
    public static void reiniciarValores() {
        Juego.juegoFinalizado = false;
        Depredador.xAutilizar = -4;
        Juego.contadorPuntos = 0;
        Juego.numeroNivel = 1;
        Juego.vidas = 3;
        reiniciarJuego = -1;
        Depredador.xInicial = 1000;
    }
    
}
