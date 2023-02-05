package com.example.demo.Entity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class BookCover {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    private String filePath;
    private long fileSize;

    private String mediaType;

    @Lob
    private byte[] preview;

    @OneToOne
    private Book book;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getPreview() {
        return preview;
    }

    public void setPreview(byte[] preview) {
        this.preview = preview;
    }

    @Override
    public String toString() {
        return "BookCover{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", fileSize=" + fileSize +
                ", mediaType='" + mediaType + '\'' +
                ", preview=" + Arrays.toString(preview) +
                ", book=" + book +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookCover bookCover = (BookCover) o;
        return fileSize == bookCover.fileSize && Objects.equals(id, bookCover.id) && Objects.equals(filePath, bookCover.filePath) && Objects.equals(mediaType, bookCover.mediaType) && Arrays.equals(preview, bookCover.preview) && Objects.equals(book, bookCover.book);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, filePath, fileSize, mediaType, book);
        result = 31 * result + Arrays.hashCode(preview);
        return result;
    }
}
