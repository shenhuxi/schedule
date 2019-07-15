package com.zpself.scheduling.data.util;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Stream;

public class ClassScanner {
    private final List<TypeFilter> includeFilters = new LinkedList();
    private final List<TypeFilter> excludeFilters = new LinkedList();
    private ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
    private MetadataReaderFactory metadataReaderFactory;

    public ClassScanner() {
        this.metadataReaderFactory = new CachingMetadataReaderFactory(this.resourcePatternResolver);
    }

    @SafeVarargs
    public static Set<Class<?>> scan(String[] basePackages, Class... annotations) {
        ClassScanner cs = new ClassScanner();
        int var5;
        if (ArrayUtils.isNotEmpty(annotations)) {
            Class[] var3 = annotations;
            int var4 = annotations.length;

            for(var5 = 0; var5 < var4; ++var5) {
                Class<? extends Annotation> c = var3[var5];
                cs.addIncludeFilter(new AnnotationTypeFilter(c));
            }
        }

        Set<Class<?>> classes = new HashSet();
        String[] var9 = basePackages;
        var5 = basePackages.length;

        for(int var10 = 0; var10 < var5; ++var10) {
            String s = var9[var10];
            classes.addAll(cs.doScan(s));
        }

        return classes;
    }

    @SafeVarargs
    public static Set<Class<?>> scan(String basePackages, Class... annotations) {
        return scan(StringUtils.tokenizeToStringArray(basePackages, ",; "), annotations);
    }

    public void addIncludeFilter(TypeFilter includeFilter) {
        this.includeFilters.add(includeFilter);
    }

    public void addExcludeFilter(TypeFilter excludeFilter) {
        this.excludeFilters.add(0, excludeFilter);
    }

    public void resetFilters() {
        this.includeFilters.clear();
        this.excludeFilters.clear();
    }

    public Set<Class<?>> doScan(String basePackage) {
        HashSet classes = new HashSet();

        try {
            String packageSearchPath = "classpath*:" + ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils.resolvePlaceholders(basePackage)) + "/**/*.class";
            Resource[] resources = this.resourcePatternResolver.getResources(packageSearchPath);
            boolean noInclude = this.includeFilters.size() == 0;
            boolean noExclude = this.excludeFilters.size() == 0;
            Stream.of(resources).forEach((r) -> {
                if (r.isReadable()) {
                    try {
                        MetadataReader metadataReader = this.metadataReaderFactory.getMetadataReader(r);
                        boolean match = noInclude && noExclude || this.matches(metadataReader);
                        if (match) {
                            try {
                                classes.add(Class.forName(metadataReader.getClassMetadata().getClassName()));
                            } catch (ClassNotFoundException var8) {
                                var8.printStackTrace();
                            }
                        }
                    } catch (IOException var9) {
                        var9.printStackTrace();
                    }
                }

            });
            return classes;
        } catch (IOException var7) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", var7);
        }
    }

    private boolean matches(MetadataReader metadataReader) throws IOException {
        Iterator var2 = this.excludeFilters.iterator();

        TypeFilter tf;
        do {
            if (!var2.hasNext()) {
                var2 = this.includeFilters.iterator();

                do {
                    if (!var2.hasNext()) {
                        return true;
                    }

                    tf = (TypeFilter)var2.next();
                } while(tf.match(metadataReader, this.metadataReaderFactory));

                return false;
            }

            tf = (TypeFilter)var2.next();
        } while(!tf.match(metadataReader, this.metadataReaderFactory));

        return false;
    }
    public static void main(String[] args){
        ClassScanner a=new ClassScanner();
        Set<Class<?>> classes = a.doScan("com.matech.quartz.data.job.impl");
        for(Class clazz: classes){
            String name = clazz.getName();
            System.out.println(name);
        }
    }
}

