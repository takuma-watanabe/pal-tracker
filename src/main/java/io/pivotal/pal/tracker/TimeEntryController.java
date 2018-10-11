package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository repository;

    public TimeEntryController(TimeEntryRepository repository){
        this.repository = repository;
    }

    @ResponseBody
    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){
        return new ResponseEntity(repository.create(timeEntry), HttpStatus.CREATED);
    }

    @ResponseBody
    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") long id) {
        TimeEntry item = repository.find(id);
        return new ResponseEntity<TimeEntry>(item, (item == null) ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        return new ResponseEntity<List<TimeEntry>>(repository.list(), HttpStatus.OK );
    }

    @ResponseBody
    @PutMapping("/time-entries/{id}")
    public  ResponseEntity update(@PathVariable("id") long id, @RequestBody TimeEntry timeEntry){
        TimeEntry entry = repository.update(id, timeEntry);
        return new ResponseEntity(entry, (entry != null) ? HttpStatus.OK: HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry>  delete(@PathVariable("id") long id){
        TimeEntry entry = repository.delete(id);
        return new ResponseEntity<TimeEntry>(entry, HttpStatus.NO_CONTENT);
    }
}