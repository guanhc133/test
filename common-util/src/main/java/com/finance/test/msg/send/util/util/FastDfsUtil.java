package com.finance.test.msg.send.util.util;

import com.finance.test.msg.send.util.PoolFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

/**
 * @program: test
 * @description: fastDfs上传下载
 * @author: GuanHC
 * @create: 2018-05-04 10:05
 */
@Slf4j
public class FastDfsUtil {

    /**
     * fastdfs 配置文件路径
     */
    private static final String FAST_DFS_CONFIG = "fastdfs/fastDfs.conf";
    /**
     * 默认为Group1
     */
    private static final String GROUP1 = "group1";
    /**
     * 连接池
     */
    private ObjectPool<TrackerServer> pool;
    /**
     * 最大空闲数
     */
    private int maxIdle;
    /**
     * 总数
     */
    private int maxTotal;
    /**
     * 最小空闲数
     */
    private int minIdle;
    /**
     * 检查有效性
     */
    private boolean testOnBorrow = true;

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }


    public void init() throws Exception {
        String fastDfsConfigFilePath = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + FAST_DFS_CONFIG).getAbsolutePath();
        ClientGlobal.init(fastDfsConfigFilePath);
        initPool();
    }


    public void initPool() {
        PooledObjectFactory<TrackerServer> factory = new PoolFactory();
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(this.maxIdle);
        config.setMaxTotal(this.maxTotal);
        config.setMinIdle(this.minIdle);
        config.setTestOnBorrow(this.testOnBorrow);
        pool = new GenericObjectPool<TrackerServer>(factory, config);
    }



    public int download(String groupName, String remoteFilename, String localFileName) {
        int result = -1;
        TrackerServer trackerServer = null;
        try {
            trackerServer = pool.borrowObject();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 下载文件
            //Strings.isNullOrEmpty
            groupName = StringUtils.isEmpty(groupName) ? GROUP1 : groupName;
            result = storageClient.download_file(groupName, remoteFilename, localFileName);
        } catch (Exception e) {
            log.error("FASTDFS文件下载失败", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return result;
    }


    public InputStream download(String groupName, String remoteFilename) {
        InputStream inputStream = null;
        TrackerServer trackerServer = null;
        try {
            trackerServer = pool.borrowObject();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 下载文件
            groupName = StringUtils.isEmpty(groupName) ? GROUP1 : groupName;
            byte[] bytes = storageClient.download_file(groupName, remoteFilename);
            inputStream = new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            log.error("FASTDFS文件下载失败", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return inputStream;
    }


    public byte[] downloadWithByte(String groupName, String remoteFilename) {
        TrackerServer trackerServer = null;
        byte[] bytes = null;
        try {
            trackerServer = pool.borrowObject();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 下载文件
            groupName = StringUtils.isEmpty(groupName) ? GROUP1 : groupName;
            bytes = storageClient.download_file(groupName, remoteFilename);
        } catch (Exception e) {
            log.error("FASTDFS文件下载失败", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return bytes;
    }


    public int delete(String groupName, String remoteFilename) {
        int result = -1;
        TrackerServer trackerServer = null;
        try {
            trackerServer = pool.borrowObject();
            StorageClient storageClient = new StorageClient(trackerServer, null);
            // 删除文件
            groupName = StringUtils.isEmpty(groupName) ? GROUP1 : groupName;
            result = storageClient.delete_file(groupName, remoteFilename);
        } catch (Exception e) {
            log.error("FASTDFS文件删除失败", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return result;
    }


    public String[] uploadByPath(String localFilePath) {
        String[] result = null;
        TrackerServer trackerServer = null;
        try {
            trackerServer = pool.borrowObject();
            StorageClient storageClient = new StorageClient(trackerServer, null);

            result = storageClient.upload_file(localFilePath, null, null);
        } catch (Exception e) {
            log.error("FASTDFS文件上传失败", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        log.info("上传FASTDFS结果:{}", Arrays.toString(result));
        return result;
    }


    public String[] uploadFile(InputStream inStream, String uploadFileName, long fileLength) throws IOException {
        byte[] fileBuff = getFileBuffer(inStream, fileLength);
        String[] result = null;
        String fileExtName = "";
        if (uploadFileName.contains(".")) {
            fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);
        } else {
            return result;
        }
        //设置元信息
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("fileName", uploadFileName);
        metaList[1] = new NameValuePair("fileExtName", fileExtName);
        metaList[2] = new NameValuePair("fileLength", String.valueOf(fileLength));
        TrackerServer trackerServer = null;
        try {
            trackerServer = pool.borrowObject();
            StorageClient client = new StorageClient(trackerServer, null);
            result = client.upload_file(fileBuff, fileExtName, metaList);
        } catch (Exception e) {
            log.error("FASTDFS文件上传失败", e);
        } finally {
            closeTrackerServer(trackerServer);
        }
        return result;
    }


    private byte[] getFileBuffer(InputStream inStream, long fileLength) throws IOException {
        byte[] buffer = new byte[256 * 1024];
        byte[] fileBuffer = new byte[(int) fileLength];
        int count = 0;
        int length;
        while ((length = inStream.read(buffer)) != -1) {
            for (int i = 0; i < length; ++i) {
                fileBuffer[count + i] = buffer[i];
            }
            count += length;
        }
        return fileBuffer;
    }


    public void closeTrackerServer(TrackerServer trackerServer) {
        // 退出前,一定要将队列服务关闭
        try {
            if (trackerServer != null) {
                pool.returnObject(trackerServer);
            }
        } catch (Exception e) {
            log.error("关闭fastdfs服务失败", e);
        }
    }

}
