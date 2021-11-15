package es.dylanhurtado.BarberoDurmiente;

public class Barberia {
    private int nSillas;
    private int nSillasOcupadas;
    private boolean sillonOcupado;
    private boolean finCorte;
    private boolean barberoDormido;

    public Barberia(int nSillas) {
        this.nSillas = nSillas;
        nSillasOcupadas = 0;
        sillonOcupado = false;
        finCorte = false;
        barberoDormido = false;
    }

    public synchronized boolean entrar(int idCliente) throws InterruptedException {
        if (nSillasOcupadas == nSillas) {
            // Si no hay sillas libres, me voy sin cortar el pelo
            System.out.println("---- El cliente " + idCliente
                    + " se va sin cortarse el pelo");
            return false;
        } else {
            // Me quedo esperando si la silla del barbero está
            // ocupada
            nSillasOcupadas++;
            System.out.println("---- El cliente " + idCliente
                    + " se sienta en la silla de espera");
            while (sillonOcupado) {
                wait();
            }
            nSillasOcupadas--;

            //Me siento en el sillon del barbero
            sillonOcupado = true;
            finCorte = false;

            // Si el barbero está dormido le despierto
            if (barberoDormido) {
                System.out.println("---- El cliente " + idCliente
                        + " despierta al barbero");
                notifyAll();
            }

            // Espero a que me corte el pelo
            System.out.println("---- El cliente " + idCliente
                    + " en la silla de barbero");
            while (!finCorte) {
                wait();
            }

        }
        //Descupamos el sillon
        sillonOcupado = false;

        // Que pase el siguiente
        notifyAll();

        System.out.println("---- El cliente " + idCliente
                + " se va con el pelo cortado");
        return true;

    }


    public synchronized void esperarCliente() throws InterruptedException {

        barberoDormido = true;
        while (!sillonOcupado) {
            System.out.println("++++ Barbero esperando cliente");
            wait();
        }
        barberoDormido = false;
        System.out.println("++++ Barbero cortando el pelo");

    }

    public synchronized void acabarCorte() {

        finCorte = true;
        System.out.println("++++ Barbero termina de cortar el pelo");
        notifyAll();

    }
}
