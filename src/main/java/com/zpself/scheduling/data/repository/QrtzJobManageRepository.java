package com.zpself.scheduling.data.repository;

import com.zpself.scheduling.data.entity.QrtzJobManage;
import com.zpself.scheduling.data.id.SchedNameTriggerNameGroupPK;
import org.springframework.stereotype.Repository;

/**
 * QrtzJobManagea的处理类
 *
 * @author zengpeng
 * @date 2016/10/31
 */
@Repository
public interface QrtzJobManageRepository extends BaseRepository<QrtzJobManage, SchedNameTriggerNameGroupPK> {
}

