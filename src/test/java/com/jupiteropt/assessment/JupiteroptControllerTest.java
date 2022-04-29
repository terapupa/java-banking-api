package com.jupiteropt.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.jupiteropt.assessment.controller.JupiteroptController;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JupiteroptControllerTest {
  @Mock
  AccountEntityRepository accountEntityRepository;
  @Mock
  CustomerEntityRepository customerEntityRepository;
  @Mock
  TransferHistoryEntityRepository transferHistoryEntityRepository;

  JupiteroptController controller;

  @BeforeEach
  void before() {
    controller = new JupiteroptController(accountEntityRepository,
        transferHistoryEntityRepository, customerEntityRepository);
  }

  @Test
  void creteAccountTest() {
    when(accountEntityRepository.save(any())).thenReturn(new AccountEntity());
    when(customerEntityRepository
        .findByFirstNameAndLastName(anyString(), anyString()))
        .thenReturn(Optional.of(new CustomerEntity("f", "l")));
    CreateAccountDto dto = new CreateAccountDto("first", "last", 0, 10);
    AccountEntity entity = controller.createAccount(dto).getBody();
    assertNotNull(entity);
  }

  @Test
  void creteAccountExceptionTest() {
    assertThrows(AppException.class, () -> controller.
        createAccount(new CreateAccountDto("", "last", 0, 10)));
    assertThrows(AppException.class, () -> controller.
        createAccount(new CreateAccountDto(null, "last", 0, 10)));
    assertThrows(AppException.class, () -> controller.
        createAccount(new CreateAccountDto("first", "", 0, 10)));
    assertThrows(AppException.class, () -> controller.
        createAccount(new CreateAccountDto("first", null, 0, 10)));
    assertThrows(AppException.class, () -> controller.
        createAccount(new CreateAccountDto("first", "last", 0, -1)));
  }

  @Test
  void transferTest() {

    TransferHistoryEntity entity = controller.
        transfer(new TransferDto(1, 2, 10)).getBody();
//    String guid = "test";
//    when(jiobitRepository.findByGuid(any())).thenReturn(List.of(new JiobitEntity(guid,1,1,1,1)));
//    List<JiobitEntity> entity = controller.getByGuid(guid);
//    assertEquals(entity.size(), 1);
    assertNotNull(entity);
  }

  @Test
  void getAccountByIdTest() {
    when(accountEntityRepository.findByAccountId(any())).thenReturn(Optional.of(new AccountEntity()));
    AccountEntity entity = controller.getAccountById(1).getBody();
    assertNotNull(entity);
  }

  @Test
  void getHistoryByAccountIdTest() {
    when(transferHistoryEntityRepository.findByAccountFromOrAccountTo(1, 1))
        .thenReturn(List.of(new TransferHistoryEntity(1L,1,1,1)));
    List<TransferHistoryEntity> entity = controller.getHistoryByAccountId(1).getBody();
    assertEquals(entity.size(), 1);
  }

}
