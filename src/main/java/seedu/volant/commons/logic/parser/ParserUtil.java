package seedu.volant.commons.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.volant.commons.core.index.Index;
import seedu.volant.commons.logic.parser.exceptions.ParseException;
import seedu.volant.commons.util.StringUtil;
import seedu.volant.home.model.tag.Tag;
import seedu.volant.home.model.trip.DateRange;
import seedu.volant.home.model.trip.Location;
import seedu.volant.home.model.trip.Name;
import seedu.volant.itinerary.model.activity.Title;
import seedu.volant.journal.exceptions.ContentTooLongException;
import seedu.volant.journal.model.entry.Feeling;
import seedu.volant.journal.model.entry.SortType;
import seedu.volant.journal.model.entry.Weather;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String address} into an {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Location parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedAddress = location.trim();
        if (!Location.isValidLocation(trimmedAddress)) {
            throw new ParseException(Location.MESSAGE_CONSTRAINTS);
        }
        return new Location(trimmedAddress);
    }

    /**
     * Parses a {@code String dateRange} int a {@code DateRange}.
     * Leading an trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dateRange} is invalid.
     */
    public static DateRange parseDateRange(String dateRange) throws ParseException {
        requireNonNull(dateRange);
        String trimmedDateRange = dateRange.trim();

        if (!DateRange.isValidDateRange(trimmedDateRange)) {
            throw new ParseException(DateRange.MESSAGE_CONSTRAINTS);
        }

        String[] splitDate = trimmedDateRange.split(" to ");
        String[] fromFields = splitDate[0].strip().split("-");
        int fromDay = Integer.valueOf(fromFields[0]);
        int fromMonth = Integer.valueOf(fromFields[1]);
        int fromYear = Integer.valueOf(fromFields[2]);
        LocalDate parsedFromDate;
        String[] toFields = splitDate[1].strip().split("-");
        int toDay = Integer.valueOf(toFields[0]);
        int toMonth = Integer.valueOf(toFields[1]);
        int toYear = Integer.valueOf(toFields[2]);
        LocalDate parsedToDate;
        try {
            parsedFromDate = LocalDate.of(fromYear, fromMonth, fromDay);
            parsedToDate = LocalDate.of(toYear, toMonth, toDay);
        } catch (Exception e) {
            throw new ParseException("Please input a valid date in DD-MM-YYYY format!");
        }
        return new DateRange(parsedFromDate, parsedToDate);
    }


    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
    /**
     * Parses {@code String s} into title
     * @param title String to be parsed
     * @return title of activity.
     */
    public static Title parseTitle(String title) {
        return new Title(title);
    }
    /**
     * Parses a {@code String feeling} into a {@code Feeling}.
     * @param feeling
     * @return corresponding Feeling after parsing
     * @throws ParseException if given string does not correspond to an existing feeling
     */
    public static Feeling parseFeeling(String feeling) throws ParseException {
        requireNonNull(feeling);
        String trimmedCappedFeeling = feeling.trim().toUpperCase();
        Feeling parsedFeeling;
        try {
            parsedFeeling = Feeling.valueOf(trimmedCappedFeeling);
        } catch (Exception e) {
            throw new ParseException(Feeling.MESSAGE_CONSTRAINTS);
        }
        return parsedFeeling;
    }

    /**
     * Parses a {@code String date} into a {@code LocalDate}.
     */
    public static LocalDate parseDate(String date) throws ParseException {
        requireNonNull(date);
        String[] dateFields = date.strip().split("-");
        int day = Integer.valueOf(dateFields[0]);
        int month = Integer.valueOf(dateFields[1]);
        int year = Integer.valueOf(dateFields[2]);
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new ParseException("Please input a valid date in DD-MM-YYYY format!");
        }
        return parsedDate;
    }

    /**
     * Parses {@code String time} into a {@code LocalTime}.
     */
    public static LocalTime parseTime(String time) throws ParseException {
        requireNonNull(time);
        String[] timeFields = time.strip().split(":");
        int hour = Integer.valueOf(timeFields[0]);
        int minute = Integer.valueOf(timeFields[1]);
        LocalTime parsedTime;
        try {
            parsedTime = LocalTime.of(hour, minute);
        } catch (Exception e) {
            throw new ParseException("Please input a valid time in HH:MM format!");
        }
        return parsedTime;
    }

    /**
     * Parses {@code String weather} into a {@code Weather}.
     */
    public static Weather parseWeather(String weather) throws ParseException {
        requireNonNull(weather);
        String trimmedCappedWeather = weather.trim().toUpperCase();
        Weather parsedWeather;
        try {
            parsedWeather = Weather.valueOf(trimmedCappedWeather);
        } catch (Exception e) {
            throw new ParseException(Weather.MESSAGE_CONSTRAINTS);
        }
        return parsedWeather;
    }

    /**
     * Parses {@code String text} into a formatted {@code String}.
     */
    public static String parseText(String text) throws ContentTooLongException {
        requireNonNull(text);
        int contentLength = text.trim().length();
        if (contentLength > 280) {
            int charactersOverLimit = contentLength - 280;
            throw new ContentTooLongException(charactersOverLimit);
        }
        return text.trim();
    }

    /**
     * Parses {@code String sortType} into a {@code SortType}.
     */
    public static SortType parseSortType(String sortType) throws ParseException {
        requireNonNull(sortType);
        try {
            String formattedSortType = sortType.trim().toLowerCase().substring(0, 1);
            switch (formattedSortType) {
            case "o":
                return SortType.OLD;
            case "n":
                return SortType.NEW;
            case "l":
                return SortType.LOCATION;
            case "f":
                return SortType.FEELING;
            default:
                throw new ParseException(SortType.MESSAGE_CONSTRAINTS);
            }
        } catch (Exception e) {
            throw new ParseException(SortType.MESSAGE_CONSTRAINTS);
        }
    }
}
