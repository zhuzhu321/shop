package cn.it.shop.service;

import java.util.List;

import cn.it.shop.model.Product;

public interface ProductService extends BaseService<Product> {
	
	//��ѯ��Ʒ��Ϣ���������
	public List<Product> queryJoinCategory(String type, int page, int size); //ʹ����Ʒ�����Ʋ�ѯ
	//���ݹؼ��ֲ�ѯ�ܼ�¼��
	public Long getCount(String type);
	//����idsɾ��������¼
	public void deleteByIds(String ids);
	//�����ȵ�����ѯ�Ƽ���Ʒ��������ѯǰ4����
	public List<Product> querByCategoryId(int cid);
}
