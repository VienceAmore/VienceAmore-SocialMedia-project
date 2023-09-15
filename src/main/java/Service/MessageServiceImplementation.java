package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import DAO.MessageDAO;
import Model.Message;
import Util.ConnectionUtil;

public class MessageServiceImplementation implements MessageService {

    MessageDAO messageDAO;

    public MessageServiceImplementation(){
        this.messageDAO = new MessageDAO();
    }

    @Override
    public Message addMessage(Message message) {
        if(!message.getMessage_text().isEmpty() && (message.getMessage_text().length() <= 254) && existingPostedBy(message)) 
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
    public boolean updateMessageById(int id, Message message) {
        
        Message existingMessage = messageDAO.getMessageById(id);

        if (existingMessage == null) {
            return false;
        }

        if(!message.getMessage_text().isEmpty() && message.getMessage_text().length() <= 254)
        {
            return messageDAO.updateMessageById(id, message);
        }
        return false;
    }
    
    @Override
    public boolean deleteMessageById(int id){
        return messageDAO.deleteMessageById(id);
    }

    public boolean existingPostedBy(Message message){
        try{
            Connection connection = ConnectionUtil.getConnection();
            String sql = "SELECT * FROM message WHERE posted_by = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, message.getPosted_by());

            ResultSet result = statement.executeQuery();

            if(result.next())
                return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }
}
