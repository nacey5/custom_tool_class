/**
 * @author DAHUANG
 * @date 2022/8/22
 */
@RestController
@RequestMapping("/listener")
public class UserController {
    @GetMapping("/user")
    public User getUser(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        User user = (User) application.getAttribute("user");
        return user;
    }
}
