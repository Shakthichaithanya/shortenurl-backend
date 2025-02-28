package com.shortenurl.shortenurl.events;

import com.shortenurl.shortenurl.service.ShortenurlService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationShutDownEvent implements ApplicationListener<ContextClosedEvent> {

    private final ShortenurlService shortenurlService;

    public ApplicationShutDownEvent (ShortenurlService shortenurlService) {
        this.shortenurlService = shortenurlService;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
       shortenurlService.deleteCache();
    }
}
