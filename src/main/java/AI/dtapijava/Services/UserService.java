package AI.dtapijava.Services;


import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.UserCreateReqDTO;
import AI.dtapijava.DTOs.Response.*;
import AI.dtapijava.Entities.*;
import AI.dtapijava.Enums.TransactionType;
import AI.dtapijava.Exceptions.EmailReadyExistException;
import AI.dtapijava.Exceptions.UserNotFoundExceptions;
import AI.dtapijava.Infrastructure.Util.UserUtils;
import AI.dtapijava.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SellOfferRepository sellOfferRepository;
    @Autowired
    private BuyOfferRepository buyOfferRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    public User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
    }

    public UsersFullResDTO getUsers() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<User> users = userRepository.findAll();
        execHelper.addNewDbTime(OffsetDateTime.now());

        return new UsersFullResDTO(users, execHelper.getDbTime(), execHelper.getExecTime());
    }

    public UserFullResDTO getActiveUser() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        return new UserFullResDTO(user, execHelper.getDbTime(), execHelper.getExecTime());
    }

    public ExecTimeResDTO createUser(UserCreateReqDTO userCreateReqDTO) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        User user = User.builder()
                .email(userCreateReqDTO.getEmail())
                .name(userCreateReqDTO.getName())
                .password(passwordEncoder.encode(userCreateReqDTO.getPassword()))
                .cash(0.0)
                .build();

        execHelper.setStartDbTime(OffsetDateTime.now());
        if (userRepository.existsByEmail(userCreateReqDTO.getEmail())) {
           throw new EmailReadyExistException("Email already exists in database");
        }

        User newUser = userRepository.save(user);
        execHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

    public UserResourcesResDTO getActiveUserResources() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<Resource> userResources = resourceRepository.getAllResourcesFromUser(user.getId());
        execHelper.addNewDbTime();

        List<UserResourceResDTO> resourcesList = userResources.stream().map(UserResourceResDTO::new).collect(Collectors.toList());

        return new UserResourcesResDTO(resourcesList, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

    public UserSellOffersResDTO getActiveUserSellOffers() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<SellOffer> userSellOffers = sellOfferRepository.getAllSellOffersForUserId(user.getId());
        execHelper.addNewDbTime();

        List<UserSellOfferResDTO> sellOffersList = userSellOffers.stream().map(UserSellOfferResDTO::new).collect(Collectors.toList());

        return new UserSellOffersResDTO(sellOffersList, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

    public UserBuyOffersResDTO getActiveUserBuyOffers() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<BuyOffer> userBuyOffers = buyOfferRepository.getAllBuyOffersForUserId(user.getId());
        execHelper.addNewDbTime();

        List<UserBuyOfferResDTO> buyOffersList = userBuyOffers.stream().map(UserBuyOfferResDTO::new).collect(Collectors.toList());

        return new UserBuyOffersResDTO(buyOffersList, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

    public UserTransactionsResDTO getActiveUserTransactions() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<Transaction> userSellTransactions = transactionRepository.getAllSellTransactionsForUserId(user.getId());
        List<Transaction> userBuyTransactions = transactionRepository.getAllBuyTransactionsForUserId(user.getId());
        //List<Transaction> userTransactions = new ArrayList<Transaction>();
        List<UserTransactionResDTO> userTransactionsList = new ArrayList<UserTransactionResDTO>();
        for (Transaction tr : userSellTransactions) {
            userTransactionsList.add(new UserTransactionResDTO(tr, TransactionType.SELL_OFFER));
        }
        for (Transaction tr : userBuyTransactions) {
            userTransactionsList.add(new UserTransactionResDTO(tr, TransactionType.BUY_OFFER));
        }

        List <UserTransactionResDTO> list = Stream.concat(
                userBuyTransactions.stream().map(t->new UserTransactionResDTO(t, TransactionType.BUY_OFFER)),
                userSellTransactions.stream().map(t->new UserTransactionResDTO(t, TransactionType.SELL_OFFER))
        ).collect(Collectors.toList());

        execHelper.addNewDbTime();

        //List<UserTransactionResDTO> userTrasactionsList = userTransactions.stream().map(UserTransactionResDTO::new).collect(Collectors.toList());

        return new UserTransactionsResDTO(userTransactionsList, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

    public UserTransactionsResDTO getActiveUserSellTransactions() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<Transaction> userSellTransactions = transactionRepository.getAllSellTransactionsForUserId(user.getId());
        execHelper.addNewDbTime();

        List<UserTransactionResDTO> userTransactionsList = userSellTransactions.stream().map(x -> new UserTransactionResDTO(x, TransactionType.SELL_OFFER)).collect(Collectors.toList());

        return new UserTransactionsResDTO(userTransactionsList, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

    public UserTransactionsResDTO getActiveUserBuyTransactions() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<Transaction> userBuyTransactions = transactionRepository.getAllBuyTransactionsForUserId(user.getId());
        execHelper.addNewDbTime();

        List<UserTransactionResDTO> userTransactionsList = userBuyTransactions.stream().map(x -> new UserTransactionResDTO(x, TransactionType.BUY_OFFER)).collect(Collectors.toList());

        return new UserTransactionsResDTO(userTransactionsList, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }
}
