package system;

import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import org.junit.Test;
import oshi.hardware.ComputerSystem;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class OshiDemo {
    public static void main(String[] args) {

    }

    @Test
    public void testMem() {
        GlobalMemory memory = OshiUtil.getMemory();
        long total =memory.getTotal();
        System.out.println(total);
        long available = memory.getAvailable();
    }
    @Test
    public void testCpu() {
        CpuInfo cpuInfo = OshiUtil.getCpuInfo();
        System.out.println(cpuInfo);
        Integer cpuNum = cpuInfo.getCpuNum();
        String cpuModel = cpuInfo.getCpuModel();
        double free = cpuInfo.getFree();
    }
    @Test
    public void test1() {
        HardwareAbstractionLayer hardware = OshiUtil.getHardware();
        System.out.println(hardware);
        ComputerSystem computerSystem = hardware.getComputerSystem();
        System.out.println(computerSystem);
        System.out.println(hardware.getProcessor());

    }

}
