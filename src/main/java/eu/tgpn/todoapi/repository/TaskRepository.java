package eu.tgpn.todoapi.repository;

import eu.tgpn.todoapi.dto.TaskDTO;
import eu.tgpn.todoapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task ,Integer> {

}
