package es.dylanhurtado.BarberoDurmiente;

public class Main {
    final static int NUM_SILLAS = 5;
    final static int NUM_CLIENTES = 10;

    public static void main(String[] args){

        //Barberia
        Barberia barberia = new Barberia(NUM_SILLAS);

        //Barbero
        Barbero barbero = new Barbero(barberia);
        barbero.start();

        //Clientes
        Cliente[] Clientes = new Cliente[10];
        for (int i = 0; i < NUM_CLIENTES; i++) {
            Clientes[i] = new Cliente(i,barberia);
            Clientes[i].start();
        }

    }
}
