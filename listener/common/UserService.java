/**
 * @author DAHUANG
 * @date 2022/8/22
 */
@Service
public class UserService {
    /**
     * 获取用户信息
     * @return
     */
    public User getUser(){
        //实际业务场景下，会从数据库中获取数据
        return new User(10001,"张三","123456");
    }
}
