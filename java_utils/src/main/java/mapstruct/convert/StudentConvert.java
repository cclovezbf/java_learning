package mapstruct.convert;

import mapstruct.bean.Student;
import mapstruct.bean.StudentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
@Mapper
public interface StudentConvert {

    StudentConvert INSTANCE = Mappers.getMapper( StudentConvert.class );
    @Mapping(source = "sId",target = "id")
    @Mapping(source = "sName",target = "name")
    @Mapping(target = "createDate", expression = "java(new java.util.Date())")
    StudentVo toStudentVo(Student student);

    //数据源来自多个对象
    @Mapping(source = "student1.sId", target = "id")
    @Mapping(source = "student2.sName", target = "name")
    @Mapping(source = "student1.sSex", target = "sSex")
     StudentVo toStudentVo2(Student student1,Student student2);

    //数据源来自普通参数
    @Mapping(source = "student1.sId", target = "id")
    @Mapping(source = "student1.sName", target = "name")
    StudentVo toStudentVo2(Student student1,Integer id);


    void updateStudentVo(Student student, @MappingTarget StudentVo studentVo);

}
