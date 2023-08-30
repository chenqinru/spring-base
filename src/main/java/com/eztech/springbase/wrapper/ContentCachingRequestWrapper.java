package com.eztech.springbase.wrapper;

import org.springframework.lang.NonNull;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 内容缓存请求包装
 * new String(((ContentCachingRequestWrapper)request).getCachedBody())
 * @author chenqinru
 * @date 2023/08/14
 */
public class ContentCachingRequestWrapper extends HttpServletRequestWrapper {
    private final byte[] cachedBody;

    public ContentCachingRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        InputStream inputStream = request.getInputStream();
        this.cachedBody = StreamUtils.copyToByteArray(inputStream);
    }

    @Override
    public ServletInputStream getInputStream() {
        return new CachedServletInputStream(this.cachedBody);
    }

    @Override
    public BufferedReader getReader() {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.cachedBody);
        return new BufferedReader(new InputStreamReader(byteArrayInputStream));
    }

    @NonNull
    @Override
    public String getCharacterEncoding() {
        return StandardCharsets.UTF_8.name();
    }

    private static class CachedServletInputStream extends ServletInputStream {

        private final InputStream cachedInputStream;

        public CachedServletInputStream(byte[] cachedBody) {
            this.cachedInputStream = new ByteArrayInputStream(cachedBody);
        }

        @Override
        public boolean isFinished() {
            try {
                return this.cachedInputStream.available() == 0;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener readListener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public int read() throws IOException {
            return this.cachedInputStream.read();
        }
    }

    public byte[] getCachedBody() {
        return cachedBody;
    }
}
