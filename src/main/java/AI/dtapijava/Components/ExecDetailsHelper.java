package AI.dtapijava.Components;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;

@Component
@Getter
@Setter
public class ExecDetailsHelper {

    private Integer execTime;
    private Integer dbTime;
    private OffsetDateTime startExecTime;
    private OffsetDateTime startDbTime;

    public ExecDetailsHelper() {
        this.execTime = 0;
        this.dbTime = 0;
        this.startExecTime = OffsetDateTime.now();
    }

    public Integer getExecTimeWithEndExecTime(OffsetDateTime endExecTime) {
        this.execTime = Duration.between(this.startExecTime, endExecTime).getNano()/1000;
        return this.execTime;
    }

    public Integer getExecTime() {
        this.execTime = Duration.between(this.startExecTime, OffsetDateTime.now()).getNano()/1000;
        return this.execTime;
    }

    public void addNewDbTime(OffsetDateTime offsetDateTime) {
        if (this.startDbTime != null)
            this.dbTime += Duration.between(this.startDbTime, offsetDateTime).getNano()/1000;
        this.startDbTime = null;
    }

    public void addNewDbTime() {
        this.addNewDbTime(OffsetDateTime.now());
    }


}
