package com.etop.management.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.Contract;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.EtopPage;
import com.etop.management.bean.OpInfoBean;
import com.etop.management.bean.PageContract;
import com.etop.management.bean.ResultType;
import com.etop.management.model.ContractItemBillModel;
import com.etop.management.model.ContractListModel;
import com.etop.management.model.ContractModel;

public interface ContractService {

	Contract getContractById(String id);
	
	/**
	 * 
	 * @param criteria:id,refcontractid,category
	 * @return
	 */
	Contract query(Criteria criteria);
	
	/**
	 * 查找新建合同是否与其他已有合同日期冲突
	 * 0329：不包括已删除合同
	 * @param refCompanyId 取消
	 * @param refRoomId
	 * @param contractCategory
	 * @param contractStartDate
	 * @param contractEndDate
	 * @return
	 */
	List<Contract> queryLeaseListByCompanyAndRoom(String refCompanyId,String refRoomId,int contractCategory, String contractStartDate,String contractEndDate);
	
	/**
	 * 用来判断此公司是否已创建合同
	 * @param refCompanyId
	 * @param refRoomId
	 * @param contractCategory
	 * @param todayStr
	 * @return
	 */
	List<Contract> getContractByCompanyAndCategoryAndRoom(String refCompanyId,String refRoomId,int contractCategory, String todayStr);
	
	/**
	 * 获取公司有效合同列表
	 * @param refCompanyId
	 * @param contractCategory
	 * @param todayStr
	 * @return
	 */
	List<Contract> getActiveContractsByCompanyAndCategory(String refCompanyId,int contractCategory,String todayStr);
	
	
	String saveContract(OpInfoBean op,ContractModel model);
	
	void updateContract(OpInfoBean op,Contract entity);
	
	List<ContractListModel> getContractList(PageContract page);
	
	int getContractListCount(PageContract page);
	
	/**
	 * 根据租赁合同取其他合同
	 * @param ids
	 * @param category
	 * @return
	 */
	List<Contract> getOtherContractByLease(List<String> ids,int category);
	
	/**
	 * 目前不能删除
	 * @param op
	 * @param id
	 * @return
	 */
	int removeContract(OpInfoBean op,String id);
	
	/**
	 * 出帐
	 * @param
	 * @param model
	 * @return -3:no park,-2:no contract item,-1:no contract,0:failed,1:success
	 */
//	int generateBillByContractItemBillModel(ContractItemBillModel model);
	
	/**
	 * 生成园区合同帐单
	 * return map<String,Integer>,key=success,failed
	 */
	ResultType generateBillByPark(String refParkId,String today);
	
	/**
	 * 自动生成所有园区合同帐单(暂不用)
	 */
	void generateAutoBill(String today);

	/**
	 * 手动生成园区押金帐单
	 * @param today
	 */
	void generateManualDepositBill(String today);
	
	/**
	 * 生成押金帐单，包括收入与支出
	 * @param refParkId
	 * @param today
	 * @return
	 */
	ResultType generateDepositBillByPark(String refParkId,String today);
	/**
	 * 按合同出帐 
	 * @param contract
	 * @return map<String,Integer>,key=success,failed
	 */
//	int generateBillByContract(Contract contract);
	/**
	 * 生成合同帐单
	 * @param contract
	 * @param today
	 * @return
	 */
	Map<String,Integer> generateBillByContract(Contract contract,String today);
	
	/**
	 * 终止合同，同时出帐<br>
	 * 若为租赁合同，同时终止所有合同，不包括服务合同，所有正常合同出帐，不包括能源，服务<br>
	 * 其中能源合同另外出帐，能源合同不管是否终止，只要最后结算日期小于合同结束日期，都需要出帐
	 * @param op
	 * @param id
	 * @terminateType 0拒绝1同意
	 * @return 0终止合同个数1生成帐单数
	 */
	List<Integer> terminateContract(OpInfoBean op,Contract contract,int terminateType);
		
	/**
	 * 删除合同
	 * @param op
	 * @param contract
	 * @param delType
	 * @return
	 */
	List<Integer> delContract(OpInfoBean op,Contract contract,int delType);
	/**
	 * 取关联此房间最后有效合同
	 * @param refRoomId
	 * @param contractCategory
	 * @return
	 */
	Contract getLastActiveContractByRefRoomId(String refRoomId,int contractCategory);
	


	EtopPage<ContractListModel> ListbyCompanyId(Map<String, Object> condition,
			int offset, int limit);

	ContractModel getContractByNo(String contractNo);
	
	/**
	 * 财务审核
	 * @param op
	 * @param refContractId
	 * @return
	 */
	int auditContractByFinance(OpInfoBean op,String refContractId,int auditType);
	
	/**
	 * 园长审核
	 * @param op
	 * @param refContractId
	 * @return
	 * @throws Exception 
	 */
	int auditContractByParker(OpInfoBean op,String refContractId,int auditType) throws Exception;
	
}
