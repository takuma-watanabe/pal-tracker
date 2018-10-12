package io.pivotal.pal.tracker;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.actuate.health.Health;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TimeEntryHealthIndicatorTest {
    private TimeEntryRepository mockRepository;
    private TimeEntryHealthIndicator timeEntryHealthIndicator;

    @Before
    public void setUp() throws Exception {
        mockRepository = mock(TimeEntryRepository.class);
        timeEntryHealthIndicator = new TimeEntryHealthIndicator(mockRepository);
    }

    @Test
    public void testHealthIsUpwhenListReturnsFiveEntriesOrMore() {
        List<TimeEntry> timeEntries = asList(
                new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry(), new TimeEntry()
        );
        doReturn(timeEntries)
                .when(mockRepository)
                .list();


        Health health = timeEntryHealthIndicator.health();


        assertEquals(Health.down().build(), health);
    }

    @Test
    public void testHealthIsDown_whenListReturnsLessThanFiveEntries() {
        List<TimeEntry> timeEntries = Collections.emptyList();
        doReturn(timeEntries)
                .when(mockRepository)
                .list();


        Health health = timeEntryHealthIndicator.health();


        assertEquals(Health.up().build(), health);
    }
}