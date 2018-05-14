package util.helpers;

import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import util.SeleniumExecutor;
import util.configurations.TestConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Log4j
public class FileHelper {

    public static void cleanDirectory(String path) {
        try {
            if (new File(path).exists())
                FileUtils.cleanDirectory(new File(path));
        } catch (IOException e) {
            log.error("Unable to clean directory: " + path, e);
        }
    }

    public static File getLatestFileFromDirectory(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }

        File lastModifiedFile = files[0];
        for (int i = 1; i < files.length; i++) {
            if (lastModifiedFile.lastModified() < files[i].lastModified()) {
                lastModifiedFile = files[i];
            }
        }
        return lastModifiedFile;
    }

    private static boolean isFileNotCompleted() {
        boolean result = false;
        File folder = new File(TestConfiguration.downloadFolderPath);

        if (folder.exists() && folder.listFiles().length > 0) {
            for (int i = 0; i < folder.listFiles().length; i++) {
                String fileName = folder.listFiles()[i].getName();
                if (fileName.endsWith(".part") || fileName.endsWith(".crdownload") || fileName.endsWith(".tmp")) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public static boolean isAnyFileDownloaded() {

        boolean fileFound = false;
        boolean result = false;
        int counter = 0;
        File folder = new File(TestConfiguration.downloadFolderPath);

        while (counter < SeleniumExecutor.TIMEOUT) {
            if (folder.exists() && folder.listFiles().length > 0 && !isFileNotCompleted()) {
                fileFound = true;
                break;
            } else {
                SeleniumExecutor.pause(SeleniumExecutor.SHORT_TIME_FOR_THREAD);
                counter++;
            }
        }

        if (fileFound) {
            File file = folder.listFiles()[0];

            FileInputStream fileStream = null;
            try {
                fileStream = new FileInputStream(file);

                while (counter < SeleniumExecutor.TIMEOUT) {
                    try {
                        if (fileStream.available() == 0) {
                            SeleniumExecutor.pause(SeleniumExecutor.SHORT_TIME_FOR_THREAD);
                            counter++;
                        } else {
                            result = true;
                            break;
                        }
                    } catch (IOException e) {
                        log.error("Problem while getting stream availability", e);
                    }
                }
            } catch (FileNotFoundException e) {
                log.error("Problem while open stream for file ", e);
            } finally {
                if (fileStream != null) {
                    try {
                        fileStream.close();
                    } catch (IOException e) {
                        log.error("Problem while close stream for file ", e);
                    }
                }
            }
        }

        return result;
    }

    public static String getAbsoluteFilePath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    public static String getAbsoluteFilePathToUpload(String fileLocation) {
        String tempPath = FileHelper.class.getResource(fileLocation).getFile();
        File file = new File(tempPath);
        return file.getAbsolutePath().replaceAll("%20", " ");
    }

    public static boolean deleteFile(String pathToFile) {

        File file = new File(pathToFile);

        if (file.isFile()) {
            file.delete();
        }

        return new File(pathToFile).exists();
    }

    public static void appendTabbEndedLineToFile(File f, String data) throws IOException {
        FileUtils.writeStringToFile(f, data + "\t", true);
    }
}
