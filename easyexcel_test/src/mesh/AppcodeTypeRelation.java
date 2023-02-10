package mesh;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

/**
 * @author jinpeng.fan
 * @date 2023/2/6 5:26 PM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class AppcodeTypeRelation {

    @ExcelProperty("app_code")
    private String appcode;

    @ExcelProperty("app_type")
    private String appType;

    @ExcelProperty("线上容器实例数")
    private Integer dockerCount;

    @ExcelProperty("能否下线")
    private String ifOffline;

    @ExcelProperty("level")
    private String level;
}
