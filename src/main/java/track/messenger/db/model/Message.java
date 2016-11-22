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

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @Column(name = "timestamp", updatable = false)
    private long timestamp;

    public Message() {
    }

    public Message(String text, User sender, Chat chat, long timestamp) {
        this.text = text;
        this.sender = sender;
        this.chat = chat;
        this.timestamp = timestamp;
    }

    public Message(long id, String text, User sender, Chat chat, long timestamp) {
        this.id = id;
        this.text = text;
        this.sender = sender;
        this.chat = chat;
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

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
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
                ", sender=" + sender +
                ", chat=" + chat +
                ", timestamp=" + timestamp +
                '}';
    }
}
