import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.BaseResponse;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;

public class Main {

    public static void main(String[] args) {

        //Criação do objeto bot com as informações de acesso
        TelegramBot bot = TelegramBotAdapter.build("1036167275:AAFf0aVfpYqgkv1_NAxu1psKZXi7YwAczbI");

        //objeto responsável por receber as mensagens
        GetUpdatesResponse updatesResponse;
        //objeto responsável por gerenciar o envio de respostas
        SendResponse sendResponse;
        //objeto responsável por gerenciar o envio de ações do chat
        BaseResponse baseResponse;

        //controle de off-set, isto é, a partir deste ID será lido as mensagens pendentes na fila
        int m = 0;

        Localizacao localizacao = new Localizacao(null, null);
        Categoria categoria = new Categoria(null, null, null);
        Bem bem = new Bem(null, null,null, null, null);

        boolean controle_localizacao = false;
        boolean controle_categoria = false;
        boolean controle_bem = false;

        Gerenciador gerencia = new Gerenciador(bot);

        //loop infinito pode ser alterado por algum timer de intervalo curto
        while (true) {

            //executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
            updatesResponse = bot.execute(new GetUpdates().limit(100).offset(m));

            //lista de mensagens
            List<Update> updates = updatesResponse.updates();

            //análise de cada ação da mensagem
            for (Update update : updates) {

                String iniciaBot = update.message().text();
                if (iniciaBot.equals("/menu") || iniciaBot.equals("/start") || iniciaBot.equals("Oi")) {
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "" +
                            "########### BOT FÉ - MENU PRINCIPAL ###########\n" +
                            "1. Cadastrar localização\n" +
                            "2. Cadastrar categoria de bem\n" +
                            "3. Cadastrar bem\n" +
                            "4. Listar localizações\n" +
                            "5. Listar categorias\n" +
                            "6. Listar bens de uma localização\n" +
                            "7. Buscar bem por código\n" +
                            "8. Buscar bem por nome\n" +
                            "9. Buscar bem por descrição\n" +
                            "10. Movimentar bem\n" +
                            "11. Gerar relatório\n\n" +
                            "# Insira o número correspondente ao que deseja #"));
                }

                //atualização do off-set
                m = update.updateId() + 1;

                System.out.println("Recebendo mensagem:" + update.message().text());

                String respostaMenu = update.message().text();

//############################################################ 2 ESCOLHA ##################################################################

                if (respostaMenu.equals("1") || controle_localizacao) {
                    if(localizacao.getNome() == null) {
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "##### CADASTRAR LOCALIZAÇÃO #####\n"));
                        m = update.updateId() + 1;

                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o nome da localização: "));
                        m = update.updateId() + 1;

                        localizacao.setNome(update.message().text());
                        controle_localizacao = true;
                    }
                    else if (localizacao.getDescricao() == null) {
                        localizacao.setNome(update.message().text());

                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite a sua descrição: "));
                        localizacao.setDescricao(update.message().text());
                    }
                    else if (localizacao.getDescricao() != null) {
                        localizacao.setDescricao(update.message().text());
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "\nNome: " +
                                localizacao.getNome() + "\nDescrição: "+ localizacao.getDescricao()));
                        gerencia.getLocalizacoes().add(localizacao);
                        localizacao = new Localizacao(null, null);
                        controle_localizacao = false;
                    }
                }

//############################################################ 2 ESCOLHA ##################################################################

                if (respostaMenu.equals("2") || controle_categoria) {
                    if(categoria.getCodigo() == null) {
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "##### CADASTRAR CATEGORIA #####\n"));
                        m = update.updateId() + 1;

                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o código da categoria:"));
                        m = update.updateId() + 1;

                        categoria.setCodigo(update.message().text());
                        controle_categoria = true;
                    }
                    else if(categoria.getNome() == null) {
                        categoria.setCodigo(update.message().text());
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o nome da categoria:"));
                        m = update.updateId() + 1;

                        categoria.setNome(update.message().text());
                        controle_categoria = true;
                    }
                    else if (categoria.getDescricao() == null) {
                        categoria.setNome(update.message().text());

                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite a descrição da cateogira: "));
                        categoria.setDescricao(update.message().text());
                    }
                    else if (categoria.getDescricao() != null) {
                        categoria.setDescricao(update.message().text());
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "\nCódigo: "+ categoria.getCodigo() + "\nNome: " +
                                categoria.getNome() + "\nDescrição: "+ categoria.getDescricao()));
                        gerencia.getCategorias().add(categoria);
                        categoria = new Categoria(null, null, null);
                        controle_categoria = false;
                    }

                }

//############################################################ 2 ESCOLHA ##################################################################


                //envio de "Escrevendo" antes de enviar a resposta
                baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                //verificação de ação de chat foi enviada com sucesso
                System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

            }

        }

    }

}
