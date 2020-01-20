package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;

class Main {
    public static void main(String[] args) {
        Escribir_Ficheros ef=new Escribir_Ficheros();
        Alumno a1=new Alumno("jorge");
        Alumno a2=new Alumno("carlos");
       
        a1.start();
        a2.start();
        //ef.escribir();
        // Pila a=new Pila();
        // lf.llenarp(a);
        // a.mostrar();
        // int valor=random.nextInt(a.nroelem())+1; // Numeros entre 1-3
        // System.out.println("Valor random: "+valor);
        // System.out.println(a.nroelem());
        // System.out.println("Valor eliminado: "+a.elim_i(valor));
        // ef.escribirp(a);
    }
    
}

class Leer_Fichero {
    int c;

    public void leer() {
        try {
            FileReader entrada = new FileReader("holamundo2.txt"); // Buscara un archivo que se encuentre en la misma ruta donde se encuentre el archivo.
            BufferedReader br =new BufferedReader(entrada); // Mandamos una instancia de tipo Reader que es el archivo.
            // Almacenamos en una memoria interna que nos servira para poder leer de manera mas eficiente el archivo.
            String linea;
            while ((linea=br.readLine())!=null) { //readLine leera un linea hasta encontrar un \n o \r en el texto. Si no encuentra ninguno de estos 2 devolvera null y sabremos q es el fin del archivo
                System.out.println(linea);
                c++;
            }
            System.out.println("hay:"+c);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void llenarp(Pila a){
        try {
            FileReader entrada = new FileReader("holamundo2.txt"); // Buscara un archivo que se encuentre en la misma ruta donde se encuentre el archivo.
            BufferedReader br =new BufferedReader(entrada); // Mandamos una instancia de tipo Reader que es el archivo.
            // Almacenamos en una memoria interna que nos servira para poder leer de manera mas eficiente el archivo.
            String linea;
            while ((linea=br.readLine())!=null) { //readLine leera un linea hasta encontrar un \n o \r en el texto. Si no encuentra ninguno de estos 2 devolvera null y sabremos q es el fin del archivo
                a.adicionar(linea);
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}
class Escribir_Ficheros{
    public void escribirp(Pila a){
        Pila w=new Pila();
        w.vaciar(a);
        try {
            FileWriter fw = new FileWriter("holamundo2.txt");
            while(!w.es_vacia()){
                fw.write(w.eliminar()+"\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void escribir(){
        Calendar calendario = Calendar.getInstance();
        try {
            // Escribira en un fichero existen o lo creara en caso de que no exista.
            FileWriter fw = new FileWriter("holamundo2.txt"); // Si solo colocas el nombre del archivo se creara en la ruta donde se este ejecutando el programa.
            // (char)97 Para obtener simbolo de un determino orden de ascii.
            
            int k = 0;
            calendario.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                    calendario.get(Calendar.DAY_OF_MONTH) + 1, 8, 0);
            int arrival = calendario.get(Calendar.DAY_OF_MONTH) + 3;
            while (calendario.get(Calendar.DAY_OF_MONTH) < arrival) {
                if (k == 0) {
                    fw.write(calendario.get(Calendar.MONTH) + " " + calendario.get(Calendar.DAY_OF_MONTH) + " "
                            + calendario.get(Calendar.HOUR_OF_DAY) + " " + k+"\n");
                    k = 30;
                    calendario.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                            calendario.get(Calendar.DAY_OF_MONTH), (calendario.get(Calendar.HOUR_OF_DAY)), k);
                } else {
                    fw.write(calendario.get(Calendar.MONTH) + " " + calendario.get(Calendar.DAY_OF_MONTH) + " "
                            + calendario.get(Calendar.HOUR_OF_DAY) + " " + k+"\n");
                    calendario.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                            calendario.get(Calendar.DAY_OF_MONTH), (calendario.get(Calendar.HOUR_OF_DAY) + 1), k);
                    k = 0;
                }
                if (calendario.get(Calendar.HOUR_OF_DAY) > 19) {
                    calendario.set(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                            (calendario.get(Calendar.DAY_OF_MONTH) + 1), 8, 0);
                }
            }
            fw.close();

        } catch (IOException e) {
           System.out.println("No se pudo crear el archivo");
        }

    }
}