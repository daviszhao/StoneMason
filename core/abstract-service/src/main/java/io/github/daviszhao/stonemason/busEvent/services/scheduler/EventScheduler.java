package io.github.daviszhao.stonemason.busEvent.services.scheduler;

import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.busEvent.services.EventService;
import io.github.daviszhao.stonemason.busEvent.services.consumers.AskEventConsumer;
import io.github.daviszhao.stonemason.busEvent.services.consumers.AskRespEventConsumer;
import io.github.daviszhao.stonemason.busEvent.services.consumers.NotifyEventConsumer;
import io.github.daviszhao.stonemason.busEvent.services.consumers.RevokeEventConsumer;
import io.github.daviszhao.stonemason.models.event.EventProcess;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import javax.inject.Named;

@Named
@AllArgsConstructor
public class EventScheduler {
    private EventService service;
    private NotifyEventConsumer notifyEventConsumer;
    private AskEventConsumer askEventConsumer;
    private AskRespEventConsumer askRespEventConsumer;
    private RevokeEventConsumer revokeEventConsumer;

    @Scheduled(fixedRate = 500L)
    public void sendUnpublishedEvent() {
        service.getAllUnpublishedEvent().forEach(eventPublish -> service.publishEvent(eventPublish));
    }

    @Scheduled(fixedRate = 500L)
    public void searchAndHandleUnprocessedEvent() {
        service.getUnproccessedEventProcesses().forEach(this::process);
    }

    private void process(EventProcess eventProcess) {

        switch (eventProcess.getEventcategory()) {
            case NOTIFY:
            default:
                notifyEventConsumer.process(eventProcess.getPayload(), eventProcess.getEventtype());

                break;
            case ASK:
                askEventConsumer.process(eventProcess.getPayload(), eventProcess.getEventtype());

                break;
            case REVOKE:
                revokeEventConsumer.process(eventProcess.getPayload(), eventProcess.getEventtype());

                break;
            case ASKRESP:
                askRespEventConsumer.process(eventProcess.getPayload(), eventProcess.getEventtype());

                break;
        }
        eventProcess.setStatus(ProcessStatus.PROCESSED);
        service.updateEventProcess(eventProcess);
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
