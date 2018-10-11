package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private List<TimeEntry> list = new ArrayList<TimeEntry>();

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(list.size() + 1);
        list.add(timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {

        for (TimeEntry item : list) {
            if (item.getId() == id) {
                return item;
            }
        }

        return null;
    }

    public List<TimeEntry> list() {
        return this.list;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        delete(id);
        timeEntry.setId(id);
        list.add(timeEntry);
        return timeEntry;
    }

    public TimeEntry delete(long id) {
        TimeEntry entry = find(id);
        if (entry != null) {
            list.remove(entry);
            return entry;
        }

        return null;
    }

}
