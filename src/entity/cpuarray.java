package entity;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25/025.
 */
public class cpuarray {
    public static ArrayList<CPU> CPUarray() throws SigarException
    {
        ArrayList<CPU> acpu=new ArrayList<>();
        Sigar sigar = new Sigar();
        // CPU的总量（单位：HZ）及CPU的相关信息
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {// 不管是单块CPU还是多CPU都适用
            CpuInfo info = infos[i];
            CPU cpu=new CPU();
            cpu.setnum(infos.length);
            cpu.setMHZ(info.getMhz());
            cpu.setModel(info.getModel());
            cpu.setVendor(info.getVendor());
            cpu.setUsedper(cpuList[i].getCombined());
            acpu.add(cpu);
        }
        return acpu;
    }
}
