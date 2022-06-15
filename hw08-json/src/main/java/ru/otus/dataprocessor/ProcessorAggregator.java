package ru.otus.dataprocessor;

import ru.otus.model.Measurement;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summarizingDouble;
import static java.util.stream.Collectors.toMap;

public class ProcessorAggregator implements Processor {

    @Override
    public Map<String, Double> process(List<Measurement> data) {
        //группирует выходящий список по name, при этом суммирует поля value

        return data.stream()
                .collect(groupingBy(Measurement::getName,
                        summarizingDouble(Measurement::getValue)))
                .entrySet().stream()
                .collect(toMap(Map.Entry::getKey, e -> e.getValue().getSum(),
                        (a,b) -> a + b, TreeMap::new));
    }
}
