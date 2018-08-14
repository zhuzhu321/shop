package cn.it.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// �жϵ�ǰsession�Ƿ����û���Ϣ
		if(req.getSession().getAttribute("user") == null) {
			//���浱ǰ�ͻ���Ҫȥ��url��ַ
			String goURL = req.getServletPath();//����û���Ҫȥ�ĵ�ַ
			String param = req.getQueryString(); //��õ�ַ��Я���Ĳ���
			if(param != null) {
				goURL = goURL + "?" + param; //����ƴ�������ַ+����
			}
			//�ѵ�ǰ�ͻ���Ҫ���ʵĵ�ַ���洢��session��
			req.getSession().setAttribute("goURL", goURL);
			
			//�Ƿ�������ת����½ҳ��
			req.getSession().setAttribute("error", "�Ƿ��������¼��");
			res.sendRedirect(req.getContextPath() + "/ulogin.jsp");
		} else {
			//�������һ������������ת������ֱ�ӵ�Ŀ��ҳ��
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub

	}

}
