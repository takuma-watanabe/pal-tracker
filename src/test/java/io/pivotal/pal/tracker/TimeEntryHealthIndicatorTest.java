package io.pivotal.pal.tracker;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.health.Health;
import test.pivotal.pal.tracker.StubTimeEntryRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TimeEntryHealthIndicatorTest {
    private StubTimeEntryRepository stubTimeEntryRepository;
    private TimeEntryHealthIndicator timeEntryHealthIndicator;

    @Before
    public void setUp() throws Exception {
        stubTimeEntryRepository = new StubTimeEntryRepository();
        timeEntryHealthIndicator = new TimeEntryHealthIndicator(stubTimeEntryRepository);
    }

    @Test
    public void testHealthIsUpwhenListReturnsFiveEntriesOrMore() {
        List<TimeEntry> timeEntries = asList(
                new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry()
        );
        stubTimeEntryRepository.setList_returnValue(timeEntries);


        Health health = timeEntryHealthIndicator.health();


        assertEquals(Health.down().build(), health);
    }

    @Test
    public void testHealthIsDown_whenListReturnsLessThanFiveEntries() {
        List<TimeEntry> timeEntries = asList(
                new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry()
        );
        stubTimeEntryRepository.setList_returnValue(timeEntries);


        Health health = timeEntryHealthIndicator.health();


        assertEquals(Health.up().build(), health);
    }
}