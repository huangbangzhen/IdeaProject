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
        // CPU����������λ��HZ����CPU�������Ϣ
        CpuInfo infos[] = sigar.getCpuInfoList();
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < infos.length; i++) {// �����ǵ���CPU���Ƕ�CPU������
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
