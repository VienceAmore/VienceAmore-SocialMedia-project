package Controller;

import java.util.List;

import Model.Account;
import Model.Message;
import Service.AccountSercviceImplementation;
import Service.AccountService;
import Service.MessageService;
import Service.MessageServiceImplementation;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {

    MessageService messageService;
    AccountService accountService;

    public SocialMediaController(){
        messageService = new MessageServiceImplementation();
        accountService = new AccountSercviceImplementation();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        app.post("/messages", this::addMessage);

        app.get("/messages", this::getAllMessage);

        app.get("/messages/{message_id}", this::getMessageById);

        app.get("/accounts/{account_id}/messages", this::getAllMessageByUser);

        app.patch("/messages/{message_id}", this::updateMessageById);

        app.delete("messages/{message_id}", this::deleteMessageById);

        //account

        app.post("/register", this::createAccount);

        app.post("/login", this::verifyAccount);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    private void addMessage(Context ctx) {
        Model.Message message = ctx.bodyAsClass(Message.class);

        Model.Message newMessage = messageService.addMessage(message);

        if (newMessage != null) {
            ctx.json(newMessage);
            ctx.status(200);
        } else {
            ctx.status(400);
        }
    }

    private void getAllMessage(Context ctx){
        List<Model.Message> messagesList = messageService.getAllMessage();
        ctx.json(messagesList);
    }

    private void getMessageById(Context ctx) {

        int id = Integer.parseInt(ctx.pathParam("id"));

        Model.Message message = messageService.getMessageById(id);
        ctx.json(message);

    }

    private void getAllMessageByUser(Context ctx) {
        Model.Message message = ctx.bodyAsClass(Message.class);

        List<Model.Message> messageByUserList = messageService.getAllMessageByUser(message.getPosted_by());

        ctx.status(200);
        if(!messageByUserList.isEmpty())
            ctx.json(messageByUserList);
        else 
            ctx.json("");
    }

    private void updateMessageById(Context ctx) {
        Model.Message message = ctx.bodyAsClass(Message.class);

        boolean result = messageService.updateMessageById(message);
        
        if (result) {
            ctx.status(200);
            ctx.json(message);
        }else{
            ctx.status(400);
        }
    }

    private void deleteMessageById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));

        Model.Message deletedMessage = messageService.getMessageById(id);
        boolean result = messageService.deleteMessageById(id);
        
        ctx.status(200);
        if(result)
            ctx.json(deletedMessage);
        else ctx.json("");
    }

    private void createAccount(Context ctx){
        Model.Account account = ctx.bodyAsClass(Account.class);

        Model.Account newAccount = accountService.createAccount(account);

        if (newAccount != null) {
            ctx.json(newAccount);
            ctx.status(200);
        } else {
            ctx.status(400);
        }

    }

    private void verifyAccount(Context ctx){
        Model.Account account = ctx.bodyAsClass(Account.class);

        boolean result = accountService.verifyAccount(account);

        if (result) {
            ctx.json(account);
            ctx.status(200);
        } else {
            ctx.status(401);
        }

    }

}