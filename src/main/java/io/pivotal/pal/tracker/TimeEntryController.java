package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private final CounterService counterService;
    private final GaugeService gaugeService;
    private TimeEntryRepository repository;

    public TimeEntryController(
            TimeEntryRepository repository,
            CounterService counterService,
            GaugeService gaugeService
    ) {
        this.repository = repository;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @PostMapping()
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntry) {
        counterService.increment("TimeEntry.created");
        gaugeService.submit("timeEntries.count", repository.list().size());
        return new ResponseEntity<>(repository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry item = repository.find(id);

        if (item == null) {
            return new ResponseEntity<TimeEntry>(item, HttpStatus.NOT_FOUND);
        }

        counterService.increment("TimeEntry.read");
        return new ResponseEntity<TimeEntry>(item, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        counterService.increment("TimeEntry.listed");
        return new ResponseEntity<List<TimeEntry>>(repository.list(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable("id") long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry entry = repository.update(id, timeEntry);
        if (entry == null) {
            return new ResponseEntity<>(entry, HttpStatus.NOT_FOUND);
        }

        counterService.increment("TimeEntry.updated");
        return new ResponseEntity<>(entry, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") long id) {
        repository.delete(id);
        counterService.increment("TimeEntry.deleted");
        gaugeService.submit("timeEntries.count", repository.list().size());
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
