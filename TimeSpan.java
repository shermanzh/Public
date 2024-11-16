import java.util.Objects;

public class TimeSpan implements Comparable<TimeSpan> {
    private final int totalMinutes;

    private TimeSpan(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public static TimeSpan ofHours(int hours) {
        ensureNonNegative(hours, "hours");
        return new TimeSpan(hours * 60);
    }

    public static TimeSpan ofMinutes(int minutes) {
        ensureNonNegative(minutes, "minutes");
        return new TimeSpan(minutes);
    }

    public static TimeSpan ofHoursAndMinutes(int hours, int minutes) {
        ensureNonNegative(hours, "hours");
        ensureNonNegative(minutes, "minutes");
        return new TimeSpan(hours * 60 + minutes);
    }

    private static void ensureNonNegative(int value, String unit) {
        if (value < 0) {
            throw new IllegalArgumentException("negative " + unit + ": " + value);
        }
    }

    public int getHours() {
        return totalMinutes / 60;
    }

    public int getMinutes() {
        return totalMinutes % 60;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    @Override
    public String toString() {
        return getHours() + "h " + getMinutes() + "m";
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TimeSpan) {
            TimeSpan other = (TimeSpan) o;
            return this.totalMinutes == other.totalMinutes;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalMinutes);
    }

    @Override
    public int compareTo(TimeSpan other) {
        return Integer.compare(this.totalMinutes, other.totalMinutes);
    }

    public TimeSpan plus(TimeSpan other) {
        return new TimeSpan(this.totalMinutes + other.totalMinutes);
    }

    public TimeSpan plusHours(int hours) {
        return plus(ofHours(hours));
    }

    public TimeSpan plusMinutes(int minutes) {
        return plus(ofMinutes(minutes));
    }

    public TimeSpan plusHoursAndMinutes(int hours, int minutes) {
        return plus(ofHoursAndMinutes(hours, minutes));
    }
}