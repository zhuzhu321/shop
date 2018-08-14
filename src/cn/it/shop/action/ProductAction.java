package cn.it.shop.action;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.Product;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction<Product> {
	
	public String queryJoinCategory() {
		System.out.println("name:" + model.getName());
		System.out.println("page��" + page);
		System.out.println("rows��" + rows);
		
		//�����洢��ҳ������
		pageMap = new HashMap<String, Object>();
		
		//���ݹؼ��ֺͷ�ҳ�Ĳ�����ѯ��Ӧ������
		List<Product> productList = productService.queryJoinCategory(model.getName(), page, rows);
		pageMap.put("rows", productList); //�洢ΪJSON��ʽ
		//���ݹؼ��ֲ�ѯ�ܼ�¼��
		Long total = productService.getCount(model.getName());
//				System.out.println(total);
		pageMap.put("total", total); //�洢ΪJSON��ʽ
		return "jsonMap";
	}
	
	public void save() {
		//fileUpload�����౻��ȡ�ˣ�uploadFile����ֱ�ӽ���һ��fileImage���󣬷����µ�ͼƬ��
		String pic = fileUpload.uploadFile(fileImage);
		
		model.setPic(pic);
		model.setDate(new Date());
		System.out.println(model);
		//��Ʒ��Ϣ���
		productService.save(model);
	}
	
	public String deleteByIds() {
		System.out.println(ids);
		productService.deleteByIds(ids);
		//���ɾ���ɹ��ͻ�����ִ�У����ǽ�"true"��������ʽ����ǰ̨
		inputStream = new ByteArrayInputStream("true".getBytes());
		return "stream";
	}
	
	public void update() {
		String pic = fileUpload.uploadFile(fileImage);
		model.setPic(pic);
		model.setDate(new Date());
		System.out.println(model);
		//������Ʒ
		productService.update(model);
	}
	
	public String get() {
		request.put("product", productService.get(model.getId()));
		return "detail";
	}
}
