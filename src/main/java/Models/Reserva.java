package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservas")
public class Reserva {
    @Id
    private int id_cliente;
    private int id_hospedagem;
    private int[] data_entrada = new int[3];
    private int[] data_saida = new int[3];

    public Reserva (int id_cliente, int id_hospedagem, int[] data_entrada, int[] data_saida)
    {
        this.id_cliente = id_cliente;
        this.id_hospedagem = id_hospedagem;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
    }

}
