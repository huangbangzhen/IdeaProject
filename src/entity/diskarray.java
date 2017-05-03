package entity;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/25/025.
 */
public class diskarray {
    public static ArrayList<Disk> Diskarray() throws SigarException
    {
        ArrayList<Disk> adisk=new ArrayList<>();
        Sigar sigar = new Sigar();

        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            Disk disk=new Disk();
            disk.setNum(fslist.length);//��������Ŀ
            FileSystem fs = fslist[i];
            disk.setDevName(fs.getDevName());// �������̷�����

            disk.setSysTypeName(fs.getSysTypeName());// �ļ�ϵͳ���ͣ����� FAT32��NTFS
            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            disk.setTotal((int) usage.getTotal()/1024);
            disk.setFree((int) usage.getFree()/1024);
            disk.setUsedper(usage.getUsePercent());
            adisk.add(disk);
        }
        return adisk;
    }
}
