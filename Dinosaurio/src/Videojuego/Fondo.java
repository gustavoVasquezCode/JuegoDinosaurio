
package Videojuego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.net.URL;
import javax.swing.ImageIcon;

public class Fondo {
    
    Juego jueguito; // Objeto de la clase juego
    
    /* Varibales del tamaño del wallpaper */
    int anchoWallpaper = 1000;
    int altoWallpaper = 400;
    
    /* Coordenadas iniciales para ir moviendo el fondo */
    int x1 = 1000;
    int y1 = 0;
    
    /* Coordenadas auxiliares para mover el fondo */
    int x2 = 0;
    int y2 = 0;
    
    public Fondo(Juego jueguito) {
        this.jueguito = jueguito;
    }
    
    public void moverPaisaje() {
        x1 -= 2;
        x2 -= 2;
        if(x1 == 0 && x2 == -1000) {
            x1 = 1000;
            x2 = 0;
        }
    }
    
    public void paint(Graphics2D g2d) {
        ImageIcon wallpaper = new ImageIcon(getClass().getResource("/Multimedia/fondo.jpg"));
        g2d.drawImage(wallpaper.getImage(), x1, y1, anchoWallpaper, altoWallpaper, null);
        g2d.drawImage(wallpaper.getImage(), x2, y2, anchoWallpaper, altoWallpaper, null);
    }
    
}
