package com.qunar.data.analysis.filter;

import com.qunar.data.analysis.bean.AppReleaseInfo;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;
import com.qunar.data.analysis.bean.ReleaseCountEnum;
import com.qunar.data.analysis.bean.StatisticMonthEnum;
import com.qunar.data.analysis.storage.MatchStorage;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 2:30 PM
 * description
 */
public class ReleaseFilter implements BaseFilter {

    private final ReleaseCountEnum releaseCount;
    private final StatisticMonthEnum statisticMonthEnum;

    public ReleaseFilter(ReleaseCountEnum releaseCount, StatisticMonthEnum statisticMonthEnum) {
        this.releaseCount = releaseCount;
        this.statisticMonthEnum = statisticMonthEnum;
    }

    @Override
    public Boolean isMatch(BaseAppcodeInfo baseAppcodeInfo) {
        switch (statisticMonthEnum) {
            case HALF_YEAR:
                return judgeHalfYear(baseAppcodeInfo);
            case THIRTEEN_MONTHS:
                return judgeThirteenMonths(baseAppcodeInfo);
            default:
                return false;
        }
    }

    private Boolean judgeHalfYear(BaseAppcodeInfo baseAppcodeInfo) {
        switch (releaseCount) {
            case OVER_TEN:
                return count6Months(baseAppcodeInfo.getAppcode()) > 10 * 6;
            case OVER_FIVE:
                return count6Months(baseAppcodeInfo.getAppcode()) >= 5 * 6;
            case BETWEEN_ZERO_TO_FIVE:
                return count6Months(baseAppcodeInfo.getAppcode()) < 5 * 6 && count6Months(baseAppcodeInfo.getAppcode()) > 0;
            case EQUAL_ZERO:
                return count6Months(baseAppcodeInfo.getAppcode()) == 0;
            case OVER_ZERO:
                return count6Months(baseAppcodeInfo.getAppcode()) > 0;
            case TOTAL:
                return true;
            default:
                return false;
        }
    }

    private Boolean judgeThirteenMonths(BaseAppcodeInfo baseAppcodeInfo) {
        switch (releaseCount) {
            case OVER_TEN:
                return count13Months(baseAppcodeInfo.getAppcode()) > 10 * 13;
            case OVER_FIVE:
                return count13Months(baseAppcodeInfo.getAppcode()) >= 5 * 13;
            case BETWEEN_ZERO_TO_FIVE:
                return count13Months(baseAppcodeInfo.getAppcode()) < 5 * 13 && count13Months(baseAppcodeInfo.getAppcode()) > 0;
            case EQUAL_ZERO:
                return count13Months(baseAppcodeInfo.getAppcode()) == 0;
            case TOTAL:
                return true;
            default:
                return false;
        }
    }

    private Integer count13Months(String appcode) {
        AppReleaseInfo releaseInfo = MatchStorage.appReleaseMap.get(appcode);
        if (releaseInfo == null) {
            return 0;
        }
        return releaseInfo.getJan23() + releaseInfo.getDec22() + releaseInfo.getNov22()
                + releaseInfo.getOct22() + releaseInfo.getSep22() + releaseInfo.getAug22()
                + releaseInfo.getJuly22() + releaseInfo.getJun22() + releaseInfo.getMar22()
                + releaseInfo.getApr22() + releaseInfo.getMar22() + releaseInfo.getFeb22() + releaseInfo.getJan22();
    }

    private Integer count6Months(String appcode) {
        AppReleaseInfo releaseInfo = MatchStorage.appReleaseMap.get(appcode);
        if (releaseInfo == null) {
            return 0;
        }
        return releaseInfo.getJan23() + releaseInfo.getDec22() + releaseInfo.getNov22()
                + releaseInfo.getOct22() + releaseInfo.getSep22() + releaseInfo.getAug22();
    }
}
