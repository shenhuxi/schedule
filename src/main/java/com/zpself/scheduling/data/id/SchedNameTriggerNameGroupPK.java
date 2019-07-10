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
public class SchedNameTriggerNameGroupPK implements Serializable {
        @ApiModelProperty(value = "调度器名")
        @Column(name = "SCHED_NAME")
        private String schedName;
        @ApiModelProperty(value = "触发器名")
        @Column(name = "TRIGGER_NAME")
        private String triggerName;
        @ApiModelProperty(value = "触发器组")
        @Column(name = "TRIGGER_GROUP")
        private String triggerGroup;

        @Override
        public boolean equals(Object o) {
                if (this == o) {
                        return true;
                }
                if (o == null || getClass() != o.getClass()) {
                        return false;
                }
                SchedNameTriggerNameGroupPK that = (SchedNameTriggerNameGroupPK) o;
                return Objects.equals(schedName, that.schedName) &&
                        Objects.equals(triggerName, that.triggerName) &&
                        Objects.equals(triggerGroup, that.triggerGroup);
        }

        @Override
        public int hashCode() {

                return Objects.hash(schedName, triggerName, triggerGroup);
        }
}
