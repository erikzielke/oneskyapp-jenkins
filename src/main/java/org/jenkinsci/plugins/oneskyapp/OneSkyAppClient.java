package org.jenkinsci.plugins.oneskyapp;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.jenkinsci.plugins.oneskyapp.model.*;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OneSkyAppClient {
    private String publicKey;
    private String secretKey;

    public static void main(String[] args) {
        OneSkyAppClient oneSkyAppClient = new OneSkyAppClient("", "");
        final List<OneSkyAppProjectGroup> projectGroups = oneSkyAppClient.getProjectGroups();
        for (OneSkyAppProjectGroup projectGroup : projectGroups) {
            System.out.println(projectGroup);
            final List<OneSkyAppProject> projects = oneSkyAppClient.getProjects(projectGroup);
            for (OneSkyAppProject project : projects) {
                final OneSkyAppProject show = oneSkyAppClient.show(project);
                System.out.println("  " + show);
                System.out.println("Files:");
                final List<OneSkyAppFile> files = oneSkyAppClient.getFiles(project);
                for (OneSkyAppFile file : files) {
                    System.out.println("    " + file);
                }
                System.out.println("Languages:");
                final List<OneSkyAppProjectLanguage> projectLanguages = oneSkyAppClient.getProjectLanguages(project);
                for (OneSkyAppProjectLanguage projectLanguage : projectLanguages) {
                    System.out.println("     " + projectLanguage);
                    for (OneSkyAppFile file : files) {
                        final File translation = oneSkyAppClient.getTranslation(project, file, projectLanguage, null);
                        System.out.println("     " + "Exporting file to: " + translation.getAbsolutePath());
                    }

                }

            }
        }
    }

    public OneSkyAppClient(String publicKey, String secretKey) {
        this.publicKey = publicKey;
        this.secretKey = secretKey;
    }

    public List<OneSkyAppProjectGroup> getProjectGroups() {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final long timestamp = System.currentTimeMillis() /1000;
            final String devHash = Hashing.md5().hashString(timestamp + secretKey, StandardCharsets.UTF_8).toString();
            final String url = String.format("https://platform.api.onesky.io/1/project-groups?api_key=%1$s&timestamp=%2$s&dev_hash=%3$s", publicKey, String.valueOf(timestamp), devHash);
            final Request request = new Request.Builder().get().url(url).build();
            final Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final OneSkyAppListResult<OneSkyAppProjectGroup> result = OneSkyAppListResult.listFromStream(OneSkyAppProjectGroup.class, response.body().byteStream());
                if (result.meta != null) {
                    if (result.meta.getStatus() == 200) {
                        return result.data;
                    } else {
                        System.out.println(result.meta.getStatus());
                    }
                }
            } else {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OneSkyAppProject show(OneSkyAppProject oneSkyAppProject) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final long timestamp = System.currentTimeMillis() /1000;
            final String devHash = Hashing.md5().hashString(timestamp + secretKey, StandardCharsets.UTF_8).toString();
            final String url = String.format("https://platform.api.onesky.io/1/projects/%1$s?api_key=%2$s&timestamp=%3$s&dev_hash=%4$s", oneSkyAppProject.getId(), publicKey, String.valueOf(timestamp), devHash);
            final Request request = new Request.Builder().get().url(url).build();
            final Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final OneSkyAppListResult<OneSkyAppProject> result = OneSkyAppListResult.objectFromStream(OneSkyAppProject.class, response.body().byteStream());
                if (result.meta != null) {
                    if (result.meta.getStatus() == 200) {
                        return result.element;
                    } else {
                        System.out.println(result.meta.getStatus());
                    }
                }
            } else {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<OneSkyAppProject> getProjects(OneSkyAppProjectGroup projectGroup) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final long timestamp = System.currentTimeMillis() /1000;
            final String devHash = Hashing.md5().hashString(timestamp + secretKey, StandardCharsets.UTF_8).toString();
            final String url = String.format("https://platform.api.onesky.io/1/project-groups/%1$s/projects?api_key=%2$s&timestamp=%3$s&dev_hash=%4$s", projectGroup.getId(), publicKey, String.valueOf(timestamp), devHash);
            final Request request = new Request.Builder().get().url(url).build();
            final Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final OneSkyAppListResult<OneSkyAppProject> result = OneSkyAppListResult.listFromStream(OneSkyAppProject.class, response.body().byteStream());
                if (result.meta != null) {
                    if (result.meta.getStatus() == 200) {
                        return result.data;
                    } else {
                        System.out.println(result.meta.getStatus());
                    }
                }
            } else {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<OneSkyAppFile> getFiles(OneSkyAppProject project) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final long timestamp = System.currentTimeMillis() /1000;
            final String devHash = Hashing.md5().hashString(timestamp + secretKey, StandardCharsets.UTF_8).toString();
            final String url = String.format("https://platform.api.onesky.io/1/projects/%1$s/files?api_key=%2$s&timestamp=%3$s&dev_hash=%4$s", project.getId(), publicKey, String.valueOf(timestamp), devHash);
            final Request request = new Request.Builder().get().url(url).build();
            final Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final OneSkyAppListResult<OneSkyAppFile> result = OneSkyAppListResult.listFromStream(OneSkyAppFile.class, response.body().byteStream());
                if (result.meta != null) {
                    if (result.meta.getStatus() == 200) {
                        return result.data;
                    } else {
                        System.out.println(result.meta.getStatus());
                    }
                }
            } else {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getTranslation(OneSkyAppProject project, OneSkyAppFile file, OneSkyAppProjectLanguage language, File outputFile) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final long timestamp = System.currentTimeMillis() /1000;
            final String devHash = Hashing.md5().hashString(timestamp + secretKey, StandardCharsets.UTF_8).toString();
            final String url = String.format("https://platform.api.onesky.io/1/projects/%1$s/translations?api_key=%2$s&timestamp=%3$s&dev_hash=%4$s&locale=%5$s&source_file_name=%6$s", project.getId(), publicKey, String.valueOf(timestamp), devHash, language.getLocale(), file.getFileName());
            final Request request = new Request.Builder().get().url(url).build();
            final Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final File parentFile = outputFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                outputFile.createNewFile();
                final InputStream inputStream = response.body().byteStream();
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                int c;
                byte[] buffer = new byte[1024 * 32];
                while ((c = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, c);
                }
                fileOutputStream.close();
                System.out.println(outputFile.getAbsolutePath());
                return outputFile;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<OneSkyAppProjectLanguage> getProjectLanguages(OneSkyAppProject project) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            final long timestamp = System.currentTimeMillis() /1000;
            final String devHash = Hashing.md5().hashString(timestamp + secretKey, StandardCharsets.UTF_8).toString();
            final String url = String.format("https://platform.api.onesky.io/1/projects/%1$s/languages?api_key=%2$s&timestamp=%3$s&dev_hash=%4$s", project.getId(), publicKey, String.valueOf(timestamp), devHash);
            final Request request = new Request.Builder().get().url(url).build();
            final Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                final OneSkyAppListResult<OneSkyAppProjectLanguage> result = OneSkyAppListResult.listFromStream(OneSkyAppProjectLanguage.class, response.body().byteStream());
                if (result.meta != null) {
                    if (result.meta.getStatus() == 200) {
                        return result.data;
                    } else {
                        System.out.println(result.meta.getStatus());
                    }
                }
            } else {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class OneSkyAppListResult<T> {

        private OneSkyAppMeta meta;
        private List<T> data;
        private T element;

        public OneSkyAppListResult() {
            data = new ArrayList<T>();
        }

        public static <T> OneSkyAppListResult<T> listFromStream(Class<T> elementClass, InputStream inputStream) {
            Gson gson = new Gson();
            OneSkyAppListResult<T> oneSkyAppListResult = new OneSkyAppListResult<T>();
            try {
                JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    final String nextName = jsonReader.nextName();
                    if (nextName.equals("meta")) {
                        oneSkyAppListResult.meta = gson.fromJson(jsonReader, OneSkyAppMeta.class);
                    } else if (nextName.equals("data")) {
                        jsonReader.beginArray();
                        while (jsonReader.hasNext()) {
                            final T element = gson.fromJson(jsonReader, elementClass);
                            oneSkyAppListResult.data.add(element);
                        }
                        jsonReader.endArray();
                    }
                }
                jsonReader.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return oneSkyAppListResult;
        }
        public static <T> OneSkyAppListResult<T> objectFromStream(Class<T> elementClass, InputStream inputStream) {
            Gson gson = new Gson();
            OneSkyAppListResult<T> oneSkyAppListResult = new OneSkyAppListResult<T>();
            try {
                JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream));
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    final String nextName = jsonReader.nextName();
                    if (nextName.equals("meta")) {
                        oneSkyAppListResult.meta = gson.fromJson(jsonReader, OneSkyAppMeta.class);
                    } else if (nextName.equals("data")) {
                        oneSkyAppListResult.element = gson.fromJson(jsonReader, elementClass);
                    }
                }
                jsonReader.endObject();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return oneSkyAppListResult;
        }
    }
}
