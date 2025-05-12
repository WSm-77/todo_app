package eu.tgpn.todoapi.entity;

import eu.tgpn.todoapi.dto.TaskDTO;
import eu.tgpn.todoapi.enums.PriorityEnum;
import jakarta.persistence.*;

import java.time.OffsetDateTime;

@Entity
@Table(name="Task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PriorityEnum priority;

    @Column
    private OffsetDateTime due;

    public Task(String title, String description, PriorityEnum priority, OffsetDateTime due) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.due = due;
    }

    public Task() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public OffsetDateTime getDue() {
        return due;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public void setDue(OffsetDateTime due) {
        this.due = due;
    }

    public TaskDTO toDTO() {
        return new TaskDTO(this.id, this.title, this.description, this.priority, this.due);
    }
}
