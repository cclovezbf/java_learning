package Jcommand;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import lombok.Data;

import java.util.Arrays;

/**
 *  <dependency>
 *     <groupId>com.beust</groupId>
 *     <artifactId>jcommander</artifactId>
 *     <version>${jcommand.version}</version>
 * </dependency>
 */
@Data
public class CmdArgs {
    @Parameter(names = {"-m", "--mode"}, description = "link mode", help = true, order = 1)
    private String mode;

    @Parameter(names = {"-h", "--host"}, description = "server host", help = true, order = 2)
    private String host;

    @Parameter(names = {"-p", "--port"}, description = "server port", help = true, order = 3)
    private Integer port;

    @Parameter(names = {"-u", "--user"}, description = "user name", help = true, order = 4)
    private String user;

    @Parameter(names = "--help", description = "print help message", help = true, order = 0)
    private boolean help;
    //    // --mode master -h 127.0.0.1 -p 8080 -u root
    public static void main(String[] args) {
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
