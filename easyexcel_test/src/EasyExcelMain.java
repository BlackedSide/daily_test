import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

public class EasyExcelMain {
    public static void main(String[] args) {
        Map<String, Map<String, Set<String>>> map = Maps.newHashMap();
        String input = "/Users/fanny/Downloads/appCode.xls";
        EasyExcel.read(input, ExcelData.class, new ReadListener<ExcelData>() {
            @Override
            public void invoke(ExcelData data, AnalysisContext analysisContext) {
                if ("王玉峰".equals(data.getDirector())) {
                    handleMap(map, data);
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {

            }
        }).sheet().doRead();
        List<OutputData> outputList = Lists.newArrayList();
        System.out.println("P4");
        for (Map.Entry<String, Map<String, Set<String>>> owner : map.entrySet()) {
            for (Map.Entry<String, Set<String>> other : owner.getValue().entrySet()) {
                for (String appCode : other.getValue()) {
//                    OutputData data = OutputData.builder()
//                            .leader(owner.getKey())
//                            .priority(other.getKey())
//                            .appCode(appCode)
//                            .build();
//                    outputList.add(data);
                    if ("梁章坪".equals(owner.getKey()) && "P4".equals(other.getKey())) {
                        System.out.println("\"" + appCode + "\",");
                    }
                }
            }
        }
//        String output = "/Users/fanny/Downloads/output.xls";
//        EasyExcel.write(output, OutputData.class).sheet().doWrite(outputList);
    }

    private static void handleMap(Map<String, Map<String, Set<String>>> map, ExcelData data) {
        String appCode = data.getAppCode();
        String owner = data.getOwner();
        String other = data.getOther();
        if (map.get(owner) == null) {
            map.put(owner, new HashMap<>());
            map.get(owner).put(other, new HashSet<>());
        } else if (map.get(owner) != null && map.get(owner).get(other) == null) {
            map.get(owner).put(other, new HashSet<>());
        }
        map.get(owner).get(other).add(appCode);
    }
}
