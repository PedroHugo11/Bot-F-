import java.util.List;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ChatAction;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendChatAction;
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
		int m=0;

		//auxiliar para exibir menu só uma vez
		int aux=0;
		
		//loop infinito pode ser alterado por algum timer de intervalo curto
		while (true){

			//executa comando no Telegram para obter as mensagens pendentes a partir de um off-set (limite inicial)
			updatesResponse =  bot.execute(new GetUpdates().limit(100).offset(m));
			
			//lista de mensagens
			List<Update> updates = updatesResponse.updates();

			//análise de cada ação da mensagem
			for (Update update : updates) {
				if(aux == 0) {
					sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"" +
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
							"11. Gerar relatório\n"));
				}
				aux = 1;
				//atualização do off-set
				m = update.updateId()+1;

				System.out.println("Recebendo mensagem:"+ update.message().text());

				String respostaMenu = update.message().text();
				if (respostaMenu.equals("1")) {
                    sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"" +
                            "########### BOT FÉ - CADASTRAR LOCALIZAÇÃO ###########\n" +
                            "1. inserir drogas\n" +
                            "2. nao sei oq precisa na localização\n"));
                    System.out.println("Mensagem Enviada?" +sendResponse.isOk());
				} else if (respostaMenu.equals("2")) {

                } else if (respostaMenu.equals("3")) {

                } else if (respostaMenu.equals("4")) {

                } else if (respostaMenu.equals("5")) {

                } else if (respostaMenu.equals("6")) {

                } else if (respostaMenu.equals("7")) {

                } else if (respostaMenu.equals("8")) {

                } else if (respostaMenu.equals("9")) {

                } else if (respostaMenu.equals("10")) {

                } else if (respostaMenu.equals("11")) {

                } else {
                    //sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"" + "####### OPÇÃO INVÁLIDA, INISIRA NOVAMENTE #######\n"));
				}
				
				//envio de "Escrevendo" antes de enviar a resposta
				baseResponse = bot.execute(new SendChatAction(update.message().chat().id(), ChatAction.typing.name()));
				//verificação de ação de chat foi enviada com sucesso
				System.out.println("Resposta de Chat Action Enviada?" + baseResponse.isOk());
				//envio da mensagem de resposta
                // sendResponse = bot.execute(new SendMessage(update.message().chat().id(),"Não entendi..."));
				//verificação de mensagem enviada com sucesso
                //System.out.println("Mensagem Enviada?" +sendResponse.isOk());

				
			}

		}

	}

}
