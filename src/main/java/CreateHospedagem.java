import Models.Hospedagem;
import Models.Proprietario;
import Models.Usuario;
import jakarta.persistence.EntityManager;
import org.w3c.dom.Entity;

import java.util.Scanner;

public class CreateHospedagem {

    public CreateHospedagem(Usuario loged, EntityManager em) {
        if(loged.getClass() == Proprietario.class){
        int id;
        String nome;
        String localizacao;
        int id_proprietario;
        Scanner scan = new Scanner(System.in);
        System.out.println(" -=- Registrar Hospedagem -=-");
        System.out.print("Digite o nome da  hospedagem:");
        nome = scan.nextLine();

        String temp = "";
        do {

            System.out.print("Digite a localizacao da hospedagem:");
            localizacao = scan.nextLine();
            System.out.println("(" + localizacao + ") esta localização está correta?");
            temp = scan.nextLine();
        }while(!temp.toLowerCase().contains("s"));

        Integer maxId = em.createQuery("SELECT MAX(u.id) FROM Hospedagem u", Integer.class).getSingleResult();
        int newId = (maxId == null) ? 1 : maxId + 1;

        id_proprietario = loged.getId();

        Hospedagem newHospedagem = new Hospedagem(newId, nome, localizacao, id_proprietario);
        em.getTransaction().begin();
        em.persist(newHospedagem);
        em.getTransaction().commit();}
        else {
            System.out.println("Logue em uma conta de Proprietario para registrar hospedagens!");
        }
    }
}
