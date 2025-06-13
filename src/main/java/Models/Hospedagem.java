package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Hospedagem")
public class Hospedagem {
    @Id
    private int id;
    private String nome;
    private int id_estado;
    private int id_cidade;
    private String localizacao;
    private int id_proprietario;

    public Hospedagem(int id, String nome, int id_estado , int id_cidade, String localizacao, int id_proprietario) {
        this.id = id;
        this.nome = nome;
        this.id_estado = id_estado;
        this.id_cidade = id_cidade;
        this.localizacao = localizacao;
        this.id_proprietario =  id_proprietario;

    }

}
