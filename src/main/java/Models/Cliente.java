package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
@Entity

public class Cliente extends Usuario{
    private String telefone;
    private String dataNascimento ;
    private String cpf;
    private ArrayList historico;
    private int nota = 0;


    public Cliente(int id, String nome, String email, String senha, String telefone, String dataNascimento, String cpf){
        super(id, nome, email, senha);
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
    }
    public Cliente(){
        super();
    }
    public String toString() {
        return "Proprietario: \n" +
                "id: " + getId() + '\n' +
                "nome: " + getNome() + '\n' +
                "email: " + getEmail() + '\n' +
                "telefone: '" + telefone + '\n' +
                ", dataNascimento: " + dataNascimento + '\n' +
                ", cpf: " + cpf + '\n' +
                ", historico: " + historico + '\n' +
                ", nota: " + nota;
    }
}
