/**
 * @author DAHUANG
 * @date 2022/8/23
 */
@RestController
@RequestMapping("/listener")
@Slf4j
public class HttpSessionListenerController {

        @GetMapping("/total1")
        public String getTotalUser(HttpServletRequest request){
            Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
            return "当前在线人数："+count;
        }

        @GetMapping("/total2")
        public String getTotalUser(HttpServletRequest request, HttpServletResponse response){
            Cookie cookie;
            try {
                cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(),"utf-8"));
                log.info(request.getSession().getId());
                //设置Cookie有效期 单位：s
                cookie.setMaxAge(20);
                response.addCookie(cookie);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
            return "当前在线人数："+count;
        }


}
