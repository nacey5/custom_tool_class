/**
 * request监听器
 * @author DAHUANG
 * @date 2022/8/23
 */
@Component
@Slf4j
public class MyServletRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        log.info("sessionId 为{}",httpServletRequest.getRequestedSessionId());
        log.info("request url为{}",httpServletRequest.getRequestURL());
        httpServletRequest.setAttribute("name","张三");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        log.info("request end");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequestEvent.getServletRequest();
        log.info("request域中保存的name值为{}",httpServletRequest.getAttribute("name"));
    }


}
