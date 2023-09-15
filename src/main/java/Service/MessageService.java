package Service;

import java.util.List;
import Model.Message;

public interface MessageService {

    Message addMessage(Message message);

    List<Message> getAllMessage();

    Message getMessageById(int id);

    List<Message> getAllMessageByUser(int userId);

    boolean updateMessageById(int id, Message message);
    
    boolean deleteMessageById(int id);

}
