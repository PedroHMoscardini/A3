import Models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.Objects;
import java.util.Scanner;

public class Login {

    public Login(EntityManager em){
        Scanner scan = new Scanner(System.in);
        Usuario user = null;
        String senhaInserida;
        do {
            System.out.println("Insira seu Email:");
            String email = scan.nextLine();
            try{
                user = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class).setParameter("email",email).getSingleResult();
            } catch (NoResultException e){
                System.out.println("Email não registrado");
            }
        }while (user == null);
        System.out.println("Usuario (" + user.getNome() + ")");
        System.out.println("insira sua senha:");
        do{
             senhaInserida= scan.nextLine();

             if(Objects.equals(senhaInserida,user.getSenha())){
                 System.out.printf("senha correta!");
             } else {
                 System.out.println("senha incorreta!");}
        }while(!Objects.equals(senhaInserida, user.getSenha()));



    }
}
