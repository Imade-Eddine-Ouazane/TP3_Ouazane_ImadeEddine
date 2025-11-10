package ma.emsi.ouazane.tp3.llm;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class GuideTouristiqueClient implements GuideTouristique {

    private GuideTouristique guide;

    public GuideTouristiqueClient() {
        String apiKey = System.getenv("GEMINI_API_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException("Clé API GEMINI_KEY manquante !");
        }

        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.5)
                .build();
        guide = AiServices.builder(GuideTouristique.class)
                .chatModel(model)
                .build();
    }

    public String ask(String lieu) {
        return guide.ask(lieu);
    }
}