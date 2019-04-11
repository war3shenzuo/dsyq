package com.etop.management.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.etop.management.bean.Contract;
import com.etop.management.bean.Criteria;
import com.etop.management.bean.PageContract;
import com.etop.management.model.ContractListModel;
import com.etop.management.model.ContractModel;
import com.github.pagehelper.Page;

public interface ContractDao {

	Contract getContractById(String id);
	
	Contract query(Criteria criteria);
	
	List<Contract> queryLeaseListByCompanyAndRoom(Criteria criteria);
	
	/**
	 * 取该房间最后一个有效合同
	 * @param criteria
	 * @return
	 */
	Contract getLastActiveContractByRoom(@Param("refRoomId")String refRoomId,@Param("contractCategory")int contractCategory);
	
	List<Contract> getActiveEnergyContractsByRoom(@Param("refRoomId")String refRoomId,@Param("recordDate")String recordDate);
	
	int createContract(Contract entity);
	
	int updateContract(Contract entity);
	
	List<ContractListModel> getContractList(PageContract page);
	
	int getContractListCount(PageContract page);
	
	/**
	 * 取回相关合同列表
	 * @param leaseId
	 * @return
	 */
	List<Contract> getContractListByLeaseId(@Param("leaseId")String leaseId);
	
	/**
	 * 取回有效的合同列表
	 * @param leaseId
	 * @param contractCategory
	 * @param today
	 * @return
	 */
	List<Contract> getValidContractListByLeaseId(@Param("leaseId")String leaseId,@Param("today")String today);
	
	int deleteContract(@Param("id")String id);

	Contract getContractNum(@Param("parkId") String parkId);
	
	Page<ContractListModel> ListbyCompanyId(Map<String, Object> condition);
	
	ContractModel getContractByNo(String contractNo);
	
	/**
	 * 取回园区中，符合出帐条件的合同列表，根据起止日期
	 * @param refParkId
	 * @param today
	 * @return
	 */
	List<Contract> getContractBillListByPark(@Param("refParkId") String refParkId,@Param("today") String today);
	/**
	 * 取出需要出押金帐的合同<br>
	 * 正常合同:end_date<=today(已结束) and deposit_bill_status=1, 已出收入帐未且出支出账,需出支出 帐；<br>
	 * 		  end_date>today(未开始或正常) and deposit_bill_status=0,并未出账，需出收入账<br>
	 * 终止合同：deposit_bill_status=1，已出收入账且未出支出账
	 * @param refParkId
	 * @param today
	 * @return
	 */
	List<Contract> getContractDepositByPark(@Param("refParkId") String refParkId,@Param("today") String today);
	
	
	
	/**
	 * 根据公司，类别取合同，主要用于检查此公司是否已签订合同
	 * @param refCompanyId
	 * @param contractCategory
	 * @param refRoomId
	 * @return
	 */
	List<Contract> getContractByCompanyAndCategoryAndRoom(@Param("refCompanyId") String refCompanyId,@Param("refRoomId") String refRoomId,@Param("contractCategory") int contractCategory,@Param("todayStr") String todayStr);
	
	/**
	 * 查找有效合同列表
	 * @param refCompanyId
	 * @param contractCategory
	 * @param todayStr
	 * @return
	 */
	List<Contract> getActiveContractsByCompanyAndCategory(@Param("refCompanyId") String refCompanyId,@Param("contractCategory") int contractCategory,@Param("todayStr") String todayStr);

	
	List<Contract> getNormalCompany(String today);
	
	List<Contract> getNormalCompanyTwo(String today);
	
	Contract chaeckNormalCompany(String companyId);
	
	
	List<Contract> getOtherContractByLease(@Param("ids") List<String> ids,@Param("category") int category);
	
	List<Contract> getRoomList(String refCompanyId);
	
	Contract getRoom(@Param("refCompanyId")String refCompanyId,@Param("room")String room);
	
}
