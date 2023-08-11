package sin.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sin.backend.dto.ChartDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RequestMapping("chart")
@Controller
public class ChartController {
    @GetMapping("chart.do")
    public String showViews() {
        return "chart/chart";// chart라는 폴더를 만들고 chart.jsp를 집어넣는다.
    }

    private Random r = new Random();

    @PostMapping("chartData.do")// @GetMapping("chartData.do")로 하면 http://192.168.0.244:8080/chart/chartData.do으로 창에서 JSON을 뽑아낼 수 있다
    @ResponseBody// jsp에  JSON 형식으로 반환한다고 써있기 때문
    public List<ChartDTO> getChartData() {
        List<ChartDTO> list = new ArrayList<ChartDTO>();// 자식 객체로 만들었다
        String[] items = {"봄", "여름", "가을", "겨울"};

        for(int i=0; i<items.length; i++) {
            int number = r.nextInt(100);// 0~99
            ChartDTO dto = new ChartDTO(items[i], number);
            list.add(dto);// JSON객체로 만들어진다
        }
        return list;
    }

}
