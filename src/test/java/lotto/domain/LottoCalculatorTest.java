package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LottoCalculatorTest {
    @DisplayName("나의 로또번호와 지난주 당첨번호를 통해 수익률을 계산할 수 있다.")
    @Test
    void calculate() {
        List<Lotto> lottos = new ArrayList<>();
        String lastWeekNumbers = "1,2,3,11,22,33";
        int buyingCount = 1;

        List<LottoNumber> numbers = new ArrayList<>();
        numbers.add(new LottoNumber(1));
        numbers.add(new LottoNumber(2));
        numbers.add(new LottoNumber(3));
        numbers.add(new LottoNumber(4));
        numbers.add(new LottoNumber(5));
        numbers.add(new LottoNumber(6));

        lottos.add(new Lotto(numbers));

        LottoMatcher lottoMatcher = new LottoMatcher(lottos, lastWeekNumbers);
        Map<LottoRank, Integer> ranks = lottoMatcher.matchWinning();

        LottoCalculator lottoCalculator = new LottoCalculator();
        double rateOfReturn = lottoCalculator.requestRateOfReturn(ranks, buyingCount);

        assertEquals(5.0, rateOfReturn);
    }
}
