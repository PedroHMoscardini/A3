import jakarta.persistence.EntityManager;
import Models.Usuario;
import java.util.Objects;
import java.util.Scanner;

public class Register {


    public Register(EntityManager em){
        long count;
        String password;
        String passwordConfirmation;
        String email;
        Scanner scan = new Scanner(System.in);
        System.out.println(" -=- Registrar Usuario -=-");
        System.out.print("Digite seu nome:");
        String name = scan.nextLine();
        do {
            System.out.print("Enter email: ");
            email = scan.nextLine();
            count = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.email = :email", Long.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if (count > 0) {    
                System.out.println("esse email já está em uso!");
            }else if(!email.contains("@yahoo.com.br") && !email.contains("@gmail.com") && !email.contains("@gmail.com")){
                System.out.println("Insira um email valido");
                count+=1;
            }
        }while(count>0);
        do{
            System.out.println("insira sua senha:");
            password = scan.nextLine();
            System.out.println("confirme sua senha:");
            passwordConfirmation = scan.nextLine();
        }while(!Objects.equals(password, passwordConfirmation));
        Integer maxId = em.createQuery("SELECT MAX(u.id) FROM Usuario u", Integer.class).getSingleResult();
        int newId = (maxId == null) ? 1 : maxId + 1;
        Usuario newUser = new Usuario(newId, name, email, password);

        em.getTransaction().begin();
        em.persist(newUser);
        em.getTransaction().commit();

    }
}