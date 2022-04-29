package com.jupiteropt.assessment.controller;

import com.jupiteropt.assessment.domain.AccountEntity;
import com.jupiteropt.assessment.domain.CreateAccountDto;
import com.jupiteropt.assessment.domain.CustomerEntity;
import com.jupiteropt.assessment.domain.TransferDto;
import com.jupiteropt.assessment.domain.TransferHistoryEntity;
import com.jupiteropt.assessment.exception.AppException;
import com.jupiteropt.assessment.repository.AccountEntityRepository;
import com.jupiteropt.assessment.repository.CustomerEntityRepository;
import com.jupiteropt.assessment.repository.TransferHistoryEntityRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JupiteroptController {

  private final AccountEntityRepository accountEntityRepository;
  private final TransferHistoryEntityRepository transferHistoryEntityRepository;
  private final CustomerEntityRepository customerEntityRepository;

  @Autowired
  public JupiteroptController(AccountEntityRepository accountEntityRepository,
                              TransferHistoryEntityRepository transferHistoryEntityRepository,
                              CustomerEntityRepository customerEntityRepository) {
    this.accountEntityRepository = accountEntityRepository;
    this.transferHistoryEntityRepository = transferHistoryEntityRepository;
    this.customerEntityRepository = customerEntityRepository;
  }

  /**
   * Create customer account and add initial deposit.
   *
   * @param dto - CreateAccountDto.
   * @return - AccountEntity.
   */
  @PutMapping(path = "/bank/createAccount")
  @ResponseBody
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public ResponseEntity<AccountEntity> createAccount(@RequestBody CreateAccountDto dto) {
    String fName = Optional.ofNullable(dto.getFirstName())
        .orElseThrow(() -> new AppException("Customer name is empty"));
    String lName = Optional.ofNullable(dto.getLastName())
        .orElseThrow(() -> new AppException("Customer name is empty"));
    double amount = Optional.of(dto.getInitialDeposit())
        .filter(value -> value > 0)
        .orElseThrow(() -> new AppException("Initial deposit shuld be > 0"));
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setFirstName(fName);
    customerEntity.setLastName(lName);
    AccountEntity accountEntity = new AccountEntity();
    accountEntity.setBalance(amount);
    accountEntity.setCustomerEntity(customerEntity);
    accountEntity = accountEntityRepository.save(accountEntity);
    return new ResponseEntity<>(accountEntity, HttpStatus.OK);
  }

  /**
   * Transfer from account to account.
   *
   * @param dto - TransferDto data.
   * @return - TransferResultDto.
   */
  @PostMapping(path = "/bank/transfer")
  @ResponseBody
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public ResponseEntity<TransferHistoryEntity> transfer(@RequestBody TransferDto dto) {
    AccountEntity from = Optional.ofNullable(accountEntityRepository.findByAccountId(dto.getAccountFrom()))
        .orElseThrow(IllegalArgumentException::new);
    AccountEntity to = Optional.ofNullable(accountEntityRepository.findByAccountId(dto.getAccountTo()))
        .orElseThrow(IllegalArgumentException::new);
    if (from.getBalance() < dto.getAmount()) {
      throw new AppException("Not enough fund for transfer");
    }
    from.setBalance(from.getBalance()-dto.getAmount());
    to.setBalance(to.getBalance()+dto.getAmount());
    from = accountEntityRepository.save(from);
    to = accountEntityRepository.save(to);
    TransferHistoryEntity transferHistoryEntity = new TransferHistoryEntity();
    transferHistoryEntity.setAccountFrom(from.getAccountId());
    transferHistoryEntity.setAccountTo(to.getAccountId());
    transferHistoryEntity.setAmount(dto.getAmount());
    transferHistoryEntity = transferHistoryEntityRepository.save(transferHistoryEntity);
    return new ResponseEntity<>(transferHistoryEntity, HttpStatus.OK);
  }

  /**
   * Retrieve account balance.
   *
   * @param account - bank account.
   * @return - AccountEntity
   */
  @GetMapping("/bank/{account}/balance")
  @ResponseBody
  public ResponseEntity<AccountEntity> getAccountById(@PathVariable long account) {
    return new ResponseEntity<>(accountEntityRepository.findByAccountId(account), HttpStatus.OK);
  }

  /**
   * Retrieve account transfer history.
   *
   * @param accountFrom - bank account transferred from.
   * @param accountTo - bank account transferred from.
   * @return - List of TransferHistoryEntity.
   */
  @GetMapping("/bank/history/")
  @ResponseBody
  public ResponseEntity<List<TransferHistoryEntity>> getHistoryByAccountId(@RequestParam long accountFrom, @RequestParam long accountTo) {
    return new ResponseEntity<>(
        transferHistoryEntityRepository.findByAccountFromIsAndAccountToIs(accountFrom, accountTo), HttpStatus.OK);
  }

}
