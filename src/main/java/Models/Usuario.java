package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    private int id;
    private String nome;
    private String email;
    private String senha;



    public Usuario(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public Usuario() {}
    @Override
    public String toString() {
        return "Usuario " +
                "id: " + id +
                ", nome: " + nome + '\n' +
                ", email: " + email + '\n' +
                ", senha: " + senha + '\n';
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
