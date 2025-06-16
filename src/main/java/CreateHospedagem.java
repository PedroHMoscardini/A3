import Models.*;
import jakarta.persistence.EntityManager;
import org.w3c.dom.Entity;

import javax.swing.plaf.nimbus.State;
import java.util.Arrays;
import java.util.Scanner;

public class CreateHospedagem {

    public CreateHospedagem(Usuario loged, EntityManager em) {
        if(loged.getClass() == Proprietario.class){

            StateData statedata = new StateData();
        int id;
        String nome;
        String localizacao;
        int id_proprietario;
        int id_estado = -1;
        int id_cidade = -1;
        int id_temp = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println(" -=- Registrar Hospedagem -=-");
        System.out.print("Digite o nome da  hospedagem:");
        nome = scan.nextLine();

        String temp = "";
        do{
            id_temp=0;
            System.out.println("em qual estado está sua hospedagem?");
            String estadotemp = scan.nextLine();
            for (String estado : statedata.Estados.keySet()) {
               if(estado.toLowerCase().contains(estadotemp.toLowerCase())){
                  id_estado =id_temp;
               }
                id_temp+=1;
            }
            if(id_estado == -1){
                System.out.println("estado não encontrado, tente novamente");
            }

        }while(id_estado == -1);
            do{
                String[] cidades =statedata.Estados.get(statedata.Translate_id(id_estado));
                id_temp=0;
                System.out.println("em qual das cidades abaixo está sua hospedagem?");
                System.out.println(Arrays.toString(cidades));
                String cidadetemp = scan.nextLine();
                for (String cidade : cidades) {

                    if(cidade.toLowerCase().contains(cidadetemp.toLowerCase())){
                        id_cidade =id_temp;
                    }
                    id_temp++;
                }
                if(id_cidade == -1){
                    System.out.println("cidade não encontrada, tente novamente");
                }

            }while(id_cidade == -1);


        do {

            System.out.print("Digite a localizacao da hospedagem:");
            localizacao = scan.nextLine();
            System.out.println("(" + localizacao + ") esta localização está correta?");
            temp = scan.nextLine();
        }while(!temp.toLowerCase().contains("s"));

        Integer maxId = em.createQuery("SELECT MAX(u.id) FROM Hospedagem u", Integer.class).getSingleResult();
        int newId = (maxId == null) ? 1 : maxId + 1;

        id_proprietario = loged.getId();

        Hospedagem newHospedagem = new Hospedagem(newId, nome, id_estado, id_cidade, localizacao, id_proprietario);
        em.getTransaction().begin();
        em.persist(newHospedagem);
        em.getTransaction().commit();}
        else {
            System.out.println("Logue em uma conta de Proprietario para registrar hospedagens!");
        }
    }
}
