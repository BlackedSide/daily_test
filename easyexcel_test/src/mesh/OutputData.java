package mesh;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 * @author jinpeng.fan
 * @date 2023/2/6 3:04 PM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class OutputData {

    @ExcelProperty("domain")
    private String domain;

    @ExcelProperty("appcode")
    private String appcode;

    @ExcelProperty("third_bu")
    private String thirdBu;

    @ExcelProperty("forth_bu")
    private String forthBu;
}
