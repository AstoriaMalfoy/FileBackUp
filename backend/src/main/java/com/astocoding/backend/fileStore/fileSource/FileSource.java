package com.astocoding.backend.fileStore.fileSource;

import com.astocoding.backend.job.config.JobConfig;

import java.io.File;
import java.util.Iterator;
import java.util.List;

public interface FileSource  {

    void checkAccess();

    List<String> getAllFilePaths();

    File readFile(String filePath);

}
