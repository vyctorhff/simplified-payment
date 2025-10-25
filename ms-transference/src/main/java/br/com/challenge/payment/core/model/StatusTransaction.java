package br.com.challenge.payment.core.model;

public enum StatusTransaction {
    PAID,
    CANCELED_NOT_UNAUTHORIZED,
    CANCELED_OVER_BALANCE,
    CANCELED_USER_COMMON
}
