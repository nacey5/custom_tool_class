/**
 * @author DAHUANG
 * @date 2022/8/26
 */
@Slf4j
@RestController
@RequestMapping("/listener")
public class MyGetRequestInfo {


    @Resource
    private UserService userService;

    @GetMapping("/diy")
    public String getInfo(HttpServletRequest httpServletRequest){
        User user2 = userService.getUser2();
        log.info("信息为:{}",httpServletRequest.getAttribute("name"));
        return "success";
    }
}
