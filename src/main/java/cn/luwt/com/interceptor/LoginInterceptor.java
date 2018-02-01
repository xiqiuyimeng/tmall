package cn.luwt.com.interceptor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cn.luwt.com.pojo.User;
import cn.luwt.com.service.CategoryService;
import cn.luwt.com.service.OrderItemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

public class LoginInterceptor extends HandlerInterceptorAdapter {
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
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws IOException{
		HttpSession session = request.getSession();
		String contextPath = session.getServletContext().getContextPath();
		String[] noNeedAuthPage = new String[]{
				"home","checklogin","register","loginAjax","login","product","category","search"
		};
		String uri=request.getRequestURI();
		uri = StringUtils.remove(uri, contextPath);
		System.out.println(uri);
		if(uri.startsWith("/fore")){
			String method = StringUtils.substringAfterLast(uri, "/fore");
			if(!Arrays.asList(noNeedAuthPage).contains(method)){
				User u = (User) session.getAttribute("user");
				if(null==u){
					response.sendRedirect("loginPage");
					return false;
				}
			}
		}
		return true;
	}

    /**
     * ��ҵ��������������ִ����ɺ�,������ͼ֮ǰִ�еĶ���
     * ����modelAndView�м������ݣ����統ǰʱ��
     */
	public void postHandle(HttpServletRequest request,HttpServletResponse response,Object handler,ModelAndView mmodelAndView) throws Exception{
		
	}
	/**  
     * ��DispatcherServlet��ȫ����������󱻵���,������������Դ��   
     *   
     * �����������׳��쳣ʱ,��ӵ�ǰ����������ִ�����е���������afterCompletion()  
     */
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex) throws Exception{
		
	}
	
}
