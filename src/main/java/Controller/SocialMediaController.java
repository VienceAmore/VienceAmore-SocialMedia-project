package Controller;

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
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();

        MessageService messageService;
        AccountService accountService;

        public SocialMediaController(){
            messageService = new MessageServiceImplementation();
            accountService = new AccountSercviceImplementation();
        }

        app.post("/messages", this::addMessage);
        app.get("/messages", this::getAllMessage);
        app.get("/messages/{message_id}", this::getMessageById);
        app.get("/accounts/{account_id}/messages", this::getAllMessageByUser);
        app.patch("/messages/{message_id}", this::updateMessageById);
        app.delete("messages/{message_id}", this::deleteMessageById);

        //app.get("example-endpoint", this::exampleHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */

    
    private void addMessage(Context ctx) {
        ctx.json("sample text");
    }

    private void getAllMessage(Context ctx){

    }

    private void getMessageById(Context ctx) {
        ctx.json("sample text");
    }

    private void getAllMessageByUser(Context ctx) {
        ctx.json("sample text");
    }

    private void updateMessageById(Context ctx) {
        ctx.json("sample text");
    }

    private void deleteMessageById(Context ctx) {
        ctx.json("sample text");
    }


    /*private void exampleHandler(Context context) {
        context.json("sample text");
    }*/

}