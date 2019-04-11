package com.etop.management.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etop.management.bean.EtopCompany;
import com.etop.management.bean.EtopCompanyIntention;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.EtopUser;
import com.etop.management.bean.ResultType;
import com.etop.management.dao.EtopAdditionalCompanyDao;
import com.etop.management.dao.EtopCompEmployeesDao;
import com.etop.management.dao.EtopCompanyBusinessDao;
import com.etop.management.dao.EtopCompanyDao;
import com.etop.management.dao.EtopSequenceDao;
import com.etop.management.entity.EtopSequence;
import com.etop.management.model.CompanyModel;
import com.etop.management.service.EtopCompanyService;
import com.etop.management.service.UserService;
import com.etop.management.util.UserInfoUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by Alan.
 *
 * @author 何利庭
 * @DATE 2016/8/28
 */
@Service
public class EtopCompanyServiceImpl implements EtopCompanyService {

    @Autowired
    private EtopCompanyDao etopCompanyDao;

    @Autowired
    private EtopAdditionalCompanyDao etopAdditionalCompanyDao;

    @Autowired
    private EtopCompEmployeesDao etopCompEmployeesDao;

    @Autowired
    private EtopCompanyBusinessDao etopCompanyBusinessDao;

    @Autowired
    private EtopSequenceDao etopSequenceDao;

    @Autowired
    private UserService userService;

    @Override
    public EtopPage<EtopCompany> search(EtopCompany etopCompany, Integer offset, Integer limit, String genre) {
        PageHelper.offsetPage(offset, limit, "updated_at DESC");
        Page<EtopCompany> list = null;

        if(("0").equals(genre) && genre != null){
            etopCompany.setCompanyStatus2("=4");
            list = etopCompanyDao.select(etopCompany);
        }else {
            if(("").equals(etopCompany.getCompanyStatus()) || etopCompany.getCompanyStatus() == null){
                etopCompany.setCompanyStatus2("!=4");
            }
            list = etopCompanyDao.select(etopCompany);
        }

        return new EtopPage<EtopCompany>(list);
    }
    
    @Override
    public EtopPage<EtopCompany> search2(EtopCompany etopCompany, Integer offset, Integer limit, String genre) {
    	PageHelper.offsetPage(offset, limit, "updated_at DESC");
    	Page<EtopCompany> list = null;
    
    		list = etopCompanyDao.select(etopCompany);
    
    	
    	return new EtopPage<EtopCompany>(list);
    }

    @Override
    public ResultType add(EtopCompany newEtopCompany) {
        ResultType resultType = null;
        Date now = new Date();
        Integer count = this.getAndIncrease(UserInfoUtil.getUserInfo().getParkId());
        newEtopCompany.setCompanyCode(UserInfoUtil.getUserParkInfo().getParkCode() + "0000" + count);
        newEtopCompany.setCompanyStatus("1");
        newEtopCompany.setInAt(now);
        newEtopCompany.setCreatedAt(now);
        newEtopCompany.setUpdatedAt(now);
        newEtopCompany.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        newEtopCompany.setParkId(UserInfoUtil.getUserInfo().getParkId());
        EtopCompany isCompanyName = etopCompanyDao.selectCompany(newEtopCompany.getCompanyName(),null);
        if(isCompanyName != null){
            resultType = ResultType.getFail("公司名字已存在,请重新输入!");
        }else {
            etopCompanyDao.insert(newEtopCompany);
            resultType = ResultType.getSuccess("添加公司信息成功", newEtopCompany.getCompanyId());
        }
        return resultType;
    }

    @Override
    public void addInfo(EtopCompanyIntention etopCompanyIntention) {
        EtopCompany etopCompany = conversionInfo(etopCompanyIntention);
        etopCompanyDao.insertInfo(etopCompany);
    }


    @Override
    public void deleteById(EtopCompany etopCompany, List<String> additionalCompanyIds) {
        List<String> companyIds = etopCompany.getCompanyIds();
        etopCompanyDao.deleteById(companyIds);
        etopAdditionalCompanyDao.deleteById(additionalCompanyIds);
        etopCompEmployeesDao.delete(companyIds);
        etopCompanyBusinessDao.deleteByIds(companyIds);
    }

    @Override
    public ResultType updateById(EtopCompany etopCompany ,String genre) {
        ResultType resultType = null;
        Date now = new Date();
        etopCompany.setUpdatedAt(now);
        etopCompany.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        //genre 公司类型  0：意向  1：正式
        if(("0").equals(genre)){  //退园公司入驻
            etopCompany.setInAt(now);
            etopCompany.setCompanyStatus("1");
        }
//
//        EtopCompany isCompanyName = etopCompanyDao.selectCompany(etopCompany.getCompanyName(),etopCompany.getCompanyId());
//
//        if(isCompanyName != null){
//            resultType = ResultType.getFail("公司名字已存在,请重新输入!");
//        }else {
            etopCompanyDao.updateById(etopCompany);
            resultType = ResultType.getSuccess("修改公司信息成功! ");
        return resultType;
    }

    @Override
    public void updateStatus(EtopCompany etopCompany) {
        Date now = new Date();
        etopCompany.setUpdatedAt(now);
        etopCompany.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompany.setOutAt(now);
        etopCompany.setCompanyStatus("4");
        etopCompanyDao.updateById(etopCompany);
    }

    @Override
    public EtopCompany getCompInfoById(String id) {
        return etopCompanyDao.getCompInfoById(id);
    }

    @Override
    public EtopPage<EtopCompany> getCompanyByParkId(EtopCompany etopCompany, Integer offset, Integer limit) {
        PageHelper.offsetPage(offset, limit, "updated_at DESC");
        Page<EtopCompany> page = etopCompanyDao.getCompanyByParkId(etopCompany.getParkIds());
        return new EtopPage<EtopCompany>(page);
    }

    @Override
    public ResultType generatedAccount(String companyId) {
        EtopCompany etopCompany = etopCompanyDao.getCompInfoById(companyId);
        EtopUser etopUser = new EtopUser();
        ResultType resultType = null;
        if(etopCompany != null){
            etopUser.setUserName(etopCompany.getCompanyCode());
            etopUser.setCompanyId(etopCompany.getCompanyId());
            etopUser.setUserType(EtopUser.COMPANY);
            etopUser.setPassWord("123456");
            etopUser.setMobile(etopCompany.getMobile());
            etopUser.setEmail(etopCompany.getEmail());
            etopUser.setParkId(etopCompany.getParkId());
            etopUser.setActivated("1");
            etopUser.setCreateAt(new Date());
            etopUser.setUpdateAt(new Date());
            try {
                resultType = userService.registerUser(etopUser, "888888");
            } catch (Exception e) {
                resultType = ResultType.getFail("账号生成失败!");

                e.printStackTrace();
            }
        }
        resultType.setData(etopUser);
        return resultType;
    }

    @Override
    public Integer selectEmployeesNums(String companyId) {
        return etopCompanyDao.selectEmployeesNums(companyId);
    }

    private EtopCompany conversionInfo(EtopCompanyIntention etopCompanyIntention){
        EtopCompany etopCompany = new EtopCompany();
        Integer count = this.getAndIncrease(UserInfoUtil.getUserInfo().getParkId());
        etopCompany.setCompanyCode(UserInfoUtil.getUserParkInfo().getParkCode() + "0000" + count);
        etopCompany.setCompanyId(etopCompanyIntention.getId());
        etopCompany.setCompanyName(etopCompanyIntention.getCompanyName());
        etopCompany.setCompanyType(etopCompanyIntention.getCompanyType());
        etopCompany.setCompanyStatus("1");
        etopCompany.setCompanyCapital(etopCompanyIntention.getCompanyCapital());
        etopCompany.setBusinessType(etopCompanyIntention.getBusinessType());
        etopCompany.setBeforeseat(etopCompanyIntention.getBeforeseat());
        etopCompany.setContact(etopCompanyIntention.getContact());
        etopCompany.setMobile(etopCompanyIntention.getMobile());
        etopCompany.setCompanyMobile(etopCompanyIntention.getCompanyMobile());
        etopCompany.setCompanyFax(etopCompanyIntention.getCompanyFax());
        etopCompany.setSpareContact(etopCompanyIntention.getSpareContact());
        etopCompany.setSpareMobile(etopCompanyIntention.getSpareMobile());
        etopCompany.setRemarks(etopCompanyIntention.getRemarks());
        etopCompany.setUpdatedAt(new Date());
        etopCompany.setInAt(new Date());
        etopCompany.setUpdatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompany.setParkId(etopCompanyIntention.getParkId());
        etopCompany.setCreatedBy(UserInfoUtil.getUserInfo().getId());
        etopCompany.setCreatedAt(new Date());
        return etopCompany;
    }

    public int getAndIncrease(String parkId) {
        int count = 0;
        EtopSequence sequence = etopSequenceDao.findByParkId(parkId);
        if(sequence == null) {
            sequence = new EtopSequence();
            sequence.setParkId(parkId);
            sequence.setCount(2);
            sequence.setUpdateTime(new Date());
            etopSequenceDao.add(sequence);
            count = count + 1;
        }
        else {
            count = sequence.getCount() + 1;
            etopSequenceDao.increaseCount(parkId);
        }
        return count;
    }


	@Override
	public List<CompanyModel> searchCompany(String parkId, String searchValue) {
		
		return this.etopCompanyDao.searchCompany(parkId, searchValue);
	}

}
