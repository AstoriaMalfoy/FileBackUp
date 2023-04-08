package com.astocoding.backend.fileStore.fileSource;

import com.astocoding.backend.exception.FileSourceAccessException;
import com.astocoding.backend.job.config.JobConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Data
@Slf4j
public class LocalFileSource implements FileSource{

    private List<String> filePaths;
    private List<String> accessFileList;

    @Override
    public void checkAccess() {
        if (filePaths == null || filePaths.size() == 0) {
            log.error("could not find the file path");
            throw new FileSourceAccessException("could not find the file path");
        }
        List<String> checkAccessFileList = new ArrayList<>();
//        check file exist and can read
        for (String filePath : filePaths) {
            try {
                File file = new File(filePath);
                boolean canAccess = true;
                if (!file.exists()) {
                    canAccess = false;
                    log.error("could not find the file path : {}",filePath);
                }
                if (file.isFile()){
                    if (!file.canRead()){
                        canAccess = false;
                        log.error("could not read the file : {}",filePath);
                    }
                }
                if (canAccess){
                    checkAccessFileList.add(filePath);
                }
            } catch (Exception e) {
                log.error("read file error : {}",e.getMessage());
            }
        }
//        replace all exist and can read file path
        this.accessFileList = checkAccessFileList;
    }

    @Override
    public List<String> getAllFilePaths() {
        return this.filePaths;
    }

    @Override
    public File readFile(String filePath) {
        return new File(filePath);
    }

}
