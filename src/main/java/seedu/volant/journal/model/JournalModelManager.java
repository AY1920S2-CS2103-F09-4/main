package seedu.volant.journal.model;

import static java.util.Objects.requireNonNull;
import static seedu.volant.commons.logic.Page.JOURNAL;
import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.volant.commons.core.GuiSettings;
import seedu.volant.commons.logic.Page;
import seedu.volant.commons.model.Model;
import seedu.volant.commons.model.ReadOnlyUserPrefs;
import seedu.volant.commons.model.UserPrefs;
import seedu.volant.home.model.TripList;
import seedu.volant.home.model.trip.Trip;
import seedu.volant.journal.model.entry.Entry;
import seedu.volant.trip.model.Journal;

/**
 * Represents the in-memory model of the journal data.
 */
public class JournalModelManager implements Model {

    private final Predicate<Entry> predicateShowAllEntries = unused -> true;
    private final TripList tripList;
    private final Trip trip;
    private final Journal journal;
    private final EntryList entryList;
    private final UserPrefs userPrefs;
    private final Page page = JOURNAL;
    private final FilteredList<Entry> filteredEntries;

    /**
     * Initializes a JournalModelManager with the given tripList, trip, journal, and userPrefs.
     */
    public JournalModelManager(TripList tripList, Trip trip, Journal journal, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(tripList, trip, userPrefs);

        LOGGER.fine("You are now in the JOURNAL page of TRIP: " + trip + ".");

        this.tripList = tripList;
        this.trip = trip;
        this.journal = journal;
        this.entryList = getEntryList();
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredEntries = new FilteredList<>(this.entryList.getEntryList());
    }

    @Override
    public Page getPage() {
        return page;
    }

    // TODO: Complete implementation of methods once implementation of Journal has been completed.

    public TripList getTripList() {
        return tripList;
    }

    public Trip getTrip() {
        return trip;
    }

    public Journal getJournal() {
        return journal;
    }

    public EntryList getEntryList() {
        return journal.getEntryList();
    }

    /**
     * Returns true if entry is within the entry list within model.
     */
    public boolean hasEntry(Entry entry) {
        // requireNonNull(entry);
        // return entryList.hasEntry(entry);
        return false;
    }

    /**
     * Removes specified target {@code Entry} from entry list within model.
     */
    public void deleteEntry(Entry target) {
        entryList.removeEntry(target);
        updateFilteredEntryList(predicateShowAllEntries);
    }

    /**
     * Adds entry to entry list within model.
     */
    public void addEntry(Entry entry) {
        entryList.addEntry(entry);
        updateFilteredEntryList(predicateShowAllEntries);
    }

    public Predicate<Entry> getPredicateShowAllEntries() {
        return predicateShowAllEntries;
    }

    //=========== Filtered Trip List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Entry} backed by the internal list of
     * {@code versionedAddressBook}
     */

    public ObservableList<Entry> getFilteredEntryList() {
        return filteredEntries;
    }

    /**
     * Updates the filtered entry list according to the given predicate.
     */
    public void updateFilteredEntryList(Predicate<Entry> predicate) {
        requireNonNull(predicate);
        filteredEntries.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof JournalModelManager)) {
            return false;
        }

        // state check
        JournalModelManager other = (JournalModelManager) obj;
        return tripList.equals(other.tripList)
                && trip.equals(other.trip)
                && userPrefs.equals(other.userPrefs);
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getVolantFilePath() {
        return userPrefs.getVolantFilePath();
    }

    @Override
    public void setVolantFilePath(Path volantFilePath) {
        requireNonNull(volantFilePath);
        userPrefs.setVolantFilePath(volantFilePath);
    }

}
