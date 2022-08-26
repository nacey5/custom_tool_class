/**
 * @author DAHUANG
 * @date 2022/8/22
 */
@Service
public class UserService {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 获取用户信息
     * @return
     */
    public User getUser(){
        //实际业务场景下，会从数据库中获取数据
        return new User(10001,"张三","123456");
    }

    /**
     * 自定义监听器的发布事件
     * @return
     */
    public User getUser2(){
        //实际业务场景下，会从数据库中获取数据
        User user = new User(10002, "李四", "123456");
        //发布事件
        MyDefineForMyselfEvent myselfEvent=new MyDefineForMyselfEvent(this,user);
        applicationContext.publishEvent(myselfEvent);
        return user;

    }
}
