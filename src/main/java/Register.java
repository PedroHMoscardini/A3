import Models.Cliente;
import Models.Proprietario;
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
        String telefone;
        String dataNascimento;
        String cpf;
        Scanner scan = new Scanner(System.in);
        System.out.println(" -=- Registrar Usuario -=-");
        System.out.print("Digite seu nome:");
        String name = scan.nextLine();


        // Verificação email
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

        //telefone
        System.out.println("insira seu telefone:");
        telefone = scan.next();

        //telefone
        System.out.println("insira sua data de nascimento:");
        dataNascimento = scan.next();

        //telefone
        System.out.println("insira seu cpf:");
        cpf = scan.next();


        //Verificação senha
        do{
            scan.nextLine();
            System.out.println("insira sua senha:");
            password = scan.nextLine();
            System.out.println("confirme sua senha:");
            passwordConfirmation = scan.nextLine();
        }while(!Objects.equals(password, passwordConfirmation));
        Integer maxId = em.createQuery("SELECT MAX(u.id) FROM Usuario u", Integer.class).getSingleResult();
        int newId = (maxId == null) ? 1 : maxId + 1;


        //Cliente ou Proprietario
        System.out.println("você é cliente ou proprietario?");
        if (scan.hasNext("c")){
            Cliente newUser = new Cliente(newId, name, email, password, telefone, dataNascimento, cpf);


            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        } else if(scan.hasNext("p")){


            Proprietario newUser = new Proprietario(newId, name, email, password, telefone, dataNascimento, cpf);
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        }

    }
}