package com.qunar.data.analysis.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author jinpeng.fan
 * @date 2023/3/7 4:04 PM
 * description
 */
public class DiffUtil {

    public static Set<String> diffStringSet(String origin, String current) {
        Set<String> originSet = Sets.newHashSet(Splitter.on(",").trimResults().split(origin));
        Set<String> currentSet = Sets.newHashSet(Splitter.on(",").trimResults().split(current));
        return Sets.difference(currentSet, originSet);
    }

    public static String printSet(Set<String> input) {
        String inputStr = input.toString();
        return inputStr.substring(1, inputStr.length() - 1);
    }

    public static void main(String[] args) {
        String origin = "f_tts_user_labels,f_tts_activity_small,f_x_sargeras,f_qmall_product";
        String current = "f_tts_user_labels,f_inter_block_price_search,f_tts_activity_small,f_x_sargeras,f_qmall_product";
        Set<String> diffRes = diffStringSet(origin, current);
        System.out.println(diffRes.size());
        System.out.println(printSet(diffRes));
    }
}
