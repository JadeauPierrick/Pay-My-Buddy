package com.paymybuddy.application.service;

import com.paymybuddy.application.constants.TransactionType;
import com.paymybuddy.application.model.Account;
import com.paymybuddy.application.model.User;
import com.paymybuddy.application.repository.AccountRepository;
import com.paymybuddy.application.repository.TransactionRepository;
import com.paymybuddy.application.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private User user;
    private User buddy;

    @BeforeAll
    private void setUp() throws Exception {
        user = new User();
        user.setEmail("user@email.com");
        user.setPassword("user");
        user.setFirstName("userTestFirstName");
        user.setLastName("userTestLastName");

        userService.addUser(user);
        Account originalAccount = user.getAccount();
        originalAccount.setBalance(new BigDecimal(100));
        accountRepository.save(originalAccount);

        buddy = new User();
        buddy.setEmail("buddy@email.com");
        buddy.setPassword("buddy");
        buddy.setFirstName("buddyTestFirstName");
        buddy.setLastName("buddyTestLastName");

        userService.addUser(buddy);
        Account buddyAccount = buddy.getAccount();
        buddyAccount.setBalance(new BigDecimal(100));
        accountRepository.save(buddyAccount);

    }

    @AfterAll
    private void tearDown(){
        userRepository.deleteById(user.getId());
        userRepository.deleteById(buddy.getId());
    }

    @Test
    @Order(1)
    public void getAccountByIdTest(){
        Account account = accountRepository.findById(user.getAccount().getId()).get();
        Account otherAccount = accountRepository.findById(buddy.getAccount().getId()).get();
        assertThat(account.getId()).isEqualTo(user.getAccount().getId());
        assertThat(otherAccount.getId()).isEqualTo(buddy.getAccount().getId());

    }

    @Test
    @Order(2)
    public void makeATransferTest() throws Exception {
        accountService.makeATransfer(user.getAccount(), buddy.getAccount(), TransactionType.TRANSFER, new BigDecimal(50),"TestMakeTransfer");
        assertThat(user.getAccount().getBalance().compareTo(new BigDecimal(100))).isEqualTo(-1);
        assertThat(buddy.getAccount().getBalance().compareTo(new BigDecimal(100))).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void makeABankTransferDepositTest() throws Exception {
        accountService.makeABankTransfer(user.getAccount(), TransactionType.BANK_DEPOSIT, new BigDecimal(40), "TestMakeABankTransferDeposit");
        assertThat(user.getAccount().getBalance().compareTo(new BigDecimal(49.75))).isEqualTo(1);
    }

    @Test
    @Order(4)
    public void makeABankTransferWithdrawalTest() throws Exception {
        accountService.makeABankTransfer(buddy.getAccount(), TransactionType.BANK_WITHDRAWAL, new BigDecimal(40), "TestMakeABankTransferWithdrawal");
        assertThat(buddy.getAccount().getBalance().compareTo(new BigDecimal(150))).isEqualTo(-1);
    }

    @Test
    @Order(5)
    public void deleteAccountByIdTest(){
        transactionRepository.deleteAll();
        accountService.deleteAccountById(user.getAccount().getId());
        accountService.deleteAccountById(buddy.getAccount().getId());
        List<Account> accountList = accountService.getAccounts();
        assertThat(accountList.size()).isEqualTo(0);
    }
}
