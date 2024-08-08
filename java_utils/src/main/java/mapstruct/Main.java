package mapstruct;

import mapstruct.bean.Student;
import mapstruct.bean.StudentVo;
import mapstruct.convert.StudentConvert;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        student.setsId("1");
        student.setsName("cc");
        student.setsSex("male");
//        StudentConvert studentConvert = Mappers.getMapper(StudentConvert.class);
        StudentConvert studentConvert = StudentConvert.INSTANCE;
        StudentVo studentVo = studentConvert.toStudentVo(student);
        System.out.println(studentVo);
        StudentVo studentVo1 = new StudentVo();
        studentConvert.updateStudentVo(student,studentVo1);
        System.out.println(studentVo1);
    }
}
