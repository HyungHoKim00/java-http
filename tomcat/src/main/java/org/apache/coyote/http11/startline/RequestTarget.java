package org.apache.coyote.http11.startline;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class RequestTarget {

    private final String value;

    public RequestTarget(String value) {
        this.value = value;
    }

    public boolean startsWith(String startsWith) {
        return value.startsWith(startsWith);
    }

    public boolean isEqualTo(String target) {
        return value.equals(target);
    }

    public Path getPath() {
        String path = value;
        if (!path.contains(".")) {
            path = path + ".html";
        }

        URL resource = getClass().getClassLoader().getResource("static" + path);
        if (resource == null) {
            throw new IllegalArgumentException("Could not find resource " + path);
        }

        try {
            return Path.of(resource.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("cannot convert to URI: " + resource);
        }
    }

    public boolean isBlank() {
        return value.isBlank() || value.equals("/");
    }
}
