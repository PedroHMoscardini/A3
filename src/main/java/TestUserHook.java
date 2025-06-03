import Models.Proprietario;
import Models.Usuario;
import jakarta.persistence.EntityManager;

public class TestUserHook {
    public TestUserHook(EntityManager em) {

        int searchId = 1;

        Usuario usuario = em.find(Proprietario.class, searchId);


        System.out.printf(usuario.toString());

    }
}
