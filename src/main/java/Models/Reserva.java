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
    private int dia_entrada;
    private int mes_entrada;
    private int dia_saida;
    private int mes_saida;
    private int id;
    public Reserva (int id_cliente, int id_hospedagem, int dia_entrada,int mes_entrada, int dia_saida, int mes_saida, int id)
    {
        this.id_cliente = id_cliente;
        this.id_hospedagem = id_hospedagem;
        this.dia_entrada = dia_entrada;
        this.mes_entrada = mes_entrada;
        this.mes_saida = dia_saida;
        this.dia_saida = mes_saida;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public int getId_hospedagem() {
        return id_hospedagem;
    }

    public int getDia_entrada() {
        return dia_entrada;
    }

    public int getMes_entrada() {
        return mes_entrada;
    }

    public int getDia_saida() {
        return dia_saida;
    }

    public int getMes_saida() {
        return mes_saida;
    }
}
