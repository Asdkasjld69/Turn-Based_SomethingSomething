package service;
import java.sql.SQLException;
import java.util.Date;

import javax.security.auth.login.LoginException;

import bean.User;
import database.dao.UserDAO;
import security.exception.ActiveUserException;
import security.exception.RegisterException;
import security.MailSender;
import security.MailUtils;

public class UserService {
	private UserDAO dao = new UserDAO();
	// 注册操作
	public void register(User user) throws RegisterException {
		// 调用dao完成注册操作
		try {
			dao.addUser(user);
			// 发送激活邮件
			String emailMsg = "Dear "+user.getUsername()+",you should click on this:"
					+ "<a href='http://112.64.55.184:8090/Turn-Based_For_Honor/ActiveUserServlet?activeCode="
					+ user.getActiveCode() + "'>&nbsp;ACTIVATE&nbsp;</a>。"
							+ " ASAP.";
			MailUtils.sendMail(user.getEmail(), emailMsg);
			MailSender.getInstance().sendMail(user.getEmail(), emailMsg);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RegisterException("REGISTERATION FAILED");
		}
	}
	// 激活用户
	public void activateUser(String activeCode) throws ActiveUserException {
		try {
			// 根据激活码查找用户
			User user = dao.findUserByActiveCode(activeCode);
			if (user == null) {
				throw new ActiveUserException("ACTIVATION FAILED");
			}
			// 判断激活码是否过期 24小时内激活有效.
			// 1.得到注册时间
			Date registTime = user.getRegistTime();
			// 2.判断是否超时
			long time = System.currentTimeMillis() - registTime.getTime();
			if (time / 1000 / 60 / 60 > 24) {
				throw new ActiveUserException("ACTIVATION EXPIRED");
			}
			// 激活用户，就是修改用户的state状态
			dao.activeUser(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ActiveUserException("ACTIVATION FAILED");
		}
	}
	// 登录操作
	public User login(String username, String password) throws LoginException {
		try {
			//根据登录时表单输入的用户名和密码，查找用户
			User user = dao.findUserByUsernameAndPassword(username, password);
			//如果找到，还需要确定用户是否为激活用户
			if (user != null) {
				// 只有是激活才能登录成功，否则提示“用户未激活”
				if (user.getState() == 1) {
					return user;
				}
				throw new LoginException("NOT ACTIVATED");
			}
			throw new LoginException("WRONG USERNAME/PASSWORD");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new LoginException("SIGNING IN FAILED");
		}
	}
	
	public User findUserById(int id) {
		User user = null;
		try {
			user = dao.findUserById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return user;
	}
	
	public User findUserByUsername(String username) {
		User user = null;
		try {
			user = dao.findUserByUsername(username);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return user;
	}
	
	public User findUserByEmail(String email) {
		User user = null;
		try {
			user = dao.findUserByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return user;
	}
	
	public void editUser(User user) {
		try {
			dao.editUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
