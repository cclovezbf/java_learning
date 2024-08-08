package Jcommand;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Data
public class CmdArgs2 {
    @Parameter(names = {"-s", "--string"}, description = "this is string", help = true, order = 1)
    private String s;

    @Parameter(names = {"-i", "--int"}, description = "this is int ", help = true, order = 2)
    private Integer i;

    @Parameter(names = {"-f", "--float"}, description = "this is float", help = true, order = 3)
    private float f;

    @Parameter(names = {"-d", "--decimal"}, description = "this is decimal", help = true, order = 4)
    private BigDecimal d;
    //注意加 这个
    @Parameter(names = {"-b", "--bool"}, description = "this is bool", help = true,arity = 1, order = 4)
    private boolean b;
    //password=true  这里就是类似与 mysql -uroot -p  然后输密码 和客户端交互
    @Parameter(names = {"-p", "--password"}, description = "this is password", help = true,password = true, order = 4)
    private String p;
    @Parameter(names = "--help", description = "print help message", help = true, order = 0)
    private boolean help;
    //这个通过多个参数去获取
    @Parameter(names = {"--list1","-l1"}, description = "this is list",help = true,order = 5)
    private List<String> list1;
    //这个通过转化去获取
    @Parameter(names = {"--list2","-l2"}, description = "this is list",help = true,listConverter =myListConvert.class,order = 5)
    private List<String> list2;
//-s chenchi -i 1234 -f 123.4567 -d 987.6543 -b false -l1 chenchi -l1 chenchi2 -l2 1,2,3,4  -p cc_password
    public static void main(String[] args) {
        System.out.println(Arrays.toString(args));
        CmdArgs2 cmdArgs = new CmdArgs2();
        JCommander commander = JCommander.newBuilder()
                //程序名
                .programName("CommandTest")
                //参数对象
                .addObject(cmdArgs)
                //创建
                .build();
        //解析参数
        commander.parse(args);
        if (cmdArgs.isHelp()) {
            //打印帮助信息
            commander.usage();
            return;
        }
        //打印转换之后的参数对象
        System.out.println(cmdArgs);
    }

    static class myListConvert implements IStringConverter<List<String>>{
        @Override
        public List<String> convert(String s) {
            String[] split = s.split(",");
            List<String> list = Arrays.asList(split);
            return list ;
        }
    }
}
