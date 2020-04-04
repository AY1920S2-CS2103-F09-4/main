package seedu.volant.commons.logic.parser;


/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_LOCATION = new Prefix("l/");
    public static final Prefix PREFIX_DATERANGE = new Prefix("d/");
    public static final Prefix PREFIX_TITLE = new Prefix("a/");
    public static final Prefix PREFIX_DATE = new Prefix("d/");
    public static final Prefix PREFIX_TIME = new Prefix("t/");
    public static final Prefix PREFIX_TEXT = new Prefix("c/");
    public static final Prefix PREFIX_WEATHER = new Prefix("w/");
    public static final Prefix PREFIX_FEELING = new Prefix("f/");
}
