package Jcommand;

import com.beust.jcommander.JCommander;

import java.util.Arrays;

public class ParsArgsDemo {
    // --mode master -h 127.0.0.1 -p 8080 -u root
    public static void main(String[] args) {
        //打印命令行参数
        System.out.println(Arrays.toString(args));
        CmdArgs cmdArgs = new CmdArgs();
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

}
