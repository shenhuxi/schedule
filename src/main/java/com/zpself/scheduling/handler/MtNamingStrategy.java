package com.zpself.scheduling.handler;

import org.hibernate.boot.model.naming.EntityNaming;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitJoinTableNameSource;
import org.hibernate.boot.model.source.spi.AttributePath;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;

import java.util.Locale;

/**
 * 自定义JPA逻辑名称生成策略
 *
 * @author lww
 * @date 2018年9月3日19:55:56
 */
public class MtNamingStrategy extends SpringImplicitNamingStrategy {

    /**
     * 由于父级实现
     */
    @Override
    public Identifier determineJoinTableName(ImplicitJoinTableNameSource source) {
        String name = source.getOwningPhysicalTableName() + "_" + fixUpperToUnderscore(source.getAssociationOwningAttributePath().getProperty());
        return this.toIdentifier(name, source.getBuildingContext());
    }

    /**
     * 大部分表名级都调用此方法
     */
    @Override
    protected String transformEntityName(EntityNaming entityNaming) {
        return fixUpperToUnderscore(super.transformEntityName(entityNaming));
    }

    /**
     * 大部分字段名级都调用此方法
     */
    @Override
    protected String transformAttributePath(AttributePath attributePath) {
        return fixUpperToUnderscore(super.transformAttributePath(attributePath));
    }

    /**
     * 把驼峰命名切换成下划线形式
     */
    private String fixUpperToUnderscore(String str) {
        StringBuilder builder = new StringBuilder(str.replace('.', '_'));
        for (int i = 1; i < builder.length() - 1; ++i) {
            if (this.isUnderscoreRequired(builder.charAt(i - 1), builder.charAt(i), builder.charAt(i + 1))) {
                builder.insert(i++, '_');
            }
        }
        return builder.toString().toLowerCase(Locale.ROOT);
    }

    /**
     * 是否包含下划线
     */
    private boolean isUnderscoreRequired(char before, char current, char after) {
        return Character.isLowerCase(before) && Character.isUpperCase(current) && Character.isLowerCase(after);
    }
}
