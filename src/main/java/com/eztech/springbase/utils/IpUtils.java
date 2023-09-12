package com.eztech.springbase.utils;

import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.data.repository.init.ResourceReader;

import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * ip 工具类
 *
 * @author chenqinru
 * @date 2023/07/29
 */
public class IpUtils {

    private IpUtils() {
    }

    /**
     * 获取客户端IP
     *
     * @param request {@link HttpServletRequest}
     * @return IP 地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        List<String> headers = Arrays.asList("X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR", "X-Real-IP");
        String unknown = "unknown";

        if (Objects.isNull(request)) {
            return unknown;
        }
        String ipAddr = headers.stream()
                .map(request::getHeader)
                .filter(ip -> Objects.nonNull(ip) && ip.length() != 0 && !unknown.equalsIgnoreCase(ip))
                .findFirst()
                .orElse(request.getRemoteAddr());
        return "0:0:0:0:0:0:0:1".equals(ipAddr) ? "127.0.0.1" : ipAddr;
    }

    /**
     * 获取城市名称
     *
     * @param ip IP 地址
     * @return 城市名称
     */
    public static String getCity(String ip) {
        String region = getRegion(ip);
        return region.split("\\|")[3];
    }

    /**
     * 获取地区信息
     *
     * @param ip IP 地址
     * @return 地区信息
     */
    private static String getRegion(String ip) {
        try {
            // 获取资源文件的 URL
            ClassLoader classLoader = ResourceReader.class.getClassLoader();
            URL resourceURL = classLoader.getResource("/static/ip2region.xdb");
            byte[] byteArray = Files.readAllBytes(Paths.get(resourceURL.toURI()));
            Searcher searcher = Searcher.newWithBuffer(byteArray);
            String region = searcher.search(ip);
            searcher.close();
            return region;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}
