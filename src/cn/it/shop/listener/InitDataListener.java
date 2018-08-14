package cn.it.shop.listener;

import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.it.shop.utils.FileUpload;
import cn.it.shop.utils.impl.ProductTimerTask;
/**
 * @Description: TODO(������Ŀ������ʱ�����ݳ�ʼ��)
 * @author Ni Shengwu
 *
 */
//@Component //��������web������������tomcatʵ�����ģ�����Springʵ�����ġ����ܷŵ�Spring��
public class InitDataListener implements ServletContextListener {
	
	private ApplicationContext context = null;
	private ProductTimerTask productTimerTask = null;
	private FileUpload fileUpload = null;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// �������һ�����Լ���productService������Spring�����е�service��ʵ������2�Σ���Ϊ�����ּ�����һ�Ρ�����ȡ��
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//		productService = (ProductService) context.getBean("productService");
//		System.out.println(productService);
//		
		// �������������Ŀ������ʱ����Spring�����ļ�ͨ��Spring�ļ��������أ��洢��ServletContext�У�����ֻҪ��ServletContext�л�ȡ���ɡ�
		// �˷�����key�Ƚϳ��������׼�ס
//		ApplicationContext context = (ApplicationContext) event.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
//		productService = (ProductService) context.getBean("productService");
//		System.out.println(productService);
		
		// �����������ͨ����������ؼ���
		context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());				
		productTimerTask = (ProductTimerTask) context.getBean("productTimerTask");//
		//�����ö��󽻸�productTimerTask
		productTimerTask.setApplication(event.getServletContext());
		//ͨ�����ö�ʱ��������ҳ������ÿ��һСʱͬ��һ�Σ�����Ϊ�ػ��̣߳�
		new Timer(true).schedule(productTimerTask, 0, 1000*60*60);//ÿ��һСʱִ��һ��productTimerTask
		
		//���洢����ͼƬ������ŵ�application�У���Ŀ������ʱ�����
		fileUpload = (FileUpload) context.getBean("fileUpload");
		event.getServletContext().setAttribute("bankImageList", fileUpload.getBankImage());
	}

}
