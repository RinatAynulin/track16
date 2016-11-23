package track.messenger.db.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Aynulin on 22.11.2016.
 */

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private Collection<Message> messages = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_chats", joinColumns = {
            @JoinColumn(name = "chat_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "user_id",
                    nullable = false, updatable = false)})
    private Collection<User> participants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User admin;

    public Chat() {
    }

    public Chat(User admin) {
        this.admin = admin;
    }

    public Chat(Collection<Message> messages, Collection<User> participants, User admin) {
        this.messages = messages;
        this.participants = participants;
        this.admin = admin;
    }

    public Chat(long id, Collection<Message> messages, Collection<User> participants, User admin) {
        this.id = id;
        this.messages = messages;
        this.participants = participants;
        this.admin = admin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Collection<Message> getMessages() {
        return messages;
    }

    public void setMessages(Collection<Message> messages) {
        this.messages = messages;
    }

    public Collection<User> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<User> participants) {
        this.participants = participants;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", messages=" + messages +
                ", participants=" + participants +
                ", admin=" + admin +
                '}';
    }
}
