package cn.it.shop.service;

import java.util.Map;

import cn.it.shop.model.BackData;
import cn.it.shop.model.SendData;

public interface PayService {

	// �Ѽ��ܺ����Ϣ�洢��requestMap��
	public abstract Map<String, Object> saveDataToRequest(
			Map<String, Object> request, SendData sendData);
	
	//�ѷ��ص����ݽ��м��ܵõ����ģ����봫���������ıȽ�
	public boolean checkBackData(BackData backData);
}