package archivos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

class Main {
    public static void main(String[] args) {
        /* Antes de ejecutar los hilos debes cargar los datos de las fechas al archivo.
        Esto solo se hace en caso de que no tengas el archivo. 
        Para esto debemos ejecutar el programa con solo las 2 lineas que estan abajo.
        Una vez echo esto comentamos de nuevo las 2 lineas y ejutamos los n-hilos.
        */

        Escribir_Ficheros ef=new Escribir_Ficheros();
        ef.escribir();
        // Cada vez que se llame a un correr un hilo genera una fecha de manera aleatoria y 
        // se le asignara al objeto Alumno en su atributo fecha

        /*Alumno[] hilos = new Alumno[10];
        for (int i = 0; i < hilos.length; i++) {
            Alumno a=new Alumno("Alumno "+i);
            a.start();
        } */
    
    }   
}

class Leer_Fichero {
    int c;

    public void leer() {
        //Imprime por consola cada linea del archivo
        try {
            FileReader entrada = new FileReader("holamundo2.txt");
            BufferedReader br =new BufferedReader(entrada); 
            String linea;
            while ((linea=br.readLine())!=null) { 
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
        // Copiamos todos los datos del archivo a una pila
        try {
            FileReader entrada = new FileReader("holamundo2.txt"); // Buscara un archivo que se encuentre en la misma ruta donde se encuentre el archivo.
            BufferedReader br =new BufferedReader(entrada); 
            String linea;
            while ((linea=br.readLine())!=null) {
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
        // Escribimos los datos que estaban en un pila al archivo nuevamente,vaciamos los datos a otra pila que se llene en el archivo en el mismo orden que se escribieron
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
        /* Escribe en Archivo.
        
        Esta funcion se ejecutara una sola vez para generar todas las posibles fechas de inscripcion separadas por rangos de 30 min en 3 dias.
        Cada linea del archivo contendra MES DIA HORA MINUTO, (Los meses en java empiezan en 0)
        */
        Calendar calendario = Calendar.getInstance();
        try {
            FileWriter fw = new FileWriter("holamundo2.txt"); // Si solo colocas el nombre del archivo se creara en la ruta donde se este ejecutando el programa.
            
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