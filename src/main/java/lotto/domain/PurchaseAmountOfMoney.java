package lotto.domain;

import lotto.Validator;

import java.math.BigDecimal;

import static java.math.RoundingMode.DOWN;

public class PurchaseAmountOfMoney {
    private static final String WRONG_PURCHASE_AMOUNT_MESSAGE = "잘못된 구입금액입니다.";
    private static final int LOTTO_PRICE = 1000;

    private final int amountOfMoney;

    private PurchaseAmountOfMoney(String amountOfMoneyInput) {
        validateAmountOfMoney(amountOfMoneyInput);
        this.amountOfMoney = Integer.parseInt(amountOfMoneyInput);
    }

    public static PurchaseAmountOfMoney valueOf(String amountOfMoneyInput) {
        return new PurchaseAmountOfMoney(amountOfMoneyInput);
    }
    
    private void validateAmountOfMoney(String amountOfMoneyInput) {
        if (!Validator.isPositiveInteger(amountOfMoneyInput) || Integer.parseInt(amountOfMoneyInput) < LOTTO_PRICE) {
            throw new IllegalArgumentException(WRONG_PURCHASE_AMOUNT_MESSAGE);
        }
    }

    public int numberOfLottoToPurchase() {
        return amountOfMoney / LOTTO_PRICE;
    }
    
    public double rateOfReturn(int winningMoney) {
        double realUsedAmountOfMoney = (amountOfMoney - (amountOfMoney % LOTTO_PRICE));
        BigDecimal rateOfReturn = BigDecimal.valueOf(winningMoney / realUsedAmountOfMoney);
        return rateOfReturn.setScale(2, DOWN).doubleValue();
    }
}
