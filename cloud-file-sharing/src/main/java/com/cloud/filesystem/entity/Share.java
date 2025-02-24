package com.cloud.filesystem.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "share")
@IdClass(Share.PK.class) // tells JPA that PK is the composite key class.
public class Share {

    @EmbeddedId
    private Share.PK pk;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "file_id", insertable = false, updatable = false)
    private File file;

    @Column(name = "shared_on", nullable = false)
    private LocalDate sharedOn;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;

    @IdClass(Share.PK.class) // here
    public static class PK implements Serializable {
        @Id
        @Column(name = "user_id")
        private Long userId;

        @Id
        @Column(name = "file_id")
        private Long fileId;

        // No argument constructor for JPA
        public PK() {
        }

        public PK(Long userId, Long fileId) {
            this.userId = userId;
            this.fileId = fileId;
        }

        // Getters and Setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getFileId() {
            return fileId;
        }

        public void setFileId(Long fileId) {
            this.fileId = fileId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof PK))
                return false;
            PK pk = (PK) o;
            return Objects.equals(userId, pk.userId) && Objects.equals(fileId, pk.fileId);
        }

        // This ensures that objects with the same userId and fileId end up in the same
        // hash bucket in hash-based collections
        @Override
        public int hashCode() {
            return Objects.hash(userId, fileId);
        }
    }
}