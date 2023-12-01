
package Videojuego;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import javax.swing.ImageIcon;

public class Dino {
    
    Juego jueguito; // Objeto de la clase Juego
    
    /* Variables para saber si salta o no el triceratop */
    public static boolean estaSaltando = false;
    boolean sube = false;
    boolean baja = false;
    
    /* Variables que delimitan el area del juego */
    Area patasDelanteras;
    Area patasTraseras;
    Area dinosaurio;
    Area tricera;
    
    /* Tamaño del dinosaurio */
    int anchoDinosaurio = 112;
    int altoDinosaurio = 78;
    
    /* Coordenadas iniciales del dinosaurio */
    public static int xInicial = 50;
    public static int yInicial = 270; // 250
    
    /* Coordenadas para manipular el dinosaurio */
    int xAutilizar = 0;
    int yAutilizar = 0;
    
    public Dino(Juego jueguito) {
        this.jueguito = jueguito;
    }
    
    public void saltandoDino() {
        if(xInicial + xAutilizar > 0 && xInicial + xAutilizar < jueguito.getWidth() - anchoDinosaurio) {
            xInicial += xAutilizar;
        }
        if(estaSaltando) {
            if(yInicial == 270) { // si el triceraptop esta en el suelo
                sube = true;
                yAutilizar = -2;
                baja = false;
            }
            if(yInicial == 150) { // si el triceraptop esta en el salto
                baja = true;
                yAutilizar = 2;
                sube = false;
            }
            
            if(sube) 
                yInicial += yAutilizar;
            
            if(baja) {
                yInicial += yAutilizar;
                if(yInicial == 270) // si el triceraptop llego al suelo
                    estaSaltando = false;
            }
        }
    }
    
    public void paint(Graphics2D g2d) {
        ImageIcon dinoTricera = new ImageIcon(getClass().getResource("/Multimedia/triceratops.png"));
        g2d.drawImage(dinoTricera.getImage(), xInicial, yInicial, anchoDinosaurio, altoDinosaurio, null);
    }
    
    public void activarSalto(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP) 
            estaSaltando = true;
    }
    
    public Area getBounds() {
        Rectangle forma1 = new Rectangle(xInicial, yInicial, 95, 62);
        dinosaurio = new Area(forma1);
        
        Ellipse2D forma2 = new Ellipse2D.Double(xInicial, yInicial + 28, 48, 48);
        patasTraseras = new Area(forma2);
        
        Ellipse2D forma3 = new Ellipse2D.Double(xInicial + 73, yInicial + 39, 38, 38);
        patasDelanteras = new Area(forma3);
        
        tricera = dinosaurio;
        tricera.add(dinosaurio);
        tricera.add(patasTraseras);
        tricera.add(patasDelanteras);
        
        return tricera;
    }
    
}
