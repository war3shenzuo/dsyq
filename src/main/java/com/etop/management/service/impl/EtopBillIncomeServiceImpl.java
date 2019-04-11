package com.etop.management.service.impl;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopPage;
import com.etop.management.dao.EtopBillIncomeDao;
import com.etop.management.entity.EtopBillIncome;
import com.etop.management.service.EtopBillIncomeService;
import com.github.pagehelper.PageHelper;

/**
 * <b>功能：</b>EtopBillIncomeService<br>
 */
@Service("etopBillIncomeService")
public class EtopBillIncomeServiceImpl implements EtopBillIncomeService {

	@Inject
	private EtopBillIncomeDao etopBillIncomeDao;
	
	@Override
	public EtopPage<EtopBillIncome> list(Map<String, Object> map, int offset, int limit) {
		PageHelper.offsetPage(offset, limit, "pay_time DESC");
		return new EtopPage<>(etopBillIncomeDao.list(map));
	}

	@Override
	public int add(EtopBillIncome etopBillIncome) {
		return etopBillIncomeDao.add(etopBillIncome);
	}

	@Override
	public int delete(String incomeId) {
		return etopBillIncomeDao.delete(incomeId);
	}

	@Override
	public int deletes(String[] incomeIds) {
		return etopBillIncomeDao.deletes(incomeIds);
	}
}
