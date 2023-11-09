package com.kevin.lottery.assembler;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

import javax.xml.transform.Source;
import java.lang.annotation.Target;
import java.util.List;
import java.util.stream.Stream;

/** MapStruct 对象映射接口
 * @author wang
 * @create 2023-11-09-13:55
 */
@MapperConfig
public interface IMapping<SOURCE,TARGET> {
    /**
     * 映射同属性名称，正向
     * @param var1
     * @return
     */
    @Mapping(target = "createTime",dateFormat = "yyyy-MM-dd HH:mm:ss")
    TARGET sourceToTarget(SOURCE var1);

    /**
     * 映射同属性名称，反向
     * @param var1
     * @return
     */
    @InheritInverseConfiguration(name = "sourceToTarget")
    SOURCE targetToSource(TARGET var1);

    /**
     * 映射同属性集合，正向
     * @param var1
     * @return
     */
    @InheritConfiguration(name = "sourceToTarget")
    List<TARGET> sourceToTarget(List<SOURCE> var1);

    /**
     * 映射同属性集合，反向
     * @param var1
     * @return
     */
    @InheritConfiguration(name = "targetToSource")
    List<SOURCE> targetToSource(List<TARGET> var1);

    /**
     * 映射流，正向
     * @param stream
     * @return
     */
    List<TARGET> sourceToTarget(Stream<SOURCE> stream);

    /**
     * 映射流，反向
     * @param stream
     * @return
     */
    List<SOURCE> targetToSource(Stream<TARGET> stream);
}
