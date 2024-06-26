package cn.dao.impl;

import cn.dao.BaseDao;
import cn.dao.TransferDao;
import cn.entity.Transfer;

public class TransferDaoImpl extends BaseDao implements TransferDao{

	@Override
	public int insert(Transfer transfer) {
		String sql = "INSERT INTO `bibms_transfer`(`id`,`transOutAcc`,`transAmount`,`transInAcc`)VALUES(?,?,?,?)";
		Object[] params = {transfer.getId(),transfer.getTransOutAcc(),transfer.getTransAmount(),transfer.getTransInAcc()};
		return this.executeUpdate(sql, params);
	}


}
