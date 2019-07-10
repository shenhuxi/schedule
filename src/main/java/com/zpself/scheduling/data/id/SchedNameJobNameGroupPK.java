package com.zpself.scheduling.data.id;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class SchedNameJobNameGroupPK implements Serializable {
        @ApiModelProperty(value = "调度器名")
        @Column(name = "SCHED_NAME")
        private String schedName;
        @ApiModelProperty(value = "任务名")
        @Column(name = "JOB_NAME")
        private String jobName;
        @ApiModelProperty(value = "任务组")
        @Column(name = "JOB_GROUP")
        private String jobGroup;

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                SchedNameJobNameGroupPK that = (SchedNameJobNameGroupPK) o;
                return Objects.equals(schedName, that.schedName) &&
                        Objects.equals(jobName, that.jobName) &&
                        Objects.equals(jobGroup, that.jobGroup);
        }

        @Override
        public int hashCode() {

                return Objects.hash(schedName, jobName, jobGroup);
        }
}
