package test.pivotal.pal.tracker;

import io.pivotal.pal.tracker.TimeEntry;
import io.pivotal.pal.tracker.TimeEntryRepository;

import java.util.List;

public class StubTimeEntryRepository implements TimeEntryRepository {
    private TimeEntry create_returnValue;
    private List<TimeEntry> list_returnValue;

    public void setCreate_returnValue(TimeEntry create_returnValue) {
        this.create_returnValue = create_returnValue;
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        return create_returnValue;
    }

    @Override
    public TimeEntry find(Long id) {
        return null;
    }

    public void setList_returnValue(List<TimeEntry> list_returnValue) {
        this.list_returnValue = list_returnValue;
    }

    @Override
    public List<TimeEntry> list() {
        return list_returnValue;
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
