import Models.Usuario;
import jakarta.persistence.EntityManager;

import java.util.Scanner;


public class MenuTemp {
    Usuario loged = null;
    public MenuTemp(EntityManager em){
        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.print("Bem vindo ");
            if(loged!=null){
                System.out.print(loged.getNome());
            }
            System.out.println();
            System.out.println("O que deseja fazer?");

            System.out.println("[1] Alugar Hospedagem");
            System.out.println("[2] Registar Hospedagem para Aluguel");
            System.out.println("[3] Registro");
            System.out.println("[4] Login");
            System.out.println("[5] Gerenciar Hospedagens");

            System.out.println("[0] Sair");
            int selected = scan.nextInt();
            switch (selected) {
                case 1 -> {
                    System.out.println("opção não implementada");
                }
                case 2 -> {
                    new CreateHospedagem(loged,em);
                }
                case 3 -> {
                    new Register(em);
                }
                case 4 -> {
                    Login login = new Login(em);
                    loged = login.getUser();
                }
                case 5 -> {
                    System.out.println("opção não implementada");
                }

                case 0 -> {
                    System.exit(0);
                }

                default -> {
                    System.out.println("digite uma opção valida");
                }
            }


        }

    }
}
