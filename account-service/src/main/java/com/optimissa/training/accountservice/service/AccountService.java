package com.optimissa.training.accountservice.service;
import com.optimissa.training.accountservice.api.AccountRequest;
import com.optimissa.training.accountservice.api.AccountResponse;
import com.optimissa.training.accountservice.api.CurrencyResponse;
import com.optimissa.training.accountservice.controller.AccountController;
import com.optimissa.training.accountservice.mapper.AccountMapper;
import com.optimissa.training.accountservice.mapper.AccountRequestMapper;
import com.optimissa.training.accountservice.models.*;
import com.optimissa.training.accountservice.repository.AccountRepository;
import com.optimissa.training.accountservice.repository.IbanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Random;


@Service
@Transactional
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    IbanRepository ibanRepository;

    @Autowired
    AccountRequestMapper accountRequestMapper;

    @Autowired
    ValidationIbanService validationIbanService;

    @Autowired
    AccountMapper accountMapper;

    RestTemplate restTemplate = new RestTemplate();

    String urlCurrency = "http://localhost:8093/currencies/";
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);




    public AccountResponse getAccount(int id) {
        LOGGER.info("Started AccountService.getAccount()");
        long start = System.currentTimeMillis();
        Account account = accountRepository.getAccount(id);
        AccountResponse accountResponse = new AccountResponse(
                account.getId(),
                account.getBalance(),
                account.isActive(),
                account.getIban_id(),
                account.getClient_id(),
                getByIdAccount(account.getCurrency_id()),
                account.getIban());
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.getAccount(), {} ms, {} " , end-start, accountResponse.toString() );
        return accountResponse;

    }

    public CurrencyResponse getByIdAccount(int id) {
        LOGGER.info("Started AccountService.getByIdAccount()");
        long start = System.currentTimeMillis();
        CurrencyResponse currencyResponse = restTemplate.getForObject("http://localhost:8093/currencies/get-by-id/" + id, CurrencyResponse.class);
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.getByIdAccount(), {} ms, {} " , end-start );
        return currencyResponse;
    }

    public List<Account> getAllAccount() {
        LOGGER.info("Started AccountService.getAllAccount()");
        long start = System.currentTimeMillis();
        List<Account>accounts = accountRepository.getAllAccount();
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.getAllAccount(), {} ms, {} " , end-start, accounts.toString() );
        return accounts;
    }

    public List<Account> getAccountByClient(int clientId) {
        LOGGER.info("Started AccountService.getAccountByClient()");
        long start = System.currentTimeMillis();
        List<Account>accounts = accountRepository.getAccountByClient(clientId);
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.getAccountByClient(), {} ms, {} " , end-start, accounts.toString() );
        return accounts;
    }

    public boolean createAccount(AccountRequest accountRequest) {
        LOGGER.info("Started AccountService.createAccount()");
        long start = System.currentTimeMillis();
        int ibanCountry = accountRequest.getIbanCountry();
        int ibanEntity = accountRequest.getIbanEntity();
        //mapper AccountRequest -> AccountDTO
        Account account = accountRequestMapper.maptoAccount(accountRequest);
        int lineaAccount = 0;

        if(validationIbanService.validate(ibanCountry, ibanEntity)){

            account.setIban(generateIBAN(ibanEntity, ibanCountry, account));
            lineaAccount = accountRepository.save(account);
        }
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.createAccount(), {}ms, {} " , end-start, account.toString() );
        return lineaAccount > 0;
    }

    private String generateIBAN(int ibanEntity, int ibanCountry, Account a) {
        LOGGER.info("Started AccountService.generateIBAN()");
        long start = System.currentTimeMillis();
        int max = 999_999_999;
        int min = 100_000_000;
        Random random = new Random();
        int countryControl = random.nextInt(100);
        int branch = random.nextInt(9000) + 1000;
        int accountControl = random.nextInt(100);
        int accountNumber = random.nextInt((max - min) + 1) + min;
        int lastNumber = random.nextInt(10);

        int iban = ibanRepository.save(ibanCountry, ibanEntity, branch, accountControl, accountNumber, countryControl);
        a.setIban_id(iban);

        String country = accountRepository.getibanCountry(ibanCountry);
        int entity = accountRepository.detIBANEntity(ibanEntity);

        String result = country+countryControl+entity+branch+accountControl+accountNumber+lastNumber;
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.generateIBAN(), {}ms, {} " , end-start, result);
        return result;
    }

    public int updateBalance(int id, double balance) {
        LOGGER.info("Started AccountService.updateBalance()");
        long start = System.currentTimeMillis();
        int result = accountRepository.updateAddBalance(id,balance);
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.updateBalance(), {}ms, {}" , end-start, result );
        return result;
    }

    public int updateBalanceDeduct(int id, double balance) {
        LOGGER.info("Started AccountService.updateBalanceDeduct()");
        long start = System.currentTimeMillis();
        int result = accountRepository.updateBalanceSubstract(id,balance);
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.updateBalanceSubstract(), {}ms, {}" , end-start, result );
        return result;
    }

    public StringResponse deleteAccount(int id) {
        LOGGER.info("Started AccountService.deleteAccount()");
        long start = System.currentTimeMillis();
        String str = "a";

        if (accountRepository.delete(id) == 1) {
            str = "se ha desactivado la cuenta " + id;
        } else {
            str = "no se ha desactivado la cuenta " + id + " porque no existe";
        }
        long end = System.currentTimeMillis();
        LOGGER.info("Finished AccountService.DeleteAccount(), {}ms, {}" , end-start, str);
        return new StringResponse(str);
    }

    public int modifyAccount(Account account, int id) {
        return accountRepository.modifyAccount(account,id);
    }
}
