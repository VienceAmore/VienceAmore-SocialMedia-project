package Service;

import java.util.List;
import DAO.MessageDAO;
import Model.Message;

public class MessageServiceImplementation implements MessageService {

    MessageDAO messageDAO;

    public MessageServiceImplementation(){
        this.messageDAO = new MessageDAO();
    }

    @Override
    public Message addMessage(Message message) {
        
        if(!message.getMessage_text().isEmpty() && (message.getMessage_text().length() <= 255) ) //&& message.getPosted_by())
        {
            return messageDAO.addMessage(message);
        }
        return null;
    }

    @Override
    public List<Message> getAllMessage(){
        return messageDAO.getAllMessage();
    }

    @Override
    public Message getMessageById(int id){
        return messageDAO.getMessageById(id);
    }

    @Override
    public List<Message> getAllMessageByUser(int userId){
        return messageDAO.getAllMessageByUser(userId);

    }

    @Override
    public boolean updateMessageById(Message message) {
        
        Message existingMessage = messageDAO.getMessageById(message.getMessage_id());

        if (existingMessage == null) {
            return false;
        }

        if(!message.getMessage_text().isEmpty() && message.getMessage_text().length() <= 255)
        {
            return messageDAO.updateMessageById(message);
        }
        return false;
    }
    
    @Override
    public boolean deleteMessageById(int id){
        return messageDAO.deleteMessageById(id);
    }
}
