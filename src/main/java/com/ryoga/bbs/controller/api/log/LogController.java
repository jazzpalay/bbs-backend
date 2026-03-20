package com.ryoga.bbs.controller.api.log;

import com.ryoga.bbs.controller.api.log.form.LogForm;
import com.ryoga.bbs.controller.api.log.response.LogListResponse;
import com.ryoga.bbs.controller.api.log.response.LogResponse;
import com.ryoga.bbs.domain.model.log.LogId;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.log.LogScenario;
import com.ryoga.bbs.scenario.log.command.LogCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {
    private final LogScenario logScenario;

    public  LogController(LogScenario logScenario) {
        this.logScenario = logScenario;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createLog(Authentication authentication, @RequestBody LogForm logForm) {
        String userId = authentication.getName();
        LogCommand command = LogCommand.toCreateCommand(logForm, userId);
        logScenario.createLog(command);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<LogListResponse> getLogList(Authentication authentication){
        String userId = authentication.getName();
        LogListResponse logListResponse = logScenario.getLogList(new UserId(Id.from(userId)));
        return new ResponseEntity<>(logListResponse, HttpStatus.OK);

    }

    @GetMapping("/{logId}")
    public ResponseEntity<LogResponse> getLog(Authentication authentication, @PathVariable String logId) {
        String userId = authentication.getName();
        LogResponse logResponse = logScenario.getLog(new UserId(Id.from(userId)), new LogId(Id.from(logId)));
        return new ResponseEntity<>(logResponse, HttpStatus.OK);
    }

    @PutMapping("/{logId}")
    public ResponseEntity<Void> updateLog(Authentication authentication, @PathVariable String logId, @RequestBody LogForm logForm){
        String userId = authentication.getName();
        LogCommand command = LogCommand.toUpdateCommand(logForm, logId, userId);
        logScenario.updateLog(command);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<Void> deleteLog(Authentication authentication, @PathVariable String logId) {
        String userId = authentication.getName();
        logScenario.deleteLog(new LogId(Id.from(logId)), new UserId(Id.from(userId)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
