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

    public void cadastrarLocalizacao() {

        int m = 0;
        //int aux = 0;
            
        GetUpdatesResponse updatesResponse = (this.bot).execute(new GetUpdates().limit(100).offset(m));
        List<Update> updates = updatesResponse.updates();
        int aux = 0;
        for (Update update : updates) {

            if(aux == 0){
                SendResponse sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "" +
                        "### BOT FÉ - CADASTRAR LOCALIZAÇÃO ###\n" +
                        "1. Insira o nome do local \n"));
                aux++;
                String nomeLocal = update.editedMessage().text();
                m = update.updateId() + 1;
                System.out.println("nome local:" + nomeLocal);


            }
            if(aux == 1){
                String nomeLocal = update.message().text();
                System.out.println("nome local:" + nomeLocal);
            }
            if(aux == 0){
                SendResponse sendResponse2 = bot.execute(new SendMessage(update.message().chat().id(), "" +
                        "2. Insira a descrição do local \n"));

                m = update.updateId() + 1;
                String descricaoLocal = update.message().text();

                System.out.println("descricao local" + descricaoLocal);
            }
        }

//        Localizacao localizacao = new Localizacao(nome, descricao);
//        this.localizacoes.add(localizacao);
////        return this.localizacoes;
    }

//    public void cadastrarCategoria(Categoria categoria) {
//        this.categorias.add(categoria);
////        return categorias

}
