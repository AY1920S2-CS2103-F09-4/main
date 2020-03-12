package seedu.volant.homepage.model.trip;

import static seedu.volant.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Trip in the location book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Trip {

    // Identity fields
    private final Name name;

    // Data fields
    private final Location location;
    private final DateRange dateRange;

    private TripFeatureList tripFeatureList;

    /**
     * Every field must be present and not null.
     */
    public Trip(Name name, Location location, DateRange dateRange) {
        requireAllNonNull(name, location, dateRange);
        this.name = name;
        this.location = location;
        this.dateRange = dateRange;

        tripFeatureList = new TripFeatureList();
    }

    public Name getName() {
        return name;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public Location getLocation() {
        return location;
    }

    public TripFeatureList getTripFeatureList() {
        return tripFeatureList;
    }

    /*
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }
    */

    /**
     * Returns true if both persons of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameTrip(Trip otherTrip) {
        if (otherTrip == this) {
            return true;
        }

        return otherTrip != null
                && otherTrip.getLocation().equals(getLocation())
                && (otherTrip.getDateRange().equals(getDateRange()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Trip)) {
            return false;
        }

        Trip otherTrip = (Trip) other;
        return otherTrip.getName().equals(getName())
                && otherTrip.getLocation().equals(getLocation())
                && otherTrip.getDateRange().equals(getDateRange());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, location, dateRange);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nLocation: ")
                .append(getLocation())
                .append("\nDate Range: ")
                .append(getDateRange());
        return builder.toString();
    }

}
