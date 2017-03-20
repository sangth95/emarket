package services;

import services.impl.EmarketDataServiceFake;
import services.impl.EmarketDataServiceImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by An on 2/20/2017.
 */
@Singleton
public class ServiceFactory {
    private EmarketDataServiceFake emarketDataServiceFake;
    private EmarketDataServiceImpl emarketDataServiceImpl;

    @Inject
    public ServiceFactory(EmarketDataServiceFake emarketDataServiceFake, EmarketDataServiceImpl emarketDataServiceImpl) {
        this.emarketDataServiceFake = emarketDataServiceFake;
        this.emarketDataServiceImpl = emarketDataServiceImpl;
    }

    public EmarketDataService getEmarketDataService() {
        return this.emarketDataServiceImpl;
    }
}
