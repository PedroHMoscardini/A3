package Models;

import java.util.HashMap;
import java.util.Map;

public class StateData {

    public static final HashMap<String, String[]> Estados = new HashMap<>()  static {
        Estados.put("Acre (AC)", new String[]{
                "Rio Branco", "Cruzeiro do Sul", "Sena Madureira", "Tarauacá",
                "Feijó", "Brasiléia", "Xapuri", "Plácido de Castro",
                "Rodrigues Alves", "Manoel Urbano"
        });

        Estados.put("Amapá (AP)", new String[]{
                "Macapá", "Santana", "Laranjal do Jari", "Oiapoque",
                "Mazagão", "Porto Grande", "Tartarugalzinho",
                "Pedra Branca do Amapari", "Ferreira Gomes", "Calçoene"
        });

        Estados.put("Amazonas (AM)", new String[]{
                "Manaus", "Parintins", "Itacoatiara", "Manacapuru", "Coari",
                "Tefé", "Humaitá", "Tabatinga", "Maués", "Manicoré"
        });
    }


}


