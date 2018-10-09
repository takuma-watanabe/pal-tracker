package io.pivotal.pal.tracker;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

    private Map<String, String> variables;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                         @Value("${MEMORY_LIMIT:NOT SET}") String memoryLimit,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfInstanceIndex,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfInstanceAddr) {
        variables = new HashMap<String, String>();
        variables.put("PORT", port);
        variables.put("MEMORY_LIMIT", memoryLimit);
        variables.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        variables.put("CF_INSTANCE_ADDR", cfInstanceAddr);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        return variables;
    }
}
