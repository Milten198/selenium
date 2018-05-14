package util.helpers;

import java.io.File;


public class DownloadFileHelper {

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirFiles = dir.listFiles();

        if (dirFiles != null) {
            for (File dirFile : dirFiles) {
                if (dirFile.getName().equals(fileName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
