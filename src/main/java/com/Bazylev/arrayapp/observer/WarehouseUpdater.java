package com.bazylev.arrayapp.observer;

import com.bazylev.arrayapp.entity.AbstractArrayWrapper;
import com.bazylev.arrayapp.warehouse.Warehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WarehouseUpdater implements Observer {
    private static final Logger LOGGER = LogManager.getLogger(WarehouseUpdater.class);
    private final Warehouse warehouse;

    public WarehouseUpdater() {
        this.warehouse = Warehouse.getInstance();
    }

    @Override
    public void update(AbstractArrayWrapper arrayWrapper) {
        LOGGER.debug("WarehouseUpdater received notification for array: {}", arrayWrapper.getId());
        warehouse.updateArrayData(arrayWrapper);
    }
}