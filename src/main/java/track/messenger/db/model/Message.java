package track.messenger.db.model;

import javax.persistence.*;

/**
 * Created by Aynulin on 22.11.2016.
 */

@Entity
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "text", updatable = false)
    private String text;

    @Column(name = "sender_id", updatable = false)
    private long senderId;

    @Column(name = "chat_id", updatable = false)
    private long chatId;

    @Column(name = "timestamp", updatable = false)
    private long timestamp;

    public Message() {
    }

    public Message(String text, long senderId, long chatId, long timestamp) {
        this.text = text;
        this.senderId = senderId;
        this.chatId = chatId;
        this.timestamp = timestamp;
    }

    public Message(long id, String text, long senderId, long chatId, long timestamp) {
        this.id = id;
        this.text = text;
        this.senderId = senderId;
        this.chatId = chatId;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", senderId=" + senderId +
                ", chatId=" + chatId +
                ", timestamp=" + timestamp +
                '}';
    }
}
