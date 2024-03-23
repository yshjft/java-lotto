package lotto.exception;

public class InValidBonusNumberException extends IllegalArgumentException {
    private static final String WRONG_BONUS_NUMBER_MESSAGE = "(%s): 잘못된 보너스 볼입니다.";

    public InValidBonusNumberException(int bonusNumber) {
        super(String.format(WRONG_BONUS_NUMBER_MESSAGE, bonusNumber));
    }
}
