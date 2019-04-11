package com.etop.management.constant;

import com.etop.management.bean.ProfitLoss;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
public abstract class Constants {

    public static final String CHARSET_UTF8_NAME = "UTF-8";

    public static final int PAGE_START = 0; // 默认分页第一页

    public static final String PAGE_SIZE_DEFAULT_STRING = "10"; // 默认分页大小

    public static final int PAGE_SIZE_DEFAULT = 10; // 默认分页大小

    public static final String PAGE_START_STRING = "0"; // 默认分页第一页

    /**
     * 日期格式化定义
     **/

    public static final String DATE_FMT_PATTERN_YMDHMS = "YYYY-MM-dd HH:mm:ss";

    public static final String DATE_FMT_PATTERN_YMD = "YYYY-MM-dd";

    public static final String DATE_FMT_PATTERN_YM = "YYYY-MM";

    /**
     * 培训页面显示题目数
     */
    public static final int ETOP_QUESTION_BANK_NUM = 50;

    /**
     * 题目标准分数
     */
    public static final int EXAMINATION_SCORE = 100;

    /**
     * 收支报表字段翻译
     * @param profitLoss
     * @return
     */
    public static String translateItems(ProfitLoss profitLoss){
        switch (profitLoss.getItems()) {
            case -2:
                return "支出合计";
            case -1:
                return "收益合计";
            case 0:
                return "收支合计";
            case 1:
                return "合同收益";
            case 2:
                return "服务收益";
            case 3:
                return "其他收益";
            case 4:
                return "合同支出";
            case 5:
                return "能源支出";
            case 6:
                return "人事费";
            case 7:
                return "业务费";
            case 8:
                return "管理费";
            case 9:
                return "服务支出";
            default:
                return "未知";
        }
    }

    public static String translateFine(ProfitLoss profitLoss){
        if(profitLoss.getItems() == 1){
            switch (profitLoss.getFine()) {
                case 1:
                    return "租赁合同";
                case 2:
                    return "服务合同";
                case 3:
                    return "物业合同";
                case 4:
                    return "能源合同";
            }
        }else if(profitLoss.getItems() == 2){
            switch (profitLoss.getFine()) {
                case 1:
                    return "园区服务";
            }
        }else if(profitLoss.getItems() == 3){
            switch (profitLoss.getFine()) {
                case 1:
                    return "奖励补贴";
                case 2:
                    return "快递";
                case 3:
                    return "其他";
            }
        }else if(profitLoss.getItems() == 4){
            switch (profitLoss.getFine()) {
                case 1:
                    return "外包合同";
            }
        }else if(profitLoss.getItems() == 5){
            switch (profitLoss.getFine()) {
                case 1:
                    return "电费";
                case 2:
                    return "水费";
                case 3:
                    return "燃气费";
                case 4:
                    return "空调费";
                default:
                    return "其他";
            }
        }else if(profitLoss.getItems() == 6){
            switch (profitLoss.getFine()) {
                case 1:
                    return "工资";
                case 2:
                    return "保险";
                case 3:
                    return "公积金";
                case 4:
                    return "奖金";
                case 5:
                    return "补贴";
                case 6:
                    return "福利";
                default:
                    return "其他";
            }
        }else if(profitLoss.getItems() == 7){
            switch (profitLoss.getFine()) {
                case 1:
                    return "广告宣传";
                case 2:
                    return "业务招待";
                default:
                    return "其他";
            }
        }else if(profitLoss.getItems() == 8){
            switch (profitLoss.getFine()) {
                case 1:
                    return "交通费";
                case 2:
                    return "差旅费";
                case 3:
                    return "通讯费";
                case 4:
                    return "办公费";
                case 5:
                    return "维修费";
                default:
                    return "其他";
            }
        }else if(profitLoss.getItems() == 9){
            switch (profitLoss.getFine()) {
                case 1:
                    return "服务支出";
            }
        }
        return "";
    }

}
