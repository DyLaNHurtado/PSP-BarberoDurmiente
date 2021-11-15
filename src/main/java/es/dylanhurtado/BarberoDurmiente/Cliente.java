package es.dylanhurtado.BarberoDurmiente;

public class Cliente extends Thread {
    private int idCliente;
    private Barberia barberia;
    private boolean cortePelo  = false;

    public Cliente(int idCliente, Barberia barberia) {
        this.barberia = barberia;
        this.idCliente = idCliente;
    }

    public void run() {
        // Vamos entrando, si lo queremos limitar ponemos las veces o solo una vez...
        while (true){
        try {

            Thread.sleep(2000);
            cortePelo  = barberia.entrar(idCliente);

            if (cortePelo){
                // Espera hasta que crezca el pelo
                Thread.sleep(25000);
            }else {
                //Espera y lo vuelve a intentar
                Thread.sleep(4000);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        }
    }

}
