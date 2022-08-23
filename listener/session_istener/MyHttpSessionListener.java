/**
 * Session监听器
 * @author DAHUANG
 * @date 2022/8/23
 */

@Component
public class MyHttpSessionListener implements HttpSessionListener {

        //日志记录
        private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

        //记录用户在线的数量
        public Integer count = 0;

        @Override
        public void sessionCreated(HttpSessionEvent httpSessionEvent) {
            logger.info("=========新用户上线了========");
            count++;
            httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
        }

        @Override
        public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
            logger.info("==========用户下线了=========");
            count--;
            httpSessionEvent.getSession().getServletContext().setAttribute("count",count);
        }

}
