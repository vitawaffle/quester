package by.vitalylobatsevich.quester.entity.util;

import by.vitalylobatsevich.quester.entity.AppEntity;

public interface Updater<T extends AppEntity> {

    T update();

}
