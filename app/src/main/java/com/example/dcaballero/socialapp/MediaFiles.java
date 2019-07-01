package com.example.dcaballero.socialapp;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import java.io.File;
import java.io.IOException;

public class MediaFiles {

    public enum Type {IMAGE, VIDEO, AUDIO};

    public static class UriPathFile {
        public File file;
        public Uri uri;
        public String path;
    }

    public static UriPathFile createFile(Context context, MediaFiles.Type type) throws IOException {
        String prefix = "";
        String suffix = "";
        String directory = null;
        switch (type){
            case IMAGE:
                prefix = "img";
                suffix = ".jpg";
                directory = Environment.DIRECTORY_PICTURES; // files/pictures
                break;
            case VIDEO:
                prefix = "vid";
                suffix = ".mp4";
                directory = Environment.DIRECTORY_MOVIES; // files/movies
                break;
            case AUDIO:
                prefix = "aud";
                suffix = ".3gp";
                directory = "files/audios";
                break;
        }
        File storageDir = context.getExternalFilesDir(directory);
        File file = File.createTempFile(prefix,suffix, storageDir);
        Uri fileUri = FileProvider.getUriForFile(context, "com.example.dcaballero.socialapp.fileprovider", file);

        UriPathFile uriPathFile = new UriPathFile();
        uriPathFile.file = file;
        uriPathFile.uri = fileUri;
        uriPathFile.path = file.getAbsolutePath();

        return uriPathFile;
    }
}
