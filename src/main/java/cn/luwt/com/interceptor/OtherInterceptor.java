package cn.luwt.com.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cn.luwt.com.pojo.OrderItem;
import cn.luwt.com.pojo.User;
import cn.luwt.com.service.CategoryService;
import cn.luwt.com.service.OrderItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OtherInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
     /**
     * ��ҵ��������������֮ǰ������
     * �������false
     *     �ӵ�ǰ������������ִ��������������afterCompletion(),���˳���������
     * �������true
     *    ִ����һ��������,ֱ�����е���������ִ�����
     *    ��ִ�б����ص�Controller
     *    Ȼ�������������,
     *    �����һ������������ִ�����е�postHandle()
     *    �����ٴ����һ������������ִ�����е�afterCompletion()
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        return true;
 
    }
  
    /**
     * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���
     * ����modelAndView�м������ݣ����統ǰʱ��
     */
 
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler,    
            ModelAndView modelAndView) throws Exception {
        /*�����ǻ�ȡ���ﳵ��һ���ж�������*/
    	HttpSession session = request.getSession();
        User user =(User)  session.getAttribute("user");
        int  cartTotalItemNumber = 0;
        if(null!=user) {
            List<OrderItem> ois = orderItemService.listByUser(user.getId());
            for (OrderItem oi : ois) {
                cartTotalItemNumber+=oi.getNumber();
            }
         
        }
        request.getSession().setAttribute("cartTotalItemNumber", cartTotalItemNumber);
 
    }
  
    /**  
     * ��DispatcherServlet��ȫ����������󱻵���,������������Դ��   
     *   
     * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion()  
     */
      
    public void afterCompletion(HttpServletRequest request,    
            HttpServletResponse response, Object handler, Exception ex)  
    throws Exception {  
            
//        System.out.println("afterCompletion(), �ڷ�����ͼ֮�󱻵���");
    }  
        
} 