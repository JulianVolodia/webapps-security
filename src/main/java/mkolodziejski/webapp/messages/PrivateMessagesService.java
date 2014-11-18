package mkolodziejski.webapp.messages;

import java.util.List;

public interface PrivateMessagesService {

    List<PrivateMessageView> getAllReceivedMessages(Integer userAccountId);

    List<PrivateMessageView> getAllSentMessages(Integer userAccountId);

    PrivateMessageView getMessage(String privateMessageId);

    void insertMessage(Integer senderId, Integer recipientId, String subject, String content);
}
