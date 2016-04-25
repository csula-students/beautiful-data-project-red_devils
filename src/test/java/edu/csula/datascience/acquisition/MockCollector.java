package edu.csula.datascience.acquisition;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A mock implementation of collector for testing
 */
public class MockCollector implements Collector<SimpleModel, MockData> {
    @Override
    public Collection<SimpleModel> mungee(Collection<MockData> src) {
        // in your example, you might need to check src.hasNext() first
    	
        return src
            .stream()
            .filter(data -> data.getDate_of_stop() != null)
        .filter(data -> data.getTime_of_stop() != null)
        .filter(data -> data.getDescription() != null)
        .filter(data -> data.getAccident() != null)
        .filter(data -> data.getAlcohol() != null)
        .filter(data -> data.getAgency() != null)
        .filter(data -> data.getBelts() != null)
        .filter(data -> data.getArrest_type() != null)
        .filter(data -> data.getCharge() != null)
        .filter(data -> data.getContributed_to_accident() != null)
        .filter(data -> data.getFatal() != null)
        .filter(data -> data.getPersonal_injury() != null)
        .filter(data -> data.getProperty_damage() != null)
            .map(SimpleModel::build)
            .collect(Collectors.toList());
    }

    @Override
    public void save(Collection<SimpleModel> data) {
    }
}
