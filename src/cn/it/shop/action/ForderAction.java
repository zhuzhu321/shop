package cn.it.shop.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Sorder;
import cn.it.shop.model.Status;
import cn.it.shop.model.User;

@Controller("forderAction")
@Scope("prototype")
public class ForderAction extends BaseAction<Forder> {
	
	@Override
	public Forder getModel() {
		model = (Forder) session.get("forder");
		return model;
	}

	//ʵ�ֹ��ﳵ���������빺��������������⹦��
	public String save() {
//		// ��session�еĹ��������ǰ��model����
//		Forder forder = (Forder) session.get("forder");
//		//model.setSorders(forder.getSorders());
//		forder.setAddress(model.getAddress());
//		forder.setName(model.getName());
//		forder.setPhone(model.getPhone());
//		forder.setRemark(model.getRemark());
//		forder.setUser((User)session.get("user"));
//		forder.setStatus(new Status(1));
//		forder.setPost(model.getPost());
//		//�������(��Ҫ��xml����POJO��ע��������)����Ҫsorder����forder
//		//��SorderServiceImpl����׷��sorder.setForder(forder);
//		forderService.save(forder);
		
		model.setUser((User)session.get("user"));
		model.setStatus(new Status(1));
		forderService.save(model);
		
		//��ʱ���ﳵ�Ѿ���⣬��ôԭ��session�еĹ��ﳵ��Ӧ�����
		session.put("oldForder", session.get("forder"));//�Ƚ�ԭ���Ĺ��ﳵ��Ϣ������������Ϊ���渶���ʱ����Ҫ�����Ϣ
		session.put("forder", new Forder());//newһ���µĿչ��ﳵ���൱������˹��ﳵ���������Է����û�����~
		return "bank";
	}
}
