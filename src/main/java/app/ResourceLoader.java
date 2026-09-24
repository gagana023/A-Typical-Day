package app;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Loads project resources from either the classpath or the workspace when run directly from an IDE.
 */
public final class ResourceLoader {
  private ResourceLoader() {}

  /**
   * Opens a resource as an input stream. This works both in a proper classpath build and in a
   * direct IDE run where resources may not have been copied into the output folder yet.
   */
  public static InputStream openStream(String resourcePath) {
    String normalized = normalize(resourcePath);

    InputStream stream = ResourceLoader.class.getResourceAsStream(normalized);
    if (stream == null) {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl != null) {
        stream = cl.getResourceAsStream(normalized.substring(1));
      }
    }

    if (stream == null) {
      Path fallback = locateOnDisk(normalized);
      if (fallback != null) {
        try {
          return Files.newInputStream(fallback);
        } catch (IOException e) {
          throw new IllegalStateException("Unable to open resource: " + resourcePath, e);
        }
      }
    }

    if (stream == null) {
      throw new IllegalArgumentException("Resource not found: " + resourcePath);
    }

    return stream;
  }

  /**
   * Resolves a resource to a URL. This is used for JavaFX Image constructors that expect a URL.
   */
  public static URL getUrl(String resourcePath) {
    String normalized = normalize(resourcePath);

    URL url = ResourceLoader.class.getResource(normalized);
    if (url == null) {
      ClassLoader cl = Thread.currentThread().getContextClassLoader();
      if (cl != null) {
        url = cl.getResource(normalized.substring(1));
      }
    }

    if (url == null) {
      Path fallback = locateOnDisk(normalized);
      if (fallback != null) {
        try {
          return fallback.toUri().toURL();
        } catch (Exception e) {
          throw new IllegalStateException("Unable to resolve resource: " + resourcePath, e);
        }
      }
    }

    if (url == null) {
      throw new IllegalArgumentException("Resource not found: " + resourcePath);
    }

    return url;
  }

  private static String normalize(String resourcePath) {
    if (resourcePath == null || resourcePath.isBlank()) {
      throw new IllegalArgumentException("Resource path must not be empty");
    }
    return resourcePath.startsWith("/") ? resourcePath : "/" + resourcePath;
  }

  private static Path locateOnDisk(String normalizedPath) {
    String relative = normalizedPath.startsWith("/") ? normalizedPath.substring(1) : normalizedPath;
    Path[] candidates = {
      Paths.get("src", "main", "resources", relative),
      Paths.get("out", relative),
      Paths.get(relative)
    };

    for (Path candidate : candidates) {
      if (Files.exists(candidate) && Files.isRegularFile(candidate)) {
        return candidate;
      }
    }

    return null;
  }
}
