package eu.tgpn.todoapi.service;

import eu.tgpn.todoapi.repository.TaskRepository;
import eu.tgpn.todoapi.dto.TaskDTO;
import eu.tgpn.todoapi.entity.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

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

    public List<TaskDTO> getTasks() {
        logger.debug("Get all tasks");

        return taskRepository.findAll().stream()
                .map(Task::toDTO)
                .toList();
    }

    public Optional<TaskDTO> getTask(Integer taskId) {
        logger.debug("Get task with id: {}", taskId);
        return taskRepository.findById(taskId).map(Task::toDTO);
    }


    public Optional<TaskDTO> updateTask(Integer taskId, TaskDTO taskDTO) {
        logger.debug("Update task with id: {}", taskId);

        Optional<Task> existingTaskOpt = taskRepository.findById(taskId);

        if (existingTaskOpt.isEmpty()) {
            return Optional.empty();
        }

        Task exisitngTask = existingTaskOpt.get();

        if (taskDTO.getTitle() != null) {
            exisitngTask.setTitle(taskDTO.getTitle());
        }
        if (taskDTO.getDescription() != null) {
            exisitngTask.setDescription(taskDTO.getDescription());
        }

        if (taskDTO.getDue() != null) {
            exisitngTask.setDue(taskDTO.getDue());
        }

        if (taskDTO.getPriority() != null) {
            exisitngTask.setPriority(taskDTO.getPriority());
        }

        Task updatedTask = taskRepository.save(exisitngTask);

        return Optional.of(updatedTask.toDTO());
    }
}
