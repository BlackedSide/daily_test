import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputData {
    @ExcelProperty("Leader")
    private String leader;

    @ExcelProperty("Priority")
    private String priority;

    @ExcelProperty("AppCode")
    private String appCode;
}
