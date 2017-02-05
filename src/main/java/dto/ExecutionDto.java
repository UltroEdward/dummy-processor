package dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import pipe.ExecutionStatus;

@Entity
@Table(name = "EXECUTIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ExecutionDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EXECUTION_ID", unique = true, nullable = false)
	@JsonProperty("id")
	private long id;
	
	@Column(name = "NAME", unique = true, nullable = false, length = 10)
	@JsonProperty("name")
	private String name;
	
	@Column(name = "START_TIME", unique = true, nullable = false, length = 10)
	@JsonProperty("start-time")
	private String startTime;
	
	@Column(name = "END_TIME", unique = true, nullable = false, length = 10)
	@JsonProperty("end-time")
	private String endTime;
	
	@Column(name = "STATUS", unique = true, nullable = false, length = 10)
	@JsonProperty("status")
	private ExecutionStatus status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public ExecutionStatus getStatus() {
		return status;
	}
	public void setStatus(ExecutionStatus status) {
		this.status = status;
	}
	
}
