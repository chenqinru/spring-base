package com.eztech.springbase.utils.oshi;

import cn.hutool.system.oshi.OshiUtil;
import com.eztech.springbase.utils.ByteUtils;
import com.eztech.springbase.utils.MathUtils;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Oshi 工具类
 *
 * @author chenqinru
 * @date 2023/07/29
 */
public class OshiUtils {

    private OshiUtils() {
    }

    /**
     * 获取系统信息
     *
     * @return 系统信息
     */
    public static OsInfo getSystemInfo() {
        OsInfo os = new OsInfo();
        os.setName(System.getProperty("os.name"));
        os.setArch(System.getProperty("os.arch"));
        os.setVersion(System.getProperty("os.version"));
        os.setHostName(System.getProperty("user.name"));
        return os;
    }

    /**
     * 获取 Java 信息
     *
     * @return Java 信息
     */
    public static JavaInfo getJavaInfo() {
        JavaInfo java = new JavaInfo();
        java.setJavaHome(System.getProperty("java.home"));
        java.setJavaVersion(System.getProperty("java.version"));
        java.setJvmName(System.getProperty("java.vm.name"));
        java.setProjectHome(System.getProperty("user.dir"));
        return java;
    }

    /**
     * 获取内存信息
     *
     * @return 内存信息
     */
    public static MemoryInfo getMemoryInfo() {
        MemoryInfo memoryInfo = new MemoryInfo();
        GlobalMemory memory = OshiUtil.getMemory();
        memoryInfo.setTotal(ByteUtils.getSize(memory.getTotal()));
        memoryInfo.setFree(ByteUtils.getSize(memory.getAvailable()));
        memoryInfo.setUsed(ByteUtils.getSize(memory.getTotal() - memory.getAvailable()));
        memoryInfo.setUsage(MathUtils.getPercent(memory.getTotal(), memory.getTotal() - memory.getAvailable()) + "%");
        return memoryInfo;
    }

    /**
     * 获取 JVM 内存信息
     *
     * @return JVM 内存信息
     */
    public static JvmMemoryInfo getJvmMemoryInfo() {
        JvmMemoryInfo jvmMemoryInfo = new JvmMemoryInfo();
        Runtime runtime = Runtime.getRuntime();
        jvmMemoryInfo.setTotal(ByteUtils.getSize(runtime.totalMemory()));
        jvmMemoryInfo.setFree(ByteUtils.getSize(runtime.freeMemory()));
        jvmMemoryInfo.setUsed(ByteUtils.getSize(runtime.totalMemory() - runtime.freeMemory()));
        jvmMemoryInfo.setUsage(MathUtils.getPercent(runtime.totalMemory(), runtime.totalMemory() - runtime.freeMemory()) + "%");
        return jvmMemoryInfo;
    }

    /**
     * 获取 CPU 信息
     *
     * @return CPU 信息
     */
    public static CpuInfo getCpuInfo() {
        CpuInfo cpuInfo = new CpuInfo();
        CentralProcessor processor = OshiUtil.getProcessor();
        cn.hutool.system.oshi.CpuInfo cpu = OshiUtil.getCpuInfo();
        cpuInfo.setName(processor.getProcessorIdentifier().getName());
        cpuInfo.setCpuNum(cpu.getCpuNum());
        cpuInfo.setTotal(cpu.getToTal() + "%");
        cpuInfo.setSys(cpu.getSys() + "%");
        cpuInfo.setUser(cpu.getUser() + "%");
        cpuInfo.setWait(cpu.getWait() + "%");
        cpuInfo.setFree(cpu.getFree() + "%");
        return cpuInfo;
    }

    /**
     * 获取磁盘信息
     *
     * @return 磁盘信息
     */
    public static List<FileStoresInfo> getFileStoresInfo() {
        OperatingSystem operatingSystem = OshiUtil.getOs();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        return fileStores.stream().map(fileStore -> {
            FileStoresInfo partitionInfo = new FileStoresInfo();
            partitionInfo.setName(fileStore.getName());
            partitionInfo.setType(fileStore.getType());
            partitionInfo.setMountPoint(fileStore.getMount());
            partitionInfo.setTotal(ByteUtils.getSize(fileStore.getTotalSpace()));
            partitionInfo.setFree(ByteUtils.getSize(fileStore.getUsableSpace()));
            partitionInfo.setUsed(ByteUtils.getSize(fileStore.getTotalSpace() - fileStore.getUsableSpace()));
            partitionInfo.setUsage(MathUtils.getPercent(fileStore.getTotalSpace(), fileStore.getTotalSpace() - fileStore.getUsableSpace()) + "%");
            return partitionInfo;
        }).collect(Collectors.toList());
    }

    /**
     * 获取信息
     *
     * @return 信息
     */
    public static Map<String, Object> getInfo() {
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("os", getSystemInfo());
        map.put("java", getJavaInfo());
        map.put("memory", getMemoryInfo());
        map.put("jvmMemory", getJvmMemoryInfo());
        map.put("cpu", getCpuInfo());
        map.put("fileStores", getFileStoresInfo());
        return map;
    }

}
