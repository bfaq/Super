package com.usco.springboot.app.dto;

public class Mensaje {
		  
       private String mensaje;

	    public Mensaje(String mensaje) {
	        this.mensaje = mensaje;
	    }

	    public String getMensaje() {
	        return mensaje;
	    }

	    public void setMensaje(String mensaje) {
	        this.mensaje = mensaje;
	    }
}
