package cn.it.shop.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.it.shop.model.User;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User> {
	public String login() {
		model = userService.login(model);
		if(model == null) {
			session.put("error", "账户或密码错误");
			return "login";
		} else {
			session.put("user", model);
			return "sucecss";
			
		}
	}
	public String outLogin() {
		session.remove("user");
		session.remove("forder");
        return "index";
	}
}

