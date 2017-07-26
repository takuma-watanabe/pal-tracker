package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository repository){
        this.repository = repository;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){
        return new ResponseEntity(repository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry item = repository.find(id);
        return new ResponseEntity<TimeEntry>(item, (item == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<List<TimeEntry>>(repository.list(), HttpStatus.OK );
    }

    @PutMapping("{id}")
    public  ResponseEntity update(@PathVariable("id") long id, @RequestBody TimeEntry timeEntry){
        TimeEntry entry = repository.update(id, timeEntry);
        return new ResponseEntity(entry, (entry != null) ? HttpStatus.OK: HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry>  delete(@PathVariable("id") long id){
        repository.delete(id);
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}
