package streams;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Use supplier in map to avoid multiple if-else statemenets.
 *
 * @author dzmitry.marudau
 * @since 2022.03
 */
public class SupplierProcessor {

    public static void main(String[] args) {
        var processor = new SupplierProcessor();
        System.out.println("processor.processFile(\"xls\") = " + processor.processFile("xls"));
        System.out.println("processor.processFile(\"pdf\") = " + processor.processFile("pdf"));
    }

    final Map<String, Supplier<String>> processorMap;

    public SupplierProcessor() {
        this.processorMap = Map.of(
                "xls", this::processXls,
                "pdf", this::processPdf,
                "txt", this::processTxt
        );
    }

    public String processFile(String extension) {
        if (processorMap.containsKey(extension)) {
            return processorMap.get(extension).get();
        }
        return "Invalid extension";
    }

    private String processXls() {
        return "Excel file is processed";
    }

    private String processPdf() {
        return "PDF file is processed";
    }

    private String processTxt() {
        return "Text file is processed";
    }
}
