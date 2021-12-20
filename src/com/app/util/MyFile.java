package com.app.util;

/**
 * com.app.util
 * Create by Le Nguyen Tu Van
 * Date 12/20/2021 - 6:06 PM
 * Description: ...
 */
public class MyFile {
    private String name;
    private byte[] data;
    private String fileExtension;

    public MyFile(String name, byte[] data, String fileExtension) {
        this.name = name;
        this.data = data;
        this.fileExtension = fileExtension;
    }

    public String getName() {
        return name;
    }

    public byte[] getData() {
        return data;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
}
