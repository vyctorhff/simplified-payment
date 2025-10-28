package br.com.challenge.payment.core.service;

import br.com.challenge.payment.boundary.repository.TransactionRepository;
import br.com.challenge.payment.core.model.StatusTransaction;
import br.com.challenge.payment.core.model.Transaction;
import br.com.challenge.payment.core.model.TransactionRequest;
import br.com.challenge.payment.helpers.model.UserHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProcessPaymentServiceTest {

    @InjectMocks
    private ProcessPaymentService service;

    @Mock
    private FindUserService findUserService;

    @Mock
    private ProcessPaymentPreCondictionsService preCondictionsService;

    @Mock
    private ProcessPaymentPostConditionsService postConditionsService;

    @Mock
    private TransferenceService transferenceService;

    @Mock
    private TransactionRepository repository;

    @Captor
    private ArgumentCaptor<Transaction> captorTransaction;

    @BeforeEach
    void setup() {
    }

    @Test
    void shouldProcessWithTransfer() {
        TransactionRequest request = new TransactionRequest(1, 2, 200.0);

        Mockito.when(findUserService.find(request.payee()))
                .thenReturn(UserHelper.createUserCommon());

        Mockito.when(findUserService.find(request.payer()))
                .thenReturn(UserHelper.createUserCommon(1));

        Transaction transaction = service.process(request);

        assertEquals(StatusTransaction.PAID, transaction.getStatus());

        verify(preCondictionsService).check(captorTransaction.capture());
        Transaction transactionPreCondicional = captorTransaction.getValue();
        assertEquals(StatusTransaction.PAID, transactionPreCondicional.getStatus());
        assertEquals(request.payee(), transactionPreCondicional.getUserSource().getId());
        assertEquals(request.payer(), transactionPreCondicional.getUserTarget().getId());

        verify(transferenceService).process(any());
        verify(postConditionsService).process(any());
    }

    @Test
    void shouldProcessWithTransferCanceled() {
        TransactionRequest request = new TransactionRequest(1, 2, 200.0);

        Mockito.when(findUserService.find(request.payee()))
                .thenReturn(UserHelper.createUserCommon());

        Mockito.when(findUserService.find(request.payer()))
                .thenReturn(UserHelper.createUserCommon());

        Mockito.doAnswer(invocation -> {
            Transaction transaction =invocation.getArgument(0);
            transaction.setStatus(StatusTransaction.CANCELED_OVER_BALANCE);
            return null;
        }).when(preCondictionsService)
                .check(any());

        Transaction transaction = service.process(request);

        assertEquals(StatusTransaction.CANCELED_OVER_BALANCE, transaction.getStatus());

        verify(preCondictionsService, Mockito.only()).check(any());
        verify(transferenceService, Mockito.never()).process(any());
        verify(postConditionsService, Mockito.never()).process(any());
    }
}