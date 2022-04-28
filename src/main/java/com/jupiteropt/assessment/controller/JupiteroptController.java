package com.jupiteropt.assessment.controller;

import com.jupiteropt.assessment.domain.AccountEntity;
import com.jupiteropt.assessment.domain.AccountEntityRepository;
import com.jupiteropt.assessment.domain.TransferDto;
import com.jupiteropt.assessment.domain.TransferHistoryEntity;
import com.jupiteropt.assessment.domain.TransferHistoryEntityRepository;
import com.jupiteropt.assessment.domain.TransferResultDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  public JupiteroptController(AccountEntityRepository accountEntityRepository,
                              TransferHistoryEntityRepository transferHistoryEntityRepository) {
    this.accountEntityRepository = accountEntityRepository;
    this.transferHistoryEntityRepository = transferHistoryEntityRepository;
  }

  /**
   * Transfer from account to account.
   *
   * @param dto - TransferDto data.
   * @return - TransferResultDto.
   */
  @PutMapping(path = "/bank/createAccount")
  @ResponseBody
  public TransferResultDto createAccount(@RequestBody TransferDto dto) {
//    repository.save(entity);
    return null;
  }


  /**
   * Transfer from account to account.
   *
   * @param dto - TransferDto data.
   * @return - TransferResultDto.
   */
  @PostMapping(path = "/bank/transfer")
  @ResponseBody
  public TransferResultDto transfer(@RequestBody TransferDto dto) {
//    repository.save(entity);
    return null;
  }

  /**
   * Retrieve account balance.
   *
   * @param account - bank account.
   * @return - AccountEntity
   */
  @GetMapping("/bank/{account}/balance")
  @ResponseBody
  public AccountEntity getAccountById(@PathVariable long account) {
    return accountEntityRepository.findByAccountId(account);
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
  public List<TransferHistoryEntity> getHistoryByAccountId(@RequestParam long accountFrom, @RequestParam long accountTo) {
    return transferHistoryEntityRepository.findByAccountFromIsAndAccountToIs(accountFrom, accountTo);
  }

}
