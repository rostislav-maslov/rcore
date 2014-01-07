package com.ub.core.pages.models.enums;

public enum PageStatus {
    PUBLISHED("PUBLISHED"),
    UNPUBLISHED("UNPUBLISHED"),
    ARCHIVED("ARCHIVED"),
    TRASHED("TRASHED")
    ;
    /**
     * @param text
     */
    private PageStatus(final String text) {
        this.text = text;
    }

    private final String text;

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
