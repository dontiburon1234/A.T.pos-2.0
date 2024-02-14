package util;

import java.awt.Image;

import clases.Licencia;

public class G {
	
    private G() {}
    
    private static G ourInstance = new G();

    public String cajero;
    public int getIdCajero;
    public Licencia licenciaGlobal;
    public int idPreFacturaActual;
    public String documentoClienteTercero;
    public Image imgLogo;
    public int alto; // alto de la imagen del logo
    public int anchoPapel;
    public int pasoRenglon; // minimo 6
    public int sizeFont;
    public String defaultPort; // coloca el puerto por el cual está conectado el poleDisplay
    public boolean defaultPortFounf; // indica si encontró conectado y encendido el poleDisplay
    

    public synchronized static G getInstance() {
        if(ourInstance==null){
            ourInstance = new G();
        }
        return ourInstance;
    }
}
