package com.sumitv.kafka.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString

public class Greeting {
    String message;
    String name;
}
