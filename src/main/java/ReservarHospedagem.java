import Models.*;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ReservarHospedagem {
    Calendario calendario = new Calendario();
    int id_estado_desejado;
    int id_cidade_desejada;
    int id_hospedagem;
    int mes_entrada = -1;
    int dia_entrada = -1;
    int mes_saida = -1;
    int dia_saida = -1;
    Scanner scan = new Scanner(System.in);
    StateData statedata = new StateData();
    public ReservarHospedagem(EntityManager em, Usuario loged){
        System.out.println("em qual estado deseja se hospedar?");
        String estadotemp = scan.nextLine();
        do{
            int id_temp = 0;
            for (String estado : statedata.Estados.keySet()) {
                if(estado.toLowerCase().contains(estadotemp.toLowerCase())){
                    id_estado_desejado =id_temp;
                }
                id_temp+=1;
            }
            if(id_estado_desejado == -1){
                System.out.println("estado não encontrado, tente novamente");
            }

        }while (id_estado_desejado == -1);
        do{
            String[] cidades =statedata.Estados.get(statedata.Translate_id(id_estado_desejado));
            int id_temp = 0;
            System.out.println("em qual das cidades abaixo você deseja se hospedar?");
            System.out.println(Arrays.toString(cidades));
            String cidadetemp = scan.nextLine();
            for (String cidade : cidades) {

                if(cidade.equalsIgnoreCase(cidadetemp)){
                    id_cidade_desejada =id_temp;
                }
                id_temp++;
            }
            if(id_cidade_desejada == -1){
                System.out.println("cidade não encontrada, tente novamente");
            }

        }while(id_cidade_desejada == -1);
        List<Hospedagem> hospedagens = em.createQuery( """
                SELECT h FROM Hospedagem h WHERE h.id_estado = :estado AND h.id_cidade = :cidade 
                """, Hospedagem.class)
                .setParameter("estado", id_estado_desejado)
                .setParameter("cidade", id_cidade_desejada)
                .getResultList();
        if(hospedagens.isEmpty()){
            System.out.println("não há hospedagens no local");
            return;
        }else {
            System.out.println("Hospedagens disponíveis:");
            for (Hospedagem h : hospedagens) {
                System.out.printf("Nome da hospedagem: " + h.getNome() + "%n localização: " + h.getLocalizacao() + "%n");
            }
            System.out.println("qual hospedagem deseja reservar?");
            String hospedagemDesejada = scan.nextLine();
            for (Hospedagem h : hospedagens) {
                if(Objects.equals(hospedagemDesejada.toLowerCase(), h.getNome().toLowerCase())){
                    id_hospedagem = h.getId();
                }
            }

            }
        System.out.println("qual mês deseja fazer o check-in?");
        do {
            if (scan.hasNextInt()) {
                mes_entrada = scan.nextInt();
                if (mes_entrada <1 || mes_entrada>12){
                    mes_entrada = -1;
                }
            } else {
                mes_entrada = calendario.mesParaNumero(scan.nextLine().toLowerCase());

            }
            if(mes_entrada == -1){
                System.out.println("mes não indentificado");
            }
        }while (mes_entrada == -1);
        do{
            System.out.println("qual dia deseja fazer checkin");
            dia_entrada = scan.nextInt();
            if(dia_entrada <0 || dia_entrada > calendario.getMonthLength(mes_entrada)){
                System.out.println("dia selecionado não existe");
                dia_entrada = -1;
            }
        }while (dia_entrada == -1);
        System.out.println("qual mês deseja fazer o check-out?");
        do {
            if (scan.hasNextInt()) {
                mes_saida = scan.nextInt();
                if (mes_saida <1 || mes_saida>12){
                    mes_saida = -1;
                }
            } else {
                mes_saida = calendario.mesParaNumero(scan.nextLine().toLowerCase());

            }
            if(mes_saida == -1){
                System.out.println("mes não indentificado");
            }
        }while (mes_saida == -1);
        do{
            System.out.println("qual dia deseja fazer check-out");
            dia_saida = scan.nextInt();
            if(dia_saida <0 || dia_saida > calendario.getMonthLength(mes_entrada)){
                System.out.println("dia selecionado não existe");
            }
        }while (dia_saida == -1);
        Integer maxId = em.createQuery("SELECT MAX(u.id) FROM Reservas u", Integer.class).getSingleResult();
        int newId = (maxId == null) ? 1 : maxId + 1;
        Reserva newReserva = new Reserva(loged.getId(),id_hospedagem,dia_entrada,mes_entrada,dia_saida,mes_saida,newId);
        em.getTransaction().begin();
        em.persist(newReserva);
        em.getTransaction().commit();
    }
}
