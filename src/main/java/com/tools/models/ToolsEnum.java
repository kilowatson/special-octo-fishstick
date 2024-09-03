package com.tools.models;

public enum ToolsEnum{
	BASE_64("Base 64 Encoding/Decoding"),
	CSV_TO_JSON("CSV to JSON")

    ;

    private final String text;

    /**
     * @param text
     */
    ToolsEnum(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
