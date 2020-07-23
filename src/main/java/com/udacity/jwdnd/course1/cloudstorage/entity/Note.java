package com.udacity.jwdnd.course1.cloudstorage.entity;

public class Note {
    private Long noteId;
    private String noteTitle;
    private String noteDescription;
    private Long userId;

    public Long getNoteId() {
        return this.noteId;
    }

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public String getNoteDescription() {
        return this.noteDescription;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setNoteId(final Long noteId) {
        this.noteId = noteId;
    }

    public void setNoteTitle(final String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public void setNoteDescription(final String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Note)) {
            return false;
        } else {
            Note other = (Note)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$noteId = this.getNoteId();
                    Object other$noteId = other.getNoteId();
                    if (this$noteId == null) {
                        if (other$noteId == null) {
                            break label59;
                        }
                    } else if (this$noteId.equals(other$noteId)) {
                        break label59;
                    }

                    return false;
                }

                Object this$noteTitle = this.getNoteTitle();
                Object other$noteTitle = other.getNoteTitle();
                if (this$noteTitle == null) {
                    if (other$noteTitle != null) {
                        return false;
                    }
                } else if (!this$noteTitle.equals(other$noteTitle)) {
                    return false;
                }

                Object this$noteDescription = this.getNoteDescription();
                Object other$noteDescription = other.getNoteDescription();
                if (this$noteDescription == null) {
                    if (other$noteDescription != null) {
                        return false;
                    }
                } else if (!this$noteDescription.equals(other$noteDescription)) {
                    return false;
                }

                Object this$userId = this.getUserId();
                Object other$userId = other.getUserId();
                if (this$userId == null) {
                    return other$userId == null;
                } else return this$userId.equals(other$userId);
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Note;
    }

    public int hashCode() {
//        int PRIME = true;
        int result = 1;
        Object $noteId = this.getNoteId();
        result = result * 59 + ($noteId == null ? 43 : $noteId.hashCode());
        Object $noteTitle = this.getNoteTitle();
        result = result * 59 + ($noteTitle == null ? 43 : $noteTitle.hashCode());
        Object $noteDescription = this.getNoteDescription();
        result = result * 59 + ($noteDescription == null ? 43 : $noteDescription.hashCode());
        Object $userId = this.getUserId();
        result = result * 59 + ($userId == null ? 43 : $userId.hashCode());
        return result;
    }

    public String toString() {
        Long var10000 = this.getNoteId();
        return "Note(noteId=" + var10000 + ", noteTitle=" + this.getNoteTitle() + ", noteDescription=" + this.getNoteDescription() + ", userId=" + this.getUserId() + ")";
    }

    public Note(final Long noteId, final String noteTitle, final String noteDescription, final Long userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }
}
