package eu.tgpn.todoapi.controller;

import eu.tgpn.todoapi.api.TasksApi;
import eu.tgpn.todoapi.dto.TaskDTO;
import eu.tgpn.todoapi.service.TaskService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen",
        date = "2025-05-12T12:49:03.672455700+02:00[Europe/Warsaw]", comments = "Generator version: 7.9.0")
@Controller
@RequestMapping("${openapi.todoList.base-path:}")
public class TasksController implements TasksApi {
    private final NativeWebRequest request;
    private final TaskService taskService;

    public TasksController(NativeWebRequest request, TaskService taskService) {
        this.request = request;
        this.taskService = taskService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> postTasks(
            @Parameter(name = "Task", description = "")
            @Valid @RequestBody(required = false)
            TaskDTO taskDTO) {
        if (taskDTO == null) {
            return ResponseEntity.badRequest().build();
        }
        taskService.add(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    @GetMapping("/tasks")
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok(taskService.getTasks());
    }

    @Override
    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<TaskDTO> getTaskById(
            @Parameter(name = "taskId", description = "ID of task to return", required = true, in = ParameterIn.PATH)
            @PathVariable Integer taskId) {
        if (taskId == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<TaskDTO> optionalTaskDTO = taskService.getTask(taskId);

        if (optionalTaskDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new TaskDTO());
        }

        return ResponseEntity.ok(optionalTaskDTO.get());
    }
}
