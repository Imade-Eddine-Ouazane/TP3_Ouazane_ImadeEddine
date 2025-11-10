package ma.emsi.ouazane.tp3.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class GuideTouristiqueClient implements GuideTouristique {

    private final ChatModel model;

    public GuideTouristiqueClient() {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Clé API GEMINI_KEY manquante !");
        }

        model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.5)
                .build();
    }

    @Override
    public String ask(String lieu) {
        String prompt = """
            Tu es un guide touristique.
            Donne les 2 principaux endroits à visiter dans la ville ou le pays demandé,
            ainsi que le prix moyen d’un repas dans la devise locale.
            Répond uniquement en JSON, au format exact :
            {
              "ville_ou_pays": "%s",
              "endroits_a_visiter": ["endroit 1", "endroit 2"],
              "prix_moyen_repas": "<prix> <devise>"
            }
            N'utilise pas Markdown.
            """.formatted(lieu);

        return model.chat(prompt);
    }
}