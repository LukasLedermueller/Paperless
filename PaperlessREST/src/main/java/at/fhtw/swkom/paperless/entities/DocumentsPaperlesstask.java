package at.fhtw.swkom.paperless.entities;

import jakarta.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;


@Entity
public class DocumentsPaperlesstask {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 0
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Integer id;

    @Column(nullable = false)
    private String taskId;

    @Column(nullable = false)
    private Boolean acknowledged;

    @Column
    private OffsetDateTime dateCreated;

    @Column
    private OffsetDateTime dateDone;

    @Column
    private OffsetDateTime dateStarted;

    @Column(columnDefinition = "text")
    private String result;

    @Column(nullable = false, length = 30)
    private String status;

    @Column
    private String taskFileName;

    @Column
    private String taskName;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(final String taskId) {
        this.taskId = taskId;
    }

    public Boolean getAcknowledged() {
        return acknowledged;
    }

    public void setAcknowledged(final Boolean acknowledged) {
        this.acknowledged = acknowledged;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getDateDone() {
        return dateDone;
    }

    public void setDateDone(final OffsetDateTime dateDone) {
        this.dateDone = dateDone;
    }

    public OffsetDateTime getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(final OffsetDateTime dateStarted) {
        this.dateStarted = dateStarted;
    }

    public String getResult() {
        return result;
    }

    public void setResult(final String result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public String getTaskFileName() {
        return taskFileName;
    }

    public void setTaskFileName(final String taskFileName) {
        this.taskFileName = taskFileName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(final String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String toString() {
        return "DocumentsPaperlesstask{" + "\n" +
                "    id=" + id + "\n" +
                "    taskId='" + taskId + '\'' + "\n" +
                "    acknowledged=" + acknowledged + "\n" +
                "    dateCreated=" + dateCreated + "\n" +
                "    dateDone=" + dateDone + "\n" +
                "    dateStarted=" + dateStarted + "\n" +
                "    result='" + result + '\'' + "\n" +
                "    status='" + status + '\'' + "\n" +
                "    taskFileName='" + taskFileName + '\'' + "\n" +
                "    taskName='" + taskName + '\'' + "\n" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentsPaperlesstask that = (DocumentsPaperlesstask) o;
        return Objects.equals(id, that.id) && Objects.equals(taskId, that.taskId) && Objects.equals(acknowledged, that.acknowledged) && Objects.equals(dateCreated, that.dateCreated) && Objects.equals(dateDone, that.dateDone) && Objects.equals(dateStarted, that.dateStarted) && Objects.equals(result, that.result) && Objects.equals(status, that.status) && Objects.equals(taskFileName, that.taskFileName) && Objects.equals(taskName, that.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskId, acknowledged, dateCreated, dateDone, dateStarted, result, status, taskFileName, taskName);
    }
}
