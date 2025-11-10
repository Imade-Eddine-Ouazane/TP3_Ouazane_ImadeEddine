package ma.emsi.ouazane.tp3.llm;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface GuideTouristique {

        @SystemMessage("""
        Tu es un guide touristique.
        Donne les {{nombre}} principaux endroits à visiter dans la ville ou le pays demandé,
        ainsi que le prix moyen d'un repas dans la devise locale.
        Répond uniquement en JSON, au format exact :
        {
          "ville_ou_pays": "<nom de la ville ou du pays>",
          "endroits_a_visiter": ["endroit 1", "endroit 2", ...],
          "prix_moyen_repas": "<prix> <devise>"
        }
        N'utilise pas Markdown.
    """)
        @UserMessage("{{lieu}}")
        String ask(@V("lieu") String lieu, @V("nombre") int nombre);}