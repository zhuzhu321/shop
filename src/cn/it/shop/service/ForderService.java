package cn.it.shop.service;

import java.math.BigDecimal;

import cn.it.shop.model.Forder;

public interface ForderService extends BaseService<Forder> {
	//���㹺���ܼ۸�
	public BigDecimal cluTotal(Forder forder);
	//���ݶ�����ţ����¶���״̬
	public void updateStatusById(int id, int sid);
}
