package lotto.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Rank {
    FIRST(6, 2_000_000_000, (matchCount, isBonusMatched) -> matchCount == 6),
    SECOND(5, 30_000_000, (matchCount, isBonusMatched) -> matchCount == 5 && isBonusMatched),
    THIRD(5, 1_500_000, (matchCount, isBonusMatched) -> matchCount == 5 && !isBonusMatched),
    FOURTH(4, 50_000, (matchCount, isBonusMatched) -> matchCount == 4),
    FIFTH(3, 5_000, (matchCount, isBonusMatched) -> matchCount == 3),
    MISS(0, 0, (matchCount, isBonusMatched) -> matchCount <= 2);

    private final int matchCount;
    private final int winningMoney;
    private final BiFunction<Integer, Boolean, Boolean> findCondition;

    Rank(int matchCount, int winningMoney, BiFunction<Integer, Boolean, Boolean> findCondition) {
        this.matchCount = matchCount;
        this.winningMoney = winningMoney;
        this.findCondition = findCondition;
    }

    public static Rank findRank(int matchCount, boolean isBonusMatched) {
        return Arrays.stream(Rank.values())
                .filter(rank -> checkRankFindCondition(rank, matchCount, isBonusMatched))
                .findFirst()
                .orElse(MISS);
    }

    private static boolean checkRankFindCondition(Rank rank, int matchCount, boolean isBonusMatched) {
        return rank.findCondition
                .apply(matchCount, isBonusMatched);
    }

    public int matchCount() {
        return matchCount;
    }

    public int winningMoney() {
        return winningMoney;
    }
}
