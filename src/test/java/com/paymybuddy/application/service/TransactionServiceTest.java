package com.paymybuddy.application.service;

import com.paymybuddy.application.constants.TransactionType;
import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.model.Transaction;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    private User user;
    private User buddy;
    private Transaction transaction;

    @BeforeAll
    private void setUp() throws Exception {
        transactionRepository.deleteAll();

        user = new User();
        user.setEmail("usertransaction@email.com");
        user.setPassword("user");
        user.setFirstName("userTestFirstName");
        user.setLastName("userTestLastName");

        userService.addUser(user);
        Account originalAccount = user.getAccount();
        originalAccount.setBalance(new BigDecimal(100));
        accountRepository.save(originalAccount);

        buddy = new User();
        buddy.setEmail("buddytransaction@email.com");
        buddy.setPassword("buddy");
        buddy.setFirstName("buddyTestFirstName");
        buddy.setLastName("buddyTestLastName");

        userService.addUser(buddy);
        Account buddyAccount = buddy.getAccount();
        buddyAccount.setBalance(new BigDecimal(100));
        accountRepository.save(buddyAccount);

        transaction = new Transaction();
        Timestamp date = Timestamp.from(Instant.now());
        transaction.setDate(date);
        transaction.setTransactionType(TransactionType.TRANSFER);
        transaction.setAmount(new BigDecimal(50));
        transaction.setFees(new BigDecimal("0.25"));
        transaction.setDescription("TransactionTest");
        transaction.setOriginalAccount(user.getAccount());
        transaction.setBuddyAccount(buddy.getAccount());

    }

    @AfterAll
    private void tearDown(){
        accountRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    public void addTransactionTest(){
        transactionService.addTransaction(transaction);
        assertThat(transaction.getOriginalAccount().getId()).isEqualTo(user.getAccount().getId());
        assertThat(transaction.getBuddyAccount().getId()).isEqualTo(buddy.getAccount().getId());
    }

    @Test
    @Order(2)
    public void getTransactionById(){
        Transaction t = transactionService.getTransactionById(transaction.getId()).get();
        assertThat(t.getId()).isEqualTo(transaction.getId());
    }

    @Test
    @Order(3)
    public void getTransactionsByAccountOriginalIdTest(){
        Page<Transaction> transactionPage = transactionService.getTransactionsByAccountOriginalId(user.getAccount().getId(), PageRequest.of(1,1));
        assertThat(transactionPage.getTotalPages()).isEqualTo(1);
    }

    @Test
    @Order(4)
    public void deleteTransactionByIdTest(){
        transactionService.deleteTransactionById(transaction.getId());
        List<Transaction> transactionList = transactionService.getTransactions();
        assertThat(transactionList.size()).isEqualTo(0);
    }

}
