/**
 * @author DAHUANG
 * @date 2022/8/23
 */
@RestController
@RequestMapping("/listener")
public class ServletRequestListenerController {
    @GetMapping("/request")
    public String getRequestInfo(HttpServletRequest httpServletRequest){
        System.out.println("requestListener中的初始化数据："+httpServletRequest.getAttribute("name"));
        return "成功";
    }
}
