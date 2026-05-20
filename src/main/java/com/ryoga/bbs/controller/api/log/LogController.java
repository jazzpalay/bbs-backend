package com.ryoga.bbs.controller.api.log;

import com.ryoga.bbs.controller.api.log.form.ImageMetadataForm;
import com.ryoga.bbs.controller.api.log.form.LogForm;
import com.ryoga.bbs.controller.api.log.response.LogDetailResponse;
import com.ryoga.bbs.controller.api.log.response.LogListResponse;
import com.ryoga.bbs.controller.api.log.response.LogResponse;
import com.ryoga.bbs.domain.model.log.LogId;
import com.ryoga.bbs.domain.model.user.UserId;
import com.ryoga.bbs.domain.type.Id;
import com.ryoga.bbs.scenario.image.command.ImageCommand;
import com.ryoga.bbs.scenario.image.command.ImageCommandList;
import com.ryoga.bbs.scenario.log.LogScenario;
import com.ryoga.bbs.scenario.log.command.LogCommand;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {
    private final LogScenario logScenario;

    public  LogController(LogScenario logScenario) {
        this.logScenario = logScenario;
    }

    @PostMapping(value ="/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createLog(
            Authentication authentication,
            @RequestPart("logForm")
            @Valid LogForm logForm,
            @RequestPart(value = "metadata", required = false)
            List<ImageMetadataForm> metadata,
            @RequestPart(value = "images", required = false)
            List<MultipartFile> images
    ) throws IOException {
        String userId = authentication.getName();
        LogCommand logCommand = LogCommand.toCreateCommand(logForm, userId);
        ImageCommandList imageCommandList = ImageCommandList.toCreateCommand(userId, logCommand.getLogId(), metadata, images);
        logScenario.createLog(logCommand, imageCommandList);
        return new ResponseEntity<>(logCommand.getLogId(), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<LogListResponse> getLogList(Authentication authentication){
        String userId = authentication.getName();
        LogListResponse logListResponse = logScenario.getLogList(new UserId(Id.from(userId)));
        return new ResponseEntity<>(logListResponse, HttpStatus.OK);
    }

    @GetMapping("/{logId}")
    public ResponseEntity<LogDetailResponse> getLog(Authentication authentication, @PathVariable String logId) {
        String userId = authentication.getName();
        LogDetailResponse logDetailResponse = logScenario.getLog(new UserId(Id.from(userId)), new LogId(Id.from(logId)));
        return new ResponseEntity<>(logDetailResponse, HttpStatus.OK);
    }

    @PutMapping(value ="/{logId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateLog(
            Authentication authentication,
            @PathVariable String logId,
            @RequestPart("logForm") @Valid
            LogForm logForm,
            @RequestPart(value = "metadata", required = false)
            List<ImageMetadataForm> metadata,
            @RequestPart(value = "images", required = false)
            List<MultipartFile> images
    ) throws IOException {
        String userId = authentication.getName();
        LogCommand logCommand = LogCommand.toUpdateCommand(logForm, logId, userId);
        ImageCommandList imageCommandList = ImageCommandList.toCreateCommand(userId, logCommand.getLogId(), metadata, images);
        logScenario.updateLog(logCommand, imageCommandList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{logId}")
    public ResponseEntity<Void> deleteLog(Authentication authentication, @PathVariable String logId) {
        String userId = authentication.getName();
        logScenario.deleteLog(new LogId(Id.from(logId)), new UserId(Id.from(userId)));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
