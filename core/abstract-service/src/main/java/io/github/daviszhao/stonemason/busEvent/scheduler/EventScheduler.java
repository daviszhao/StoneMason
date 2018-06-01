package io.github.daviszhao.stonemason.busEvent.scheduler;

import io.github.daviszhao.stonemason.busEvent.services.EventService;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class EventScheduler {
    @Inject
    private EventService service;


    @Scheduled(fixedRate = 500L)
    public void sendUnpublishedEvent() {
        service.getAllUnpublishedEvent().forEach(eventPublish -> service.publishEvent(eventPublish));
    }

    //    @Scheduled(fixedRate = 500L)
    public void searchAndHandleUnprocessedEvent() {
        service.getUnproccessedEventProcesses().forEach(service::process);
    }
//
//    @Scheduled(fixedRate = 500L)
//    public void handleUnprocessedEventWatchProcess() {
//        eventBus.handleUnprocessedEventWatchProcess();
//    }
//
//    @Scheduled(fixedRate = 1000L)
//    public void handleTimeoutEventWatch() {
//        eventBus.handleTimeoutEventWatch();
//    }


}
