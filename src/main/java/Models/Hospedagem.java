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
    private String localizacao;
    private int id_proprietario;

    public Hospedagem(int id, String nome, String localizacao, int id_proprietario) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.id_proprietario =  id_proprietario;

    }

}
