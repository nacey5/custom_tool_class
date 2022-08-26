/**
 * 自定义事件需要继承 ApplicationEvent 对象
 * @author DAHUANG
 * @date 2022/8/26
 */

@Getter
@Setter
public class MyDefineForMyselfEvent extends ApplicationEvent {
    private User user;

    public MyDefineForMyselfEvent(Object source, User user) {
        super(source);
        this.user=user;
    }

    public MyDefineForMyselfEvent(Object source, Clock clock) {
        super(source, clock);
    }
    
}
