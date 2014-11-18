package mkolodziejski.webapp.messages;

import java.sql.Timestamp;

public class PrivateMessageView {
    private Integer id;
    private Integer senderId;
    private String senderFullName;
    private Integer recipientId;
    private String recipientFullName;
    private String subject;
    private String content;
    private Timestamp insertTs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public String getSenderFullName() {
        return senderFullName;
    }

    public void setSenderFullName(String senderFullName) {
        this.senderFullName = senderFullName;
    }

    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public String getRecipientFullName() {
        return recipientFullName;
    }

    public void setRecipientFullName(String recipientFullName) {
        this.recipientFullName = recipientFullName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getInsertTs() {
        return insertTs;
    }

    public void setInsertTs(Timestamp insertTs) {
        this.insertTs = insertTs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrivateMessageView that = (PrivateMessageView) o;

        if (!content.equals(that.content)) return false;
        if (!id.equals(that.id)) return false;
        if (!insertTs.equals(that.insertTs)) return false;
        if (!recipientFullName.equals(that.recipientFullName)) return false;
        if (!recipientId.equals(that.recipientId)) return false;
        if (!senderFullName.equals(that.senderFullName)) return false;
        if (!senderId.equals(that.senderId)) return false;
        if (!subject.equals(that.subject)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + senderId.hashCode();
        result = 31 * result + senderFullName.hashCode();
        result = 31 * result + recipientId.hashCode();
        result = 31 * result + recipientFullName.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + content.hashCode();
        result = 31 * result + insertTs.hashCode();
        return result;
    }
}
