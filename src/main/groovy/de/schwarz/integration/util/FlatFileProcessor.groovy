package de.schwarz.integration.util


import java.nio.file.Files
import java.nio.file.Path

class FlatFileProcessor {
    static final String DEFAULT_ENCODING = 'UTF-8'
    static final String DEFAULT_EXTENSION = '.csv'

    String directory
    String encoding = DEFAULT_ENCODING
    String extension = DEFAULT_EXTENSION

    def <T> T process(Closure<T> closure) {
        Path csv = Files.list(Path.of(directory)).find { it.toString().toLowerCase().endsWith(extension.toLowerCase()) }
        T result = closure(csv.newReader(encoding))
        result
    }

}