
package Videojuego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import javax.swing.ImageIcon;

public class Depredador {
    
    Juego jueguito; // Objeto de la clase juego
    
    URL rugido;
    AudioClip rugidoTrex;
    
    
    /* Variables que delimitan el area del depredador */
    Area cabeza;
    Area cuerpo;
    Area trex;
    
    /* Variables del tamaño del T-Rex */
    int anchoDepredador = 70;
    int altoDepredador = 70;
    
    /* Coordenadas iniciales donde se ubica el depredador */
    public static int xInicial = 1300;
    public static int yInicial = 270; // 270
    
    /* Coordenadas para manipular el depredador */
    public static int xAutilizar = -4;
    //int yAutilizar = 0;
    
    public Depredador(Juego jueguito) {
        this.jueguito = jueguito;
        rugido = getClass().getResource("/multimedia/Trex21.wav");
        rugidoTrex = Applet.newAudioClip(rugido);
    }
    
    
    public void moverDepredador() {
        if(xInicial == 1000 || xInicial == 400)
            sonidoTrex();
        if(xInicial <= -100) {
            jueguito.contadorPuntos++;
            xInicial = 1300;
            if(jueguito.contadorPuntos % 3 == 0) {
                xAutilizar += -2;
                jueguito.numeroNivel++;
            }
        }
        else {
            if(choque()) {
                if(jueguito.vidas == 1){
                    jueguito.finDelJuego();
                }
                else{
                    jueguito.perderVidas();
                }
            } else
               xInicial += xAutilizar;
        }
    }
    
    public void paint(Graphics2D g2d) {
        ImageIcon dinoDepredador = new ImageIcon(getClass().getResource("/Multimedia/Trex.png"));
        g2d.drawImage(dinoDepredador.getImage(), xInicial, yInicial, anchoDepredador, altoDepredador, null);
    }
    
    public Area getBounds() {
        Ellipse2D forma1 = new Ellipse2D.Double(xInicial, yInicial, 40, 40);
        Rectangle forma2 = new Rectangle(xInicial + 12, yInicial + 16, 50, 53);
        
        cabeza = new Area(forma1);
        cuerpo = new Area(forma2);
        
        trex = cabeza;
        trex.add(cabeza);
        trex.add(cuerpo);
        
        return trex;
    }
    
    public boolean choque() {
        Area areaA = new Area(jueguito.triceraptops.getBounds());
        areaA.intersect(getBounds());
        
        return !areaA.isEmpty();
    }
    
    public void sonidoTrex() {
        rugidoTrex.play();
    }
    
}
