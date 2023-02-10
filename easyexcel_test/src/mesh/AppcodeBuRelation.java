package mesh;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author jinpeng.fan
 * @date 2023/2/6 2:37 PM
 * description
 */
@Getter
@Setter
@EqualsAndHashCode
public class AppcodeBuRelation {

    @ExcelProperty("app_code")
    private String appcode;

    @ExcelProperty("source_url")
    private String source;

    @ExcelProperty("三级部门")
    private String third_bu;

    @ExcelProperty("四级部门")
    private String forth_bu;

    @ExcelProperty("负责人")
    private String owner;
}
