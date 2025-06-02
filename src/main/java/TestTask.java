import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import Models.Usuario;

import java.util.Scanner;
/*
 - Login
 - Usuarios
 - Verificar disponibilidade
 - Registro de Hospedagem



 */
public class TestTask {
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        emf = Persistence.createEntityManagerFactory("PU");
        EntityManager em = emf.createEntityManager();
        new Register(em);


    }
}