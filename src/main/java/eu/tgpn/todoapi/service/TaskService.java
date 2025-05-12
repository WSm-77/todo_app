package eu.tgpn.todoapi.service;

import eu.tgpn.todoapi.repository.TaskRepository;
import eu.tgpn.todoapi.dto.TaskDTO;
import eu.tgpn.todoapi.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO add(TaskDTO taskDTO) {
        Task task = new Task(
                taskDTO.getTitle(),
                taskDTO.getDescription(),
                taskDTO.getPriority(),
                taskDTO.getDue()
        );

        logger.debug("Adding a new task: {}", taskDTO.getTitle());
        taskRepository.save(task);
        return(taskDTO);
    }
}
