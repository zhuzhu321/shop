package cn.it.shop.service;

import cn.it.shop.model.Forder;
import cn.it.shop.model.Product;
import cn.it.shop.model.Sorder;
import cn.it.shop.model.User;

public interface UserService extends BaseService<User> {
	//�û���½���ɹ����ظ�User
	public User login(User user);
}
