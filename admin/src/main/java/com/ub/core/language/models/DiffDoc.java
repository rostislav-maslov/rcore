package com.ub.core.language.models;

import org.bson.types.ObjectId;

public class DiffDoc {
    private LanguageCode languageCode;
    private ObjectId docId;

    public DiffDoc() {
    }

    public DiffDoc(LanguageCode languageCode, ObjectId docId) {
        this.languageCode = languageCode;
        this.docId = docId;
    }

    public LanguageCode getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(LanguageCode languageCode) {
        this.languageCode = languageCode;
    }

    public ObjectId getDocId() {
        return docId;
    }

    public void setDocId(ObjectId docId) {
        this.docId = docId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiffDoc diffDoc = (DiffDoc) o;

        if (!docId.equals(diffDoc.docId)) return false;
        if (!languageCode.equals(diffDoc.languageCode)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = languageCode.hashCode();
        result = 31 * result + docId.hashCode();
        return result;
    }
}
