/**
 * This is a helper class that generates unique IDs for calendar entries.
 */
public class IDGenerator {
    private int nextEntryID = 1;

    public int getNextEntryID() {
        return nextEntryID++;
    }
}
