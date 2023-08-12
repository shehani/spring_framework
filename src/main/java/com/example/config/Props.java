package com.example.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.util.Map;

@Component
@Data
@ConfigurationProperties(prefix = "halpe-church")
@Validated
public class Props {
    @Min(value = 2, message = "must be between 2 - 10")
    @Max(value = 10, message = "must be between 2 - 10")
    private int pageSize;
    private Map<String,String> idea;
    private List<String> schedule;

}
