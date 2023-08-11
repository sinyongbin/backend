package sin.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChartDTO {
    private String item;
    private int number;

    @Override
    public String toString() {// 그냥 값을 찍어보려고 씀
        return "#ChartDTO item: " + item + ", number: " + number;
    }

}
