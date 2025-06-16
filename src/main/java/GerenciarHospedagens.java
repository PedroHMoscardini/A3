import Models.Cliente;
import Models.Hospedagem;
import Models.Reserva;
import Models.Usuario;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Scanner;

public class GerenciarHospedagens {
    Scanner scan = new Scanner(System.in);
    List<Reserva> reservas;
    public GerenciarHospedagens(Usuario loged, EntityManager em){
        while (true){
        List<Hospedagem> hospedagens = em.createQuery( """
                SELECT h FROM Hospedagem h WHERE h.id_proprietario = :id_proprietario 
                """, Hospedagem.class)
                .setParameter("id_proprietario",loged.getId())
                .getResultList();
        if(hospedagens.isEmpty()){
            System.out.println("você não possui hospedagens");
            return;
        }else {
            System.out.println("Suas hospedagens:");
            for (Hospedagem h : hospedagens) {
                System.out.printf("Nome da hospedagem: " + h.getNome() + "%n localização: " + h.getLocalizacao() + "%n id:" + h.getId() + "%n");
            }
        }
        System.out.println("o que deseja fazer?");
        System.out.println("[1] verificar reservas");
        System.out.println("[2] remover reserva");
        System.out.println("[3] editar hospedagem");
        System.out.println("[4] deletar hospedagem");
        System.out.println("[5] retornar");
        int choice = scan.nextInt();
        switch (choice){
            case 1:{
                for(Hospedagem h : hospedagens)
                {
                    this.reservas = em.createQuery( """
                              SELECT h FROM Reservas h WHERE h.id_hospedagem = :id_hospedagem 
                               """, Reserva.class)
                            .setParameter("id_hospedagem",h.getId())
                            .getResultList();
                }
                if(reservas.isEmpty()){
                    System.out.println("você não possui hospedagens");
                    return;
                }else {
                    System.out.println("Suas reservas:");
                    for (Reserva r : reservas) {
                        Hospedagem reservada;
                        reservada = em.find(Hospedagem.class, r.getId_hospedagem());
                        Usuario cliente;
                        cliente = em.find(Usuario.class, r.getId_cliente());
                        System.out.printf("hospedagem: " + reservada.getNome() + "%n localização: " + reservada.getLocalizacao() + "%n Cliente: " + cliente.getNome() + "%nDia de check-in: " + r.getDia_entrada() + "/" + r.getMes_entrada() + "%nDia de check-out: " + r.getDia_saida() + "/" + r.getMes_saida() + "%n id da reserva: " +r.getId() + "%n" );
                    }
                }
            }
            case 2:{
                System.out.println("Qual o id da reserva que deseja remover?");
                int deleteID = scan.nextInt();
                Reserva deleteReserva = em.find(Reserva.class, deleteID);
                Hospedagem reservada;
                reservada = em.find(Hospedagem.class, deleteReserva.getId_hospedagem());
                Usuario cliente;
                cliente = em.find(Usuario.class, deleteReserva.getId_cliente());
                System.out.printf("hospedagem: " + reservada.getNome() + "%n localização: " + reservada.getLocalizacao() + "%n Cliente: " + cliente.getNome() + "%nDia de check-in: " + deleteReserva.getDia_entrada() + "/" + deleteReserva.getMes_entrada() + "%nDia de check-out: " + deleteReserva.getDia_saida() + "/" + deleteReserva.getMes_saida() + "%n id da reserva: " +deleteReserva.getId() + "%n");
                System.out.println("essa é a reserva que deseja deletar?");
                if(scan.nextLine().toLowerCase().contains("s")){
                    em.getTransaction().begin();
                    em.remove(deleteReserva);
                    em.getTransaction().commit();
                } else{
                    System.out.println("remoção cancelada");
                }
            }
            case 3:{
                System.out.println("Qual o id da hospedagem que deseja editar?");
                int editID = scan.nextInt();
                scan.nextLine();
                    Hospedagem editHospedagem = em.find(Hospedagem.class, editID);
                System.out.printf("Nome da hospedagem: " + editHospedagem.getNome() + "%n localização: " + editHospedagem.getLocalizacao() + "%n id:" + editHospedagem.getId()+ "%n");
                System.out.println("essa hospedagem que deseja editar?");
                if(scan.nextLine().toLowerCase().contains("s")){
                    System.out.println("digite o nome da hospedagem:");
                    editHospedagem.setNome(scan.nextLine());
                    System.out.println("digite a localização hospedagem:");
                    editHospedagem.setLocalizacao(scan.nextLine());
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                }
            }
            case 4:{
                System.out.println("Qual o id da hospedagem que deseja remover?");
                int deleteID = scan.nextInt();
                scan.nextLine();
                Hospedagem deleteHospedagem = em.find(Hospedagem.class, deleteID);
                System.out.printf("Nome da hospedagem: " + deleteHospedagem.getNome() + "%n localização: " + deleteHospedagem.getLocalizacao() + "%n id:" + deleteHospedagem.getId()+ "%n");
                System.out.println("essa hospedagem que deseja deletar?");
                if(scan.nextLine().toLowerCase().contains("s")){
                    em.getTransaction().begin();
                    em.remove(deleteHospedagem);
                    em.getTransaction().commit();
                }
            }
            case 5:{
                return;
         }

        }
        }

    }
}
