package com.zpself.scheduling.data.repository;

import com.zpself.scheduling.data.entity.QrtzTriggers;
import com.zpself.scheduling.data.id.SchedNameTriggerNameGroupPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author MingSu
 * @date 2018-08-29
 */
@Repository
public interface QrtzTriggersRepository extends BaseRepository<QrtzTriggers, SchedNameTriggerNameGroupPK> {


    /**
     *
     */
    @Query(value = "SELECT " +
            "QRTZ_JOB_DETAILS.JOB_NAME," +
            "QRTZ_JOB_DETAILS.JOB_GROUP," +
            "QRTZ_JOB_DETAILS.JOB_CLASS_NAME," +
            "QRTZ_CRON_TRIGGERS.CRON_EXPRESSION," +
            "QRTZ_TRIGGERS.TRIGGER_NAME," +
            "QRTZ_TRIGGERS.TRIGGER_GROUP," +
            "QRTZ_TRIGGERS.TRIGGER_STATE " +
            "FROM " +
            "QRTZ_JOB_DETAILS " +
            "JOIN QRTZ_TRIGGERS " +
            "JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP", nativeQuery = true)
    List<Object[]> getJobAndTriggerDetails();

}
