/**
 * @author DAHUANG
 * @date 2022/8/22
 */
@Component
public class MyListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //首先获去application上下文
        ApplicationContext applicationContext = contextRefreshedEvent.getApplicationContext();
        //获取对应的service
        UserService userService = applicationContext.getBean(UserService.class);
        User user = userService.getUser();
        //获取application的域对象，将查到的信息放到application域中
        ServletContext application = applicationContext.getBean(ServletContext.class);
        application.setAttribute("user",user);
    }
}
