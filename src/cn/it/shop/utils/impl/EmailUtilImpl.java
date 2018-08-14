package cn.it.shop.utils.impl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

import cn.it.shop.utils.EmailUtil;

/**
 * @Description: TODO(��������ʼ��ķ���)
 * @author Ni Shengwu
 *
 */
@Component("emailUtil")
public class EmailUtilImpl implements EmailUtil {
	
	@Override
	public void sendEmail(String emailAddress, String id) {
		// 1. ��½�ʼ��ͻ���(�����Ựsession)
		Properties prop = new Properties();
		Session session = null;
		Message message = null;
		Transport transport = null;
		try {
			prop.setProperty("mail.transport.protocol", "smtp");
			// ������session�Ự
			session = Session.getDefaultInstance(prop);
			// ����debugģʽ�����Է�����Ϣ
			session.setDebug(true);
			// ����һ���ʼ�����
			message = new MimeMessage(session);
			// д��
				message.setSubject("�׹��̳��ʼ�");
			// ��������
			message.setContent("�˿����ã���ӭ����������̳ǣ�����" + id + "��֧���ɹ���", "text/html;charset=utf-8");
			// �����˵�ַ
			message.setFrom(new InternetAddress("soft03_test@sina.com"));			
			transport = session.getTransport();
			// �����ʼ�����������֤��Ϣ
			transport.connect("smtp.sina.com", "soft03_test", "soft03_test");
			
			// �����ռ��˵�ַ���������ʼ�
			transport.sendMessage(message, new InternetAddress[] { new InternetAddress(emailAddress) });
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {			
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}
/*
	public static void main(String[] args) throws Exception {
		//1. ��½�ʼ��ͻ���(�����Ựsession)
		Properties prop = new Properties();
		prop.setProperty("mail.transport.protocol", "smtp");	
		//������session�Ự
		Session session = Session.getDefaultInstance(prop);
		//����debugģʽ�����Է�����Ϣ
		session.setDebug(true);
		//����һ���ʼ�����
		Message message = new MimeMessage(session);
		//д��
		message.setSubject("��ӭ�����ҵ�CSDN������ҳ��");
		//��������
		message.setContent("�����ȥ�����ҵ�CSDN������ҳ��http://blog.csdn.net/eson_15"
				+ "���Ҿ͸����ôô��", "text/html;charset=utf-8");
		//�����˵�ַ
		message.setFrom(new InternetAddress("nishengwus@163.com"));
		Transport transport = session.getTransport();
		//�����ʼ�����������֤��Ϣ
		transport.connect("smtp.163.com", "nishengwus", "nishengwu%&");
		// �����ռ��˵�ַ���������ʼ�
		transport.sendMessage(message, new InternetAddress[]{new InternetAddress("1320873719@qq.com")});
		transport.close();

	}*/
}
