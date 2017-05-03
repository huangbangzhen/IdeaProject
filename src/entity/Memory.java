package entity;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/20/020.
 */
public class Memory implements Serializable{
    private long total;
    private long free;
    private long used;
    private float usedper;
    public Memory(){
        Sigar sigar = new Sigar();

        Mem mem = null;
        try {
            mem = sigar.getMem();
        } catch (SigarException e) {
            e.printStackTrace();
        }

        this.total = mem.getTotal()/1024;

        this.used = mem.getUsed()/1024;

        this.free = mem.getFree()/1024;

        this.usedper = (float) mem.getUsedPercent();
    }

    public float getUsedper() {
        return usedper;
    }

    public void setUsedper(float usedper) {
        this.usedper = usedper;
    }

    public long getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public long getFree() {
        return free;
    }

    public void setFree(int free) {
        this.free = free;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
