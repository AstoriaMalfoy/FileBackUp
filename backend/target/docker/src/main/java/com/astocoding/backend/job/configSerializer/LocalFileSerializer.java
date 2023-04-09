package com.astocoding.backend.job.configSerializer;

import com.astocoding.backend.job.JobCollection;

// 本地文件序列化
public class LocalFileSerializer implements JobConfigSerializer{

    JobCollection jobCollection;

    @Override
    public void startSerializer(JobCollection jobCollection) {
    }
}
