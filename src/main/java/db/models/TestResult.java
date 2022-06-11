package db.models;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class TestResult {
    public Integer id;
    public String name;
    public String status;
    public Timestamp updateDate;
    public Integer failureCounter;
}
