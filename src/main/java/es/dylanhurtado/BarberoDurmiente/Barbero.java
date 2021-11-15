package es.dylanhurtado.BarberoDurmiente;

public class Barbero extends Thread {

    private Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    public void run() {
        while (true) {
            try {
                barberia.esperarCliente();
                //Corta Pelo
                Thread.sleep(5000);


                barberia.acabarCorte();
                //Descansa un poco
                Thread.sleep(5000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
