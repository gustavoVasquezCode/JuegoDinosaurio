/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Videojuego;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.net.URL;
import javax.swing.ImageIcon;

public class DinoVolador {
    
    Juego jueguito; // Objeto de la clase juego
    
    URL sonido;
    AudioClip sonidoVolador;
    
    
    /* Variables que delimitan el area del depredador */
    Area cabeza;
    Area cuerpo;
    Area volador;
    
    /* Variables del tamaño del T-Rex */
    int anchoVolador = 70;
    int altoVolador = 70;
    
    /* Coordenadas iniciales donde se ubica el depredador */
    public static int xInicial = 3000;
    public static int yInicial = 20;
    
    /* Coordenadas para manipular el depredador */
    public static int xAutilizar = -4;
    //int yAutilizar = 0;
    
    public DinoVolador(Juego jueguito) {
        this.jueguito = jueguito;
        sonido = getClass().getResource("/multimedia/volador.wav");
        sonidoVolador = Applet.newAudioClip(sonido);
    }
    
    
    public void moverVolador() {
        bajarVolador();
        if(xInicial == 1000 || xInicial == 400)
            sonidoVolador();
        if(xInicial <= -100) {
            jueguito.contadorPuntos++;
            xInicial = 2500;
            if(jueguito.contadorPuntos % 3 == 0) {
                xAutilizar += -2;
                jueguito.numeroNivel++;
            }
            yInicial = 20;
        }
        else {
            if(choque()) {
                if(jueguito.vidas == 1) {
                    jueguito.finDelJuego();
                }
                else
                    jueguito.perderVidas();
            } else
               xInicial += xAutilizar;
        }
    }
    
    public void paint(Graphics2D g2d) {
        ImageIcon dinoDepredador = new ImageIcon(getClass().getResource("/Multimedia/dinoVolador.png"));
        g2d.drawImage(dinoDepredador.getImage(), xInicial, yInicial, anchoVolador, altoVolador, null);
    }
    
    public void bajarVolador() {
        if (xInicial <= 600)
            yInicial++;
    }
    
    public Area getBounds() {
        Ellipse2D forma1 = new Ellipse2D.Double(xInicial, yInicial, 40, 40);
        Rectangle forma2 = new Rectangle(xInicial + 12, yInicial + 16, 50, 53);
        
        cabeza = new Area(forma1);
        cuerpo = new Area(forma2);
        
        volador = cabeza;
        volador.add(cabeza);
        volador.add(cuerpo);
        
        return volador;
    }
    
    public boolean choque() {
        Area areaA = new Area(jueguito.triceraptops.getBounds());
        areaA.intersect(getBounds());
        
        return !areaA.isEmpty();
    }
    
    public void sonidoVolador() {
        sonidoVolador.play();
    }
    
}
