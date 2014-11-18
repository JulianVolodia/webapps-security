package mkolodziejski.webapp.messages;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PrivateMessagesServiceImpl implements PrivateMessagesService {

    @Inject
    protected Provider<Connection> connectionProvider;

    @Override
    public List<PrivateMessageView> getAllReceivedMessages(Integer userAccountId) {
        Preconditions.checkNotNull(userAccountId);

        List<PrivateMessageView> resultList = Lists.newArrayList();

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select id, sender_id, sender_full_name, recipient_id, recipient_full_name, subject, content, insert_ts " +
                    "from private_message_view where recipient_id = " + userAccountId.toString() + " " +
                    "order by insert_ts desc");

            ResultSet rs = stmt.getResultSet();

            while(rs.next()) {
                PrivateMessageView message = readPrivateMessageView(rs);
                resultList.add(message);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    @Override
    public List<PrivateMessageView> getAllSentMessages(Integer userAccountId) {
        Preconditions.checkNotNull(userAccountId);

        List<PrivateMessageView> resultList = Lists.newArrayList();

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select id, sender_id, sender_full_name, recipient_id, recipient_full_name, subject, content, insert_ts " +
                    "from private_message_view where sender_id = " + userAccountId.toString() + " " +
                    "order by insert_ts desc");

            ResultSet rs = stmt.getResultSet();

            while(rs.next()) {
                PrivateMessageView message = readPrivateMessageView(rs);
                resultList.add(message);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    @Override
    public PrivateMessageView getMessage(String privateMessageId) {
        Preconditions.checkNotNull(privateMessageId);

        PrivateMessageView message = null;

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("select id, sender_id, sender_full_name, recipient_id, recipient_full_name, subject, content, insert_ts " +
                    "from private_message_view where id = " + privateMessageId);

            ResultSet rs = stmt.getResultSet();

            if(rs.next()) {
                message = readPrivateMessageView(rs);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public void insertMessage(Integer senderId, Integer recipientId, String subject, String content) {
        Preconditions.checkNotNull(senderId);
        Preconditions.checkNotNull(recipientId);
        Preconditions.checkNotNull(subject);
        Preconditions.checkNotNull(content);

        Connection connection = connectionProvider.get();
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("insert into private_message (id, sender_id, recipient_id, subject, content, insert_ts) values " +
                    "(nextval('private_message_seq'), " +
                    senderId.toString() + ", " +
                    recipientId.toString() + ", " +
                    "'" + subject + "', " +
                    "'" + content + "', " +
                    "NOW())");

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private PrivateMessageView readPrivateMessageView(ResultSet rs) throws SQLException {
        PrivateMessageView message = new PrivateMessageView();
        message.setId(rs.getInt("id"));
        message.setSenderId(rs.getInt("sender_id"));
        message.setSenderFullName(rs.getString("sender_full_name"));
        message.setRecipientId(rs.getInt("recipient_id"));
        message.setRecipientFullName(rs.getString("recipient_full_name"));
        message.setSubject(rs.getString("subject"));
        message.setContent(rs.getString("content"));
        message.setInsertTs(rs.getTimestamp("insert_ts"));

        return message;
    }
}
