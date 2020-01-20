package archivos;

import java.util.Calendar;
import java.util.Random;

class Alumno extends Thread {
    private String nombre;
    private String fecha;

    Alumno(String name) {
        this.nombre = name;
    }

    @Override
    public void run() {
            Escribir_Ficheros ef=new Escribir_Ficheros();
            Leer_Fichero lf =new Leer_Fichero();
            Pila a= new Pila();
            lf.llenarp(a);
            Random random=new Random();
            try {
            int valor =random.nextInt(a.nroelem())+1;
            int valor2=random.nextInt(a.nroelem())+1;
            valor=(valor+valor2-1)/2;
            String aux=a.elim_i(valor);
            String cadena[]=aux.split(" ");
            if(cadena[3].equals("0")){
                this.fecha = cadena[1]+"/"+(Integer.parseInt(cadena[0])+1)+"/"+2020+" - "+cadena[2]+":"+cadena[3]+"0 hrs";
            }else{
                this.fecha = cadena[1]+"/"+(Integer.parseInt(cadena[0])+1)+"/"+2020+" - "+cadena[2]+":"+cadena[3]+" hrs";
            }
            System.out.println("¡Hola "+this.nombre+"!, tu fecha de inscripcion es el: "+this.fecha);
            ef.escribirp(a);
        } catch (Exception e) {
            System.out.println("Ups, algo salio mal intenta de nuevo por favor "+this.nombre);
        }
    }
}