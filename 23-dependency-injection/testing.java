
public class testing {

    class EmailService {

        public void send(String message) {
            System.out.println("Gửi email: " + message);
        }
    }

    class UserService {

        private final EmailService emailService;

        public UserService(EmailService emailService) {
            this.emailService = emailService;
        }

        public void register(String username) {
            emailService.send("User mới: " + username);
        }
    }

    public static void main(String args[]) {

    }
}
