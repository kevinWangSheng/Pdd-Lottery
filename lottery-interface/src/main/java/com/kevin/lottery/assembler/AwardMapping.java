package com.kevin.lottery.assembler;

import com.kevin.domain.strategy.model.vo.DrawAwardVO;
import com.kevin.lottery.rpc.dto.AwardDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/** 对象转换配置
 * @author wang
 * @create 2023-11-09-14:15
 */
@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AwardMapping  extends IMapping<DrawAwardVO, AwardDto> {

    @Mapping(source = "uId",target = "userId")
    @Override
    AwardDto sourceToTarget(DrawAwardVO var1);

    @Override
    DrawAwardVO targetToSource(AwardDto var1);
}
