import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

import java.util.ArrayList;
import java.util.List;

public class Gerenciador {

    private TelegramBot bot;
    private ArrayList<Localizacao> localizacoes = new ArrayList<Localizacao>();
    private ArrayList<Categoria> categorias = new ArrayList<Categoria>();

    public Gerenciador(TelegramBot bot) {
        this.bot = bot;
    }

    public ArrayList<Localizacao> getLocalizacoes() {
        return localizacoes;
    }

    public ArrayList<Categoria> getCategorias() {
        return categorias;
    }

}
