package lotto;

import lotto.domain.*;
import lotto.domain.lotto.Lottos;
import lotto.domain.lotto.strategy.AutoGeneratingStrategy;
import lotto.domain.lotto.strategy.LottoGeneratingStrategy;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

public class LottoMain {
    public static void main(String[] arguments) throws Exception {
        try {
            PurchaseAmountOfMoney purchaseAmountOfMoney = enteredPurchaseAmountOfMoney();

            NumberOfManualLottoToPurchase numberOfManualLottoToPurchase = enteredNumberOfManualLottoToPurchase(purchaseAmountOfMoney.numberOfLottoToPurchase());

            Lottos manualLottos = enteredManualLottos(numberOfManualLottoToPurchase);

            int numberOfLottoToPurchase = purchaseAmountOfMoney.numberOfLottoToPurchase();
            printNumberOfLottoToPurchase(numberOfLottoToPurchase);

            LottoGeneratingStrategy lottoGeneratingStrategy = new AutoGeneratingStrategy();
            Lottos lottos = lottoGeneratingStrategy.lottos(numberOfLottoToPurchase);
            printLottos(lottos.lottos());

            WinningNumbers winningNumbers = enteredWinningNumbers();
            BonusNumber bonusNumber = enteredBonusNumber(winningNumbers);
            WinningAndBonusNumbers winningAndBonusNumbers = WinningAndBonusNumbers.newWinningAndBonusNumbers(winningNumbers, bonusNumber);

            printStatisticsOfLottos(lottos.statistics(winningAndBonusNumbers, purchaseAmountOfMoney));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
