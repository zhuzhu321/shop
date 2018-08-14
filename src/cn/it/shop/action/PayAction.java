package cn.it.shop.action;

import java.util.Map;

import org.apache.struts2.interceptor.ParameterAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;



import cn.it.shop.model.BackData;
import cn.it.shop.model.Forder;
import cn.it.shop.model.SendData;
import cn.it.shop.model.Status;
import cn.it.shop.model.User;
/**
 * Struts�������̣�
 * 1. ��ȡ������ȴ���Action�����ڴ��������ʱ��˳�㴴����Action��
 * 2. ִ��18����������������ִ�гɹ����ٵ���Action�ķ���
 * 3. Action�ķ���ִ����Ϻ��ٵ���18��������
 * �����ȴ���Action-->servletConfig(�õ�parameters)-->modelDriven
 * @author Ni Shengwu
 *
 */
@Controller("payAction")
@Scope("prototype")
public class PayAction extends BaseAction<Object> implements ParameterAware {
	
	private Map<String, String[]> parameters;
	
	@Override
	public Object getModel() {
		if(parameters.get("pd_FrpId") != null) {
			model = new SendData();
		} else {
			model = new BackData();
		}
		return model;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
		
	}
	
	public String goBank() {
		SendData sendData = (SendData)model;
		//1. ��ȫ����:P2 p3 pd Pa��Ҫ��session�л�ȡ
		Forder forder = (Forder) session.get("oldForder");
		User user = (User) session.get("user");
		sendData.setP2_Order(forder.getId().toString()); //�̻�������
		sendData.setP3_Amt(forder.getTotal().toString()); //֧�����
		sendData.setPa_MP(user.getEmail() + "," + user.getPhone()); //�̻���չ��Ϣ
		//2. �Բ�������׷��		
		//3. ���ܻ�ȡǩ��		
		//4. �洢��request����
		payService.saveDataToRequest(request, sendData); //���2,3,4
		//5. ��ת��֧��ҳ��
		
		return "pay";
	}

	public String backBank() {
		BackData backData = (BackData)model;
		System.out.println(model);
		boolean isOK = payService.checkBackData(backData);
		if(isOK) {
			//1. ���¶���״̬,�������Լ��������ݿ��е��������ȥ�ģ���������
			forderService.updateStatusById(Integer.parseInt(backData.getR6_Order()), 2);
			//2. ����user�����ַ�������ʼ�
			String emailAddress = backData.getR8_MP().split(",")[0];
			emailUtil.sendEmail(emailAddress, backData.getR6_Order());
			//3. �����ֻ�����
			String phoneNum = backData.getR8_MP().split(",")[1];
			messageUtil.sendMessage(phoneNum, backData.getR6_Order());
			System.out.println("----success!!----");
			return "index";
		} else {
			System.out.println("----false!!!----");
			return "error";
		}
	}

}
