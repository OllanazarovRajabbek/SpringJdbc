package container;

import dto.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import repository.CardRepository;
import repository.ProfileRepository;
import repository.TerminalRepository;
import repository.TransactionRepository;
import service.*;
@Component
public class ComponentContainer {
    public static Profile currentProfile = null;
    @Autowired
    public static CardRepository cardRepository = new CardRepository();
    public static ProfileRepository profileRepository = new ProfileRepository();
    public static TerminalRepository terminalRepository = new TerminalRepository();
    public static TransactionRepository transactionRepository = new TransactionRepository();
    public static CardService cardService = new CardService();
    public static ProfileService profileService = new ProfileService();

    public static AuthService authService = new AuthService();

    public static TerminalService terminalService = new TerminalService();
    public static TransactionService transactionService = new TransactionService();
}
