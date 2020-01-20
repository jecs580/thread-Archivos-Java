package archivos;

import java.util.Scanner;

class Pila {
    private int max = 80;
    private String cadena[] = new String[max + 1];
    private int tope;

    Pila() {
        tope = 0;
    }
    public int nroelem(){
        return this.tope;
    }
    public boolean es_vacia() {
        if (tope == 0) {
            return true;
        }
        return false;
    }

    public boolean es_llena() {
        if (tope == max) {
            return true;
        }
        return false;
    }

    public void adicionar(String str) {
        if (!this.es_llena()) {
            tope++;
            cadena[tope] = str;
        } else {
            System.out.println("La pila esta llena");
        }
    }

    public String eliminar() {
        String str = "";
        if (!this.es_vacia()) {
            str = cadena[tope];
            tope = tope - 1;
        } else {
            //System.out.println("La pila esta vacia");
        }
        return str;
    }

    void mostrar() {
        if (!this.es_vacia()) {
            String str;
            Pila aux = new Pila();
            while (!this.es_vacia()) {
                str = this.eliminar();
                aux.adicionar(str);   
                System.out.println("[" + str + "]");
            }
            this.vaciar(aux);
        }
    }

    void vaciar(Pila z) {
        while (!z.es_vacia()) {
            this.adicionar(z.eliminar());
        }
    }

    void llenar(int n) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.println("Ingresa una cadena");
            String str = sc.next();
            this.adicionar(str);
        }
        sc.close();
    }

    void balance(int n) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            boolean valor = evaluar(str.toCharArray());
            if (valor) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
        sc.close();
    }
    public String elim_i(int i) {
        // Ejercicio para eliminaar el elemento i de una Pila
        Pila W = new Pila();
        int max = this.nroelem() - i;
        for (int j = 0; j < max; j++) {
            String k = this.eliminar();
            W.adicionar(k);
        }
        String aux=this.eliminar();
        W.vaciar(this);
        this.vaciar(W);
        return aux;
    }
    boolean evaluar(char[] strv) {
        for (int i = 0; i < strv.length; i++) {
            if (strv[i] == '[' || strv[i] == '(') {
                this.adicionar(String.valueOf(strv[i]));
            }
                if (strv[i] == ')' && !this.eliminar().equals("(")) {
                    return false;
                }
                if (strv[i] == ']' && !this.eliminar().equals("[")) {
                    return false;
                }
        }
        if (this.es_vacia()) {
            return true;
        } else {
            return false;
        }
    }
}