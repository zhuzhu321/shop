package cn.it.shop.action;

import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.FileImage;
import cn.it.shop.service.AccountService;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ForderService;
import cn.it.shop.service.PayService;
import cn.it.shop.service.ProductService;
import cn.it.shop.service.SorderService;
import cn.it.shop.service.UserService;
import cn.it.shop.service.impl.CategoryServiceImpl;
import cn.it.shop.utils.FileUpload;
import cn.it.shop.utils.EmailUtil;
import cn.it.shop.utils.MessageUtil;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/*
 * Strutsִ�����̣��ȴ���Action���ٵ��������������������ʳɹ��ٵ���Action�ķ���
 * ����Ŀ������ʱ��Struts�Ĺ��������Ѿ�����Ӧ�����ö��󣬺����ö����Ӧ��Map�洢����ActionContext��ֵջ��
 * ���ʵ������Ӧ��xxxAware�ӿڣ��ͻ��ActionContext�л�ȡ��Ӧ��Map���д��롣ʵ�������������Ϊ��servletConfig
 * servletConfig�������´��룺�жϵ�ǰʵ��ʲô�ӿڣ����ע����Ӧ�Ķ���
 * if (action instanceof ApplicationAware) {
            ((ApplicationAware) action).setApplication(context.getApplication());
        }
        
   if (action instanceof SessionAware) {
            ((SessionAware) action).setSession(context.getSession());
        }
        
   if (action instanceof RequestAware) {
            ((RequestAware) action).setRequest((Map) context.get("request"));
        }
 */
@Controller("baseAction")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements RequestAware,SessionAware,ApplicationAware,ModelDriven<T> {

	//��װ��ͼƬ��Ϣ����
	protected FileImage fileImage;
	
	//����װ�н�Ҫ�������json��ʽ���ظ�ǰ̨�����ݣ�����Ҫʵ��get����
	protected List<T> jsonList = null;
	
	//��ȡҪɾ����ids��Ҫ��get��set����
	//����������ǰ̨�������ݵģ������������struts��ȡ�ģ�Ȼ��ͨ��������ʽ����ǰ̨������ʵ��get��������
	protected String ids;
	protected InputStream inputStream;
	
	//page��rows�ͷ�ҳ�йأ�pageMap��Ų�ѯ�����ݣ�Ȼ������json��ʽ�õ�
	//page��rowsʵ��get��set������pageMapֻ��Ҫʵ��get�������ɣ���ΪpageMap���ǽ���ǰ̨�����ģ�����struts��ȡ��
	protected Integer page;
	protected Integer rows;
	protected Map<String, Object> pageMap = null;
	
	//service����
	@Resource
	protected CategoryService categoryService;
	@Resource
	protected AccountService accountService;
	@Resource
	protected ProductService productService;
	@Resource
	protected ForderService forderService;
	@Resource
	protected SorderService sorderService;
	@Resource
	protected UserService userService;
	@Resource
	protected PayService payService;
	@Resource 
	protected EmailUtil emailUtil;
	@Resource 
	protected MessageUtil messageUtil;
	
	//�ϴ��ļ�������
	@Resource
	protected FileUpload fileUpload;

	//�����
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;
		
	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	
	//ModelDriven
	protected T model;
	@Override
	public T getModel() {
		ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
		Class clazz = (Class)type.getActualTypeArguments()[0];
		try {
			model = (T)clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}	
		return model;
	}
	
	//get��set����
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Map<String, Object> getPageMap() {
		System.out.println("--getPage--");
		return pageMap;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public List<T> getJsonList() {
		return jsonList;
	}
	public FileImage getFileImage() {
		return fileImage;
	}
	public void setFileImage(FileImage fileImage) {
		this.fileImage = fileImage;
	}
	
}
