package track.messenger.db.services;

/**
 * Created by Aynulin on 23.11.2016.
 */
public class DbService {
    private static volatile DbService instance;

    private UserService userService;
    private ChatService chatService;
    private MessageService messageService;

    private DbService(UserService userService, ChatService chatService, MessageService messageService) {
        this.userService = userService;
        this.chatService = chatService;
        this.messageService = messageService;
    }

    public static DbService getInstance() {
        DbService localInstance = instance;
        if (localInstance == null) {
            synchronized (DbService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DbService(new UserService(),
                            new ChatService(), new MessageService());
                }
            }
        }
        return localInstance;
    }

    public UserService getUserService() {
        return userService;
    }

    public ChatService getChatService() {
        return chatService;
    }

    public MessageService getMessageService() {
        return messageService;
    }
}
