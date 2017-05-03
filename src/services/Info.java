package services;
import entity.*;
import org.hyperic.sigar.SigarException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/21/021.
 */
public class Info implements Serializable {
    public static ArrayList getinfo(){
        Memory memory = new Memory();
        Os os = new Os();
        Who who = new Who();
        ArrayList<CPU> acpu= null;
        ArrayList<Disk> adisk= null;
        try {
            acpu = new cpuarray().CPUarray();
            adisk = new diskarray().Diskarray();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        Net net=new Net();
        ArrayList info= new ArrayList();
        info.add(memory);
        info.add(os);
        info.add(who);
        info.add(acpu);
        info.add(adisk);
        info.add(net);

        return info;

//        CPU n = acpu.get(0);
//        Disk d = adisk.get(0);
//        for(int i = 0; i<adisk.size(); i++){
//            System.out.println(adisk.get(i).getDevName()+"------"+adisk.get(i).getSysTypeName()+"------"+adisk.get(i).getFree()+"------"+adisk.get(i).getNum()+"------"+adisk.get(i).getTotal()+"------"+adisk.get(i).getUsedper());
//        }


//        CPU[] cpuinfo = new CPU[4];//cpu信息用对象数组存储
//        CPUlist(cpuinfo);
//        System.out.println(cpuinfo[0].getMHZ());

//        ArrayList<CPU> acpu= new ArrayList<>();//CPU信息用集合存储
//        CPUarray(acpu);
//        CPU n = acpu.get(0);
//        System.out.println(n.getMHZ());

    }

}
