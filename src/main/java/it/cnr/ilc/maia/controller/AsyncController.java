package it.cnr.ilc.maia.controller;

import it.cnr.ilc.maia.service.AsyncService;
import java.util.Collection;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @GetMapping("")
    public Collection<AsyncService.AsyncInfo> getInfos() {
        return AsyncService.getInfos();
    }

    @DeleteMapping("")
    public void purgeInfos() {
        AsyncService.purgeInfos();
    }

    @GetMapping("/{uuid}")
    public AsyncService.AsyncInfo getInfo(@PathVariable("uuid") String uuid) {
        return AsyncService.getInfo(uuid);
    }

    @GetMapping("/test")
    public AsyncService.AsyncInfo test() throws Exception {
        AsyncService.AsyncInfo info = AsyncService.createAsync();
        new Thread() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        if (Math.random() < 0.0025) {
                            throw new Exception("Errore!");
                        }
                        AsyncService.setPercentage(info.getUuid(), (double) i / 100);
                        Thread.sleep(250);
                    }
                    AsyncService.setDone(info.getUuid(), "Fatto!");
                } catch (Exception e) {
                    AsyncService.setError(info.getUuid(), e);
                }
            }
        }.start();
        return info;
    }
}
