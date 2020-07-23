package com.udacity.jwdnd.course1.cloudstorage.entity;

import java.util.Arrays;

public class File {
    private Long fileId;
    private String fileName;
    private String contentType;
    private String fileSize;
    private Long userId;
    private byte[] fileData;

    public Long getFileId() {
        return this.fileId;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getContentType() {
        return this.contentType;
    }

    public String getFileSize() {
        return this.fileSize;
    }

    public Long getUserId() {
        return this.userId;
    }

    public byte[] getFileData() {
        return this.fileData;
    }

    public void setFileId(final Long fileId) {
        this.fileId = fileId;
    }

    public void setFileName(final String fileName) {
        this.fileName = fileName;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public void setFileSize(final String fileSize) {
        this.fileSize = fileSize;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public void setFileData(final byte[] fileData) {
        this.fileData = fileData;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof File)) {
            return false;
        } else {
            File other = (File)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label75: {
                    Object this$fileId = this.getFileId();
                    Object other$fileId = other.getFileId();
                    if (this$fileId == null) {
                        if (other$fileId == null) {
                            break label75;
                        }
                    } else if (this$fileId.equals(other$fileId)) {
                        break label75;
                    }

                    return false;
                }

                Object this$fileName = this.getFileName();
                Object other$fileName = other.getFileName();
                if (this$fileName == null) {
                    if (other$fileName != null) {
                        return false;
                    }
                } else if (!this$fileName.equals(other$fileName)) {
                    return false;
                }

                Object this$contentType = this.getContentType();
                Object other$contentType = other.getContentType();
                if (this$contentType == null) {
                    if (other$contentType != null) {
                        return false;
                    }
                } else if (!this$contentType.equals(other$contentType)) {
                    return false;
                }

                label54: {
                    Object this$fileSize = this.getFileSize();
                    Object other$fileSize = other.getFileSize();
                    if (this$fileSize == null) {
                        if (other$fileSize == null) {
                            break label54;
                        }
                    } else if (this$fileSize.equals(other$fileSize)) {
                        break label54;
                    }

                    return false;
                }

                label47: {
                    Object this$userId = this.getUserId();
                    Object other$userId = other.getUserId();
                    if (this$userId == null) {
                        if (other$userId == null) {
                            break label47;
                        }
                    } else if (this$userId.equals(other$userId)) {
                        break label47;
                    }

                    return false;
                }

                return Arrays.equals(this.getFileData(), other.getFileData());
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof File;
    }

    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $fileId = this.getFileId();
        result = result * 59 + ($fileId == null ? 43 : $fileId.hashCode());
        Object $fileName = this.getFileName();
        result = result * 59 + ($fileName == null ? 43 : $fileName.hashCode());
        Object $contentType = this.getContentType();
        result = result * 59 + ($contentType == null ? 43 : $contentType.hashCode());
        Object $fileSize = this.getFileSize();
        result = result * 59 + ($fileSize == null ? 43 : $fileSize.hashCode());
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        result = result * 59 + Arrays.hashCode(this.getFileData());
        return result;
    }

    public String toString() {
        Long var10000 = this.getFileId();
        return "File(fileId=" + var10000 + ", fileName=" + this.getFileName() + ", contentType=" + this.getContentType() + ", fileSize=" + this.getFileSize() + ", userId=" + this.getUserId() + ", fileData=" + Arrays.toString(this.getFileData()) + ")";
    }

    public File(final Long fileId, final String fileName, final String contentType, final String fileSize, final Long userId, final byte[] fileData) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
        this.userId = userId;
        this.fileData = fileData;
    }
}
