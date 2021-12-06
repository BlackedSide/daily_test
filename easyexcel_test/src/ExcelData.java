import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ExcelData {
    @ExcelProperty("app_code")
    private String appCode;

    @ExcelProperty("owner")
    private String owner;

    @ExcelProperty("other")
    private String other;

    @ExcelProperty("director")
    private String director;
}
