package com.treehole.util;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class FileUtil {
    private final static String HOST = "http://widealpha.top/";

    @SuppressWarnings("all")
    public static String saveImage(MultipartFile multipartFile, @Nullable String use) {
        if (multipartFile == null) {
            return null;
        }
        String fileName = generatorFilename(null, "jpg");
        String path = "/home/share/";
        if (use != null) {
            path += use + "/";
        }
        File p = new File(path);
        if (!p.exists()) {
            p.mkdirs();
        }
        File file = new File(path + fileName);
        file.setReadable(true, false);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            file.createNewFile();
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return use != null ? HOST + use + '/' + fileName : HOST + fileName;
    }

    @SuppressWarnings("all")
    public static String saveMusic(MultipartFile multipartFile, @Nullable String use) {
        if (multipartFile == null) {
            return null;
        }
        String filename;
        if (multipartFile.getOriginalFilename() != null) {
            String[] names = multipartFile.getOriginalFilename().split("\\.");
            filename = generatorFilename(null, names[names.length - 1]);
        } else {
            filename = generatorFilename(null, "mp3");
        }
        String path = "/home/share/";
        if (use != null && use.trim().length() > 0) {
            path += use + "/";
        }
        File p = new File(path);
        if (!p.exists()) {
            p.mkdirs();
        }
        File file = new File(path + filename);
        file.setReadable(true, false);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            file.createNewFile();
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return use != null ? HOST + use + '/' + filename : HOST + filename;
    }

    public static String generatorFilename(@Nullable String prefix, @Nullable String suffix) {
        Date date = new Date();
        StringBuilder builder = new StringBuilder();
        if (prefix != null) {
            builder.append(prefix).append('-');
        }
        builder.append(date.getTime());
        if (suffix != null) {
            builder.append('.').append(suffix);
        }
        return builder.toString();
    }
}
