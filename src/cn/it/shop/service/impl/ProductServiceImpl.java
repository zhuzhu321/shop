package cn.it.shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.it.shop.model.Category;
import cn.it.shop.model.Product;
import cn.it.shop.service.CategoryService;
import cn.it.shop.service.ProductService;
/**
 * @Description TODO��ģ�������ҵ���߼���
 * @author Ni Shengwu
 *
 */
@SuppressWarnings("unchecked")
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements ProductService {

	@Override
	public List<Product> queryJoinCategory(String name, int page, int size) {
		String hql = "from Product p left join fetch p.category where p.name like :name";
		return getSession().createQuery(hql)
				.setString("name", "%" + name + "%")
				.setFirstResult((page-1) * size) //�ӵڼ�����ʼ��ʾ
				.setMaxResults(size) //��ʾ����
				.list();
	}
	
	@Override
	public Long getCount(String name) {
		String hql = "select count(p) from Product p where p.name like :name";
		return (Long) getSession().createQuery(hql)
			.setString("name", "%" + name + "%")
			.uniqueResult(); //����һ����¼:�ܼ�¼��
	}

	@Override
	public void deleteByIds(String ids) {
		String hql = "delete from Product p where p.id in (" + ids + ")";
		getSession().createQuery(hql).executeUpdate();
		//��Ӧ�ķ������α������ƬҲ��ɾ������
		//����
	}

	@Override
	public List<Product> querByCategoryId(int cid) {
		String hql = "from Product p join fetch p.category "
				+ "where p.commend=true and p.open=true and p.category.id=:cid order by p.date desc";
		return getSession().createQuery(hql)
			.setInteger("cid", cid)
			.setFirstResult(0)
			.setMaxResults(4)
			.list();
	}

}
