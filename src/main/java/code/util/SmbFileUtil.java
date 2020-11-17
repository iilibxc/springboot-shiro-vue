
package code.util;

import jcifs.smb.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

public class SmbFileUtil {
    //windows系统开启smb服务即可，linux系统需安装运行smb服务的软件包    
    private final static String sharePath = "192.168.9.209/shareFile";
    private final static String remoteUrl = "smb://192.168.9.209/shareFile/";
    private final static String username = "Administrator";
    private final static String password = "1qaz@WSX";
    private final static NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, username, password);

    public static void downloadShareFileToBrowswer(HttpServletResponse httpServletResponse, String sharePath, String fileName) {
        SmbFile smbFile;
        SmbFileInputStream smbFileInputStream = null;
        OutputStream outputStream = null;
        try {
            smbFile = new SmbFile(remoteUrl + sharePath + File.separator + fileName, auth);
            smbFileInputStream = new SmbFileInputStream(smbFile);
            httpServletResponse.setHeader("content-type", "application/octet-stream");
            httpServletResponse.setContentType("application/vnd.ms-excel;charset=UTF-8");
            httpServletResponse.setHeader("Content-disposition", "attachment;filename=" + fileName);
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ";filename*=utf-8''" + URLEncoder.encode(fileName, "UTF-8").replaceAll("+", "%20"));
            outputStream = httpServletResponse.getOutputStream();
            byte[] buff = new byte[2048];
            int len = 0;
            while ((len = smbFileInputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
            }
            outputStream.flush();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                smbFileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String downloadShareFile(String sharePath, String fileName, String localDir) {
        InputStream in = null;
        OutputStream out = null;
        boolean b = false;
        try {
            SmbFile remoteFile = new SmbFile(remoteUrl + sharePath + fileName, auth);
            if (remoteFile == null) {
                throw new Exception("共享文件不存在");
            }
            File localFile = new File(localDir + fileName);
            if (!localFile.exists()) {
                if (!localFile.getParentFile().exists()) {
                    localFile.getParentFile().mkdirs();
                }
            }
            in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            byte[] buffer = new byte[1024];
            while (in.read(buffer) != -1) {
                out.write(buffer);
                buffer = new byte[1024];
            }
            b = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b + "--" + remoteUrl + sharePath + fileName;
    }

    public static String uploadShareFile(String localFilePath, String sharePath) {
        InputStream in = null;
        OutputStream out = null;
        boolean b = false;
        String fileName = "";
        try {
            File localFile = new File(localFilePath);
            fileName = localFile.getName();
            mkDirs(sharePath);
            SmbFile remoteFile = new SmbFile(remoteUrl + sharePath + fileName, auth);
            in = new BufferedInputStream(new FileInputStream(localFile));
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = in.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();           /* while (in.read(buffer) != -1) {                out.write(buffer);                buffer = new byte[1024];            }*/
            b = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return b + "--" + remoteUrl + sharePath + fileName;
    }

    /**
     * 获取共享文件夹下的文件名集合     *     * @return
     */
    public static List<String> getShareFileList(String shareFile) {
        SmbFile smbFile;
        List<String> fileNames = new LinkedList<>();
        try {
            smbFile = new SmbFile(remoteUrl + shareFile, auth);
            if (smbFile == null) {
                throw new Exception("共享文件夹不存在");
            }
            SmbFile[] files = smbFile.listFiles();
            for (SmbFile f : files) {
                fileNames.add(f.getName());
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    public static String mkDirs(String folderName) {
        SmbFile smbFile;
        boolean b = false;
        try {
            smbFile = new SmbFile(remoteUrl + folderName, auth);
            if (!smbFile.exists()) {
                smbFile.mkdirs();
                b = true;
            } else if (smbFile.exists()) {
                b = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        }
        return b + "--" + remoteUrl + folderName + "/";
    }

    public static String deleteFile(String shareFolderPath, String fileName) {
        SmbFile smbFile;
        boolean b = false;
        try {
            smbFile = new SmbFile(remoteUrl + shareFolderPath + fileName, auth);
            if (smbFile.exists()) {
                smbFile.delete();
                b = true;
            } else if (!smbFile.exists()) {
                b = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        }
        return b + "--" + remoteUrl + shareFolderPath + fileName;
    }

    public static String createFile(String shareFolderPath, Object json, String fileName) {
        SmbFile smbFile;
        SmbFileOutputStream smbFileOutputStream = null;
        boolean b = false;
        try {
            smbFile = new SmbFile(remoteUrl + shareFolderPath + fileName, auth);
            mkDirs(shareFolderPath);
            if (!smbFile.exists()) {
                smbFile.createNewFile();
                smbFileOutputStream = new SmbFileOutputStream(smbFile);
                smbFileOutputStream.write(json.toString().getBytes());
                smbFileOutputStream.close();
                b = true;
            } else if (smbFile.exists()) {
                b = true;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (SmbException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b + "--" + remoteUrl + shareFolderPath + fileName;
    }

}
