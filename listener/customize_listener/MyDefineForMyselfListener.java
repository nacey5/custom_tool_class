/**
 * 自定义监听器需要实现 ApplicationListener 接口
 * @author DAHUANG
 * @date 2022/8/26
 */
@Component
@Slf4j
public class MyDefineForMyselfListener implements ApplicationListener<MyDefineForMyselfEvent> {

    @Override
    public void onApplicationEvent(MyDefineForMyselfEvent event) {
        //获取事件信息
        User user=event.getUser();
        log.info("用户名:{}",user.getUsername());
        log.info("密码:{}",user.getPwd());
    }
}
