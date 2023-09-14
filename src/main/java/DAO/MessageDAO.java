package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    //create 
    public Message addMessage(Message message){
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "INSERT INTO message(posted_by, message_text, time_posted_epoch) VALUES (?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            statement.setInt(2, message.getPosted_by());
            statement.setString(3, message.getMessage_text());
            statement.setLong(4, message.getTime_posted_epoch());

            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                return new Message(keys.getInt(1), message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return null;
    }

    //read
    public List<Message> getAllMessage(){
        List<Message> messageList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = (Statement) connection.createStatement();
            ResultSet rs = ((java.sql.Statement) statement).executeQuery("SELECT * FROM message");

            while (rs.next()) {
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
        
                messageList.add(new Message(message_id, posted_by, message_text, time_posted_epoch));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return messageList;
    }

    public Message getMessageById(int id){
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = (Statement) connection.createStatement();
        
            ResultSet rs = ((java.sql.Statement) statement).executeQuery("SELECT * FROM message WHERE message_id = " + id);
        
            while (rs.next()) {
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
        
                return new Message(message_id, posted_by, message_text, time_posted_epoch);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Message> getAllMessageByUser(int userId){
        List<Message> messageListByUser = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            Statement statement = (Statement) connection.createStatement();
            ResultSet rs = ((java.sql.Statement) statement).executeQuery("SELECT * FROM message WHERE posted_by = " + userId);

            while (rs.next()) {
                int message_id = rs.getInt("message_id");
                int posted_by = rs.getInt("posted_by");
                String message_text = rs.getString("message_text");
                long time_posted_epoch = rs.getLong("time_posted_epoch");
        
                messageListByUser.add(new Message(message_id, posted_by, message_text, time_posted_epoch));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return messageListByUser;
    }

    //update
    public boolean updateMessageById(Message message){
        
        try (Connection connection = ConnectionUtil.getConnection()) {
            String sql = "UPDATE message SET posted_by = ?, message_text = ?, time_posted_epoch = ? WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.setInt(4, message.getMessage_id());

            int numberOfUpdatedRows = ps.executeUpdate();

            if (numberOfUpdatedRows != 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //delete
    public boolean deleteMessageById(int id){
        try (Connection connection = ConnectionUtil.getConnection()) {

            String sql = "DELETE FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int numberOfUpdatedRows = ps.executeUpdate();

            if (numberOfUpdatedRows != 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
