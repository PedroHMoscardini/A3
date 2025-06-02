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

        do {
            System.out.println("insira seu telefone:");
            telefone = scan.next();
            count = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.telefone = :telefone", Long.class)
                    .setParameter("telefone", telefone)
                    .getSingleResult();
            if (count > 0) {
                System.out.println("esse numero já está em uso!");}
        }while(count>0);



        //Data de Nascimento
        System.out.println("insira sua data de nascimento:");
        dataNascimento = scan.next();

        //CPF
        do {
            System.out.println("insira seu CPF:");
            cpf = scan.next();
            count = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.cpf = :cpf", Long.class)
                    .setParameter("cpf", cpf)
                    .getSingleResult();
            if (count > 0) {
                System.out.println("esse CPF já está em uso!");}
        }while(count>0);



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
        String temp = scan.nextLine();
        if (temp.toLowerCase().contains("c")){
            Cliente newUser = new Cliente(newId, name, email, password, telefone, dataNascimento, cpf);


            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        } else if(temp.toLowerCase().contains("p")){


            Proprietario newUser = new Proprietario(newId, name, email, password, telefone, dataNascimento, cpf);
            em.getTransaction().begin();
            em.persist(newUser);
            em.getTransaction().commit();
        }

    }
}