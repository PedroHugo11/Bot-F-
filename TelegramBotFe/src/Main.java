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
        String nome_localizacao = null;
        String nome_categoria = null;

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

                if (iniciaBot.equals("Olá")) {
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "" +
                            "Olá, sou o Bot Fé! Para apresentar o menu de opções digite /menu"));
                }

                if (iniciaBot.equals("/menu") || iniciaBot.equals("/start") || iniciaBot.equals("Oi")) {
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "" +
                            "########### BOT FÉ - MENU PRINCIPAL ###########\n" +
                            "1. /cadastrar_localizacao\n" +
                            "2. /cadastrar_categoria\n" +
                            "3. /cadastrar_bem\n" +
                            "4. /listar_localizacoes\n" +
                            "5. /listar_categorias\n" +
                            "6. /listar_bens_de_localizacao\n" +
                            "7. /buscar_bem_por_codigo\n" +
                            "8. /buscar_bem_por_nome\n" +
                            "9. /buscar_bem_por_descricao\n" +
                            "10. /movimentar_bem\n" +
                            "11. /gerar_relatorio\n\n" +
                            "# Insira o comando correspondente ao que deseja #"));
                }

                m = update.updateId() + 1;
                System.out.println("Recebendo mensagem:" + update.message().text());
                String respostaMenu = update.message().text();

                //#################### 1 ESCOLHA ########################

                if (respostaMenu.equals("/cadastrar_localizacao") || controle_localizacao) {
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

                //#################### 2 ESCOLHA ########################
                else if (respostaMenu.equals("/cadastrar_categoria") || controle_categoria) {
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

                //#################### 3 ESCOLHA ########################
                if (respostaMenu.equals("/cadastrar_bem") || controle_bem) {
                    if(bem.getCodigo() == null) {
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "##### CADASTRAR BEM #####\n"));
                        m = update.updateId() + 1;

                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o código do bem:"));
                        m = update.updateId() + 1;

                        bem.setCodigo(update.message().text());
                        controle_bem = true;
                    }
                    else if(bem.getNome() == null) {
                        bem.setCodigo(update.message().text());
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o nome do bem:"));
                        m = update.updateId() + 1;

                        bem.setNome(update.message().text());
                        controle_bem = true;
                    }
                    else if (bem.getDescricao() == null) {
                        bem.setNome(update.message().text());

                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite a descrição do bem: "));
                        bem.setDescricao(update.message().text());
                    }
                    else if (nome_localizacao == null) {
                        bem.setDescricao(update.message().text());

                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o nome da localização do bem: "));

                        nome_localizacao = update.message().text();
                    }
                    else if (nome_localizacao != "1") {
                        nome_localizacao = update.message().text();
                        localizacao = gerencia.buscaLocalizacaoPorNome(nome_localizacao);

                        if(localizacao != null){
                            bem.setLocalizacao(localizacao);
                            sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o nome da categoria do bem: "));
                            nome_categoria = update.message().text();
                            nome_localizacao = "1";
                        }else {
                            sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "" +
                                    "Localização inválida! Tente novamente."));
                            nome_localizacao = update.message().text();
                            localizacao = gerencia.buscaLocalizacaoPorNome(nome_localizacao);

                        }
                    }
                    else if (nome_categoria == null ) {
                        System.out.println("nao entrou");
                        sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "- Digite o nome da categoria do bem: "));

                        nome_categoria = update.message().text();

                    }
                    else if (nome_categoria != null) {
                        nome_categoria = update.message().text();

                        categoria = gerencia.buscaCategoriaPorNome(nome_categoria);

                        if(categoria != null){
                            bem.setCategoria(categoria);
                            sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "\nCódigo: "+ bem.getCodigo()
                                    + "\nNome: " + bem.getNome() + "\nDescrição: "+ bem.getDescricao() + "\nLocalização: "
                                    + bem.getLocalizacao().getNome() + "\nCategoria: " + bem.getCategoria().getNome()));
                            gerencia.getBens().add(bem);

                            controle_bem = false;
                            nome_categoria = null;
                            nome_localizacao = null;
                            bem = new Bem(null, null, null, null, null);

                        }else {
                            sendResponse = bot.execute(new SendMessage(update.message().chat().id(), "" +
                                    "Categoria inválida! Tente novamente."));
                            nome_categoria = update.message().text();
                            categoria = gerencia.buscaCategoriaPorNome(nome_categoria);

                        }
                    }

                }

                //envio de "Escrevendo" antes de enviar a resposta
                baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
                //verificação de ação de chat foi enviada com sucesso
                System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());

            }

        }

    }

}
