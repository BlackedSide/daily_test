package com.qunar.data.analysis.handler;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.qunar.data.analysis.bean.BaseAppcodeInfo;

/**
 * @author jinpeng.fan
 * @date 2023/2/8 10:49 AM
 * description
 */
public abstract class BaseHandler {

    public void readFile() {
        String input = "/Users/fanny/Qunar/技术先进性跑数/base_appcode_info.xlsx";
        EasyExcel.read(input, BaseAppcodeInfo.class, new ReadListener<BaseAppcodeInfo>() {
            @Override
            public void invoke(BaseAppcodeInfo data, AnalysisContext analysisContext) {
                handle(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
    }

    public abstract void handle(BaseAppcodeInfo baseAppcodeInfo);

    public abstract void printResult();
}
