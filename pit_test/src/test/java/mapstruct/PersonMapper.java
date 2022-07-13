package mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author jinpeng.fan
 * @date 2022/7/13 11:58 AM
 * description
 */
@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person deepCopy(Person person);

}
