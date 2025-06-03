import Models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.Objects;
import java.util.Scanner;

public class Login {
    Scanner scan = new Scanner(System.in);
    Usuario user = null;

    public Login(EntityManager em){
        String senhaInserida;
        do {
            System.out.println("Insira seu Email:");
            String email = scan.nextLine();
            try{
                this.user = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class).setParameter("email",email).getSingleResult();
            } catch (NoResultException e){
                System.out.println("Email não registrado");
            }
        }while (user == null);
        System.out.println("Usuario (" + user.getNome() + ")");

        do{
            System.out.println("insira sua senha:");
             senhaInserida= scan.nextLine();

             if(Objects.equals(senhaInserida,user.getSenha())){
                 System.out.println("senha correta!");
             } else {
                 System.out.println("senha incorreta! \n Deseja tentar denovo?");
                 String temp = scan.nextLine();
                 if(temp.toLowerCase().contains("n"))
                 {
                     user = null;
                     break;

                 }
                 ;}
        }while(!Objects.equals(senhaInserida, user.getSenha()));



    }

    public Usuario getUser() {
        return user;
    }
}
